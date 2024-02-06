package org.auioc.mcmod.arnicalib.game.effect;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.neoforged.neoforge.common.EffectCure;
import org.apache.logging.log4j.Marker;
import org.auioc.mcmod.arnicalib.base.log.LogUtil;
import org.auioc.mcmod.arnicalib.base.validate.Validate;
import org.auioc.mcmod.arnicalib.game.registry.RegistryUtils;

import java.util.List;
import java.util.Optional;

import static org.auioc.mcmod.arnicalib.ArnicaLib.LOGGER;

public class MobEffectInstanceSerializer {

    private static final Marker MARKER = LogUtil.getMarker(MobEffectInstanceSerializer.class);

    public static final Codec<MobEffectInstance> CODEC = // TODO Test
        ExtraCodecs.lazyInitializedCodec(
            () -> RecordCodecBuilder.create(
                instance -> instance.group(
                    BuiltInRegistries.MOB_EFFECT.byNameCodec().fieldOf("id").forGetter(MobEffectInstance::getEffect),
                    ExtraCodecs.intRange(0, Integer.MAX_VALUE).fieldOf("duration").forGetter(MobEffectInstance::getDuration),
                    ExtraCodecs.intRange(0, 255).fieldOf("amplifier").forGetter(MobEffectInstance::getAmplifier),
                    Codec.BOOL.optionalFieldOf("ambient", false).forGetter(MobEffectInstance::isAmbient),
                    Codec.BOOL.optionalFieldOf("show_particles", true).forGetter(MobEffectInstance::isVisible),
                    Codec.BOOL.optionalFieldOf("show_icon", true).forGetter(MobEffectInstance::showIcon),
                    MobEffectInstanceSerializer.CODEC.optionalFieldOf("hidden_effect").forGetter((e) -> Optional.ofNullable(((IHMobEffectInstance) e).getHiddenEffect())),
                    MobEffectInstance.FactorData.CODEC.optionalFieldOf("factor_calculation_data").forGetter(MobEffectInstance::getFactorData),
                    EffectCure.CODEC.listOf().optionalFieldOf("cures").forGetter((o) -> {
                        var cures = o.getCures();
                        return cures.isEmpty() ? Optional.empty() : Optional.of(List.copyOf(cures));
                    })
                ).apply(instance, MobEffectInstanceSerializer::createInstance)
            )
        );

    public static JsonObject toJson(MobEffectInstance instance) {
        return GsonHelper.convertToJsonObject(
            MobEffectInstanceSerializer.CODEC
                .encodeStart(JsonOps.INSTANCE, instance)
                .getOrThrow(false, (error) -> LOGGER.error(MARKER, error)),
            "MobEffectInstance"
        );
    }

    public static MobEffectInstance fromJson(JsonElement json) {
        return MobEffectInstanceSerializer.CODEC
            .parse(new Dynamic<>(JsonOps.INSTANCE, json))
            .getOrThrow(false, (error) -> LOGGER.error(MARKER, error));
    }

    // ====================================================================== //

    private static MobEffectInstance createInstance(
        MobEffect effect, int duration, int amplifier, boolean ambient, boolean visible, boolean showIcon,
        Optional<MobEffectInstance> hiddenEffect, Optional<MobEffectInstance.FactorData> factorData,
        Optional<List<EffectCure>> cures
    ) {
        var instance = new MobEffectInstance(effect, duration, amplifier, ambient, visible, showIcon, hiddenEffect.orElse(null), Optional.empty());
        instance.getCures().clear();
        cures.ifPresent(effectCures -> instance.getCures().addAll(effectCures));
        return instance;
    }

    // ============================================================================================================== //


    /**
     * @deprecated Use {@link MobEffectInstanceSerializer#fromJson(JsonElement)} instead.
     */
    @Deprecated(since = "6.0.0", forRemoval = true)
    public static MobEffectInstance fromJson(JsonObject json) {
        String id = GsonHelper.getAsString(json, "id");
        MobEffect effect = MobEffectRegistry.getOrElseThrow(id);

        int duration = GsonHelper.getAsInt(json, "duration", 1);

        int amplifier = GsonHelper.getAsInt(json, "amplifier", 0);
        Validate.isInCloseInterval(0, 255, amplifier);

        boolean ambient = GsonHelper.getAsBoolean(json, "ambient", false);
        boolean visible = GsonHelper.getAsBoolean(json, "visible", true);
        boolean showIcon = GsonHelper.getAsBoolean(json, "show_icon", true);

        MobEffectInstance hiddenEffect = (MobEffectInstance) null;
        if (json.has("hidden_effect")) {
            JsonObject hiddenEffectJson = GsonHelper.getAsJsonObject(json, "hidden_effect");
            if (hiddenEffectJson.has("id") && !GsonHelper.getAsString(hiddenEffectJson, "id").equals(id)) throw new JsonSyntaxException("The id of the hidden effect must be unset or equal to the parent effect");
            else hiddenEffectJson.addProperty("id", id);
            if (GsonHelper.getAsInt(hiddenEffectJson, "duration", 1) <= duration) throw new JsonSyntaxException("The duration of the hidden effect must be greater than the parent effect");
            hiddenEffect = fromJson(hiddenEffectJson);
        }

        Optional<MobEffectInstance.FactorData> factorData =
            (json.has("FactorCalculationData"))
            ? MobEffectInstance.FactorData.CODEC.parse(new Dynamic<>(JsonOps.INSTANCE, json.get("FactorCalculationData"))).resultOrPartial((error) -> LOGGER.error(MARKER, error))
            : Optional.empty();

        MobEffectInstance instance = new MobEffectInstance(effect, duration, amplifier, ambient, visible, showIcon, hiddenEffect, factorData);

        //        if (json.has("curative_items")) {
        //            JsonArray curativeItemsJson = GsonHelper.getAsJsonArray(json, "curative_items");
        //            List<ItemStack> curativeItems = new ArrayList<ItemStack>();
        //            for (var element : curativeItemsJson) {
        //                var curativeItemId = GsonHelper.convertToString(element, "curative_item");
        //                curativeItems.add(new ItemStack(ItemRegistry.getOrElseThrow(curativeItemId)));
        //            }
        //            instance.setCurativeItems(curativeItems);
        //        }

        return instance;
    }

    /**
     * @deprecated Use {@link MobEffectInstanceSerializer#toJson} instead.
     */
    @Deprecated(since = "6.0.0", forRemoval = true)
    public static void toJson(MobEffectInstance instance, JsonObject json) {
        json.addProperty("id", RegistryUtils.id(instance.getEffect()).toString());
        json.addProperty("duration", instance.getDuration());
        json.addProperty("amplifier", instance.getAmplifier());
        json.addProperty("ambient", instance.isAmbient());
        json.addProperty("visible", instance.isVisible());
        json.addProperty("show_icon", instance.showIcon());

        //        JsonArray curativeItems = new JsonArray();
        //        for (ItemStack itemStack : instance.getCurativeItems()) {
        //            curativeItems.add(RegistryUtils.id(itemStack.getItem()).toString());
        //        }
        //        json.add("curative_items", curativeItems);

        if (((IHMobEffectInstance) instance).getHiddenEffect() != null) {
            JsonObject hiddenEffect = new JsonObject();
            toJson(((IHMobEffectInstance) instance).getHiddenEffect(), hiddenEffect);
            json.add("hidden_effect", hiddenEffect);
        }

        instance.getFactorData().ifPresent((data) -> {
            MobEffectInstance.FactorData.CODEC.encodeStart(JsonOps.INSTANCE, data)
                .resultOrPartial((error) -> LOGGER.error(MARKER, error))
                .ifPresent((d) -> json.add("FactorCalculationData", d));
        });
    }

}
