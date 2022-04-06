package org.auioc.mcmod.arnicalib.utils.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.auioc.mcmod.arnicalib.api.game.registry.OrderedForgeRegistries;
import org.auioc.mcmod.arnicalib.api.mixin.common.IMixinMobEffectInstance;
import org.auioc.mcmod.arnicalib.utils.java.RandomUtils;
import org.auioc.mcmod.arnicalib.utils.java.Validate;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

public interface EffectUtils {

    @Nullable
    static MobEffect getEffect(int id) {
        return MobEffect.byId(id);
    }

    @Nullable
    static MobEffect getEffect(String id) {
        return ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(id));
    }

    @Nullable
    static MobEffect getEffect(ResourceLocation id) {
        return ForgeRegistries.MOB_EFFECTS.getValue(id);
    }


    static List<MobEffect> getEffects(@Nullable MobEffectCategory type) {
        Collection<MobEffect> effects = ForgeRegistries.MOB_EFFECTS.getValues();
        List<MobEffect> effectsList = new ArrayList<>();
        for (MobEffect effect : effects) {
            if (type == null || effect.getCategory() == type) {
                effectsList.add(effect);
            }
        }
        return effectsList;
    }

    static List<MobEffect> getHarmfulEffects() {
        return getEffects(MobEffectCategory.HARMFUL);
    }

    static List<MobEffect> getBeneficialEffects() {
        return getEffects(MobEffectCategory.BENEFICIAL);
    }

    static List<MobEffect> getNeutralEffects() {
        return getEffects(MobEffectCategory.NEUTRAL);
    }

    static List<MobEffect> getAllEffects() {
        return getEffects(null);
    }

    static MobEffect getRandomEffect(boolean useOrderedRegestry) {
        if (useOrderedRegestry) {
            return RandomUtils.pickOneFromList(OrderedForgeRegistries.MobEffects.get()).getValue();
        }
        return RandomUtils.pickOneFromCollection(ForgeRegistries.MOB_EFFECTS.getValues());
    }


    @Nullable
    static MobEffectInstance createInstance(CompoundTag effect_nbt) {
        if (effect_nbt.contains("id", 8) && effect_nbt.contains("duration", 3) && effect_nbt.contains("amplifier", 3)) {
            return new MobEffectInstance(getEffect(effect_nbt.getString("id")), effect_nbt.getInt("duration"), effect_nbt.getInt("amplifier"));
        }
        return null;
    }

    static MobEffectInstance createInstance(JsonObject json) {
        ResourceLocation id = new ResourceLocation(GsonHelper.getAsString(json, "id"));
        Validate.isTrue(ForgeRegistries.MOB_EFFECTS.containsKey(id), "Unknown mob effect '" + id + "'");
        MobEffect effect = ForgeRegistries.MOB_EFFECTS.getValue(id);

        int duration = GsonHelper.getAsInt(json, "duration", 1);

        int amplifier = GsonHelper.getAsInt(json, "amplifier", 0);
        Validate.isInCloseInterval(0, 255, amplifier);

        boolean ambient = GsonHelper.getAsBoolean(json, "ambient", false);
        boolean visible = GsonHelper.getAsBoolean(json, "visible", true);
        boolean showIcon = GsonHelper.getAsBoolean(json, "show_icon", true);

        MobEffectInstance hiddenEffect = (MobEffectInstance) null;
        if (json.has("hidden_effect")) {
            JsonObject hiddenEffectJson = GsonHelper.getAsJsonObject(json, "hidden_effect");
            if (hiddenEffectJson.has("id")) {
                Validate.isTrue(GsonHelper.getAsString(hiddenEffectJson, "id").equals(id.toString()), "The id of the hidden effect must be unset or equal to the parent effect");
            } else {
                hiddenEffectJson.addProperty("id", id.toString());
            }
            Validate.isTrue(GsonHelper.getAsInt(hiddenEffectJson, "duration", 1) > duration, "The duration of the hidden effect must be greater than the parent effect");
            hiddenEffect = createInstance(hiddenEffectJson);
        }

        MobEffectInstance instance = new MobEffectInstance(effect, duration, amplifier, ambient, visible, showIcon, hiddenEffect);

        if (json.has("curative_items")) {
            JsonArray curativeItemsJson = GsonHelper.getAsJsonArray(json, "curative_items");
            List<ItemStack> curativeItems = new ArrayList<ItemStack>();
            for (JsonElement element : curativeItemsJson) {
                curativeItems.add(new ItemStack(ItemUtils.getItem(GsonHelper.convertToString(element, "curative_items"))));
            }
            instance.setCurativeItems(curativeItems);
        }

        return instance;
    }

    static void saveInstance(MobEffectInstance instance, JsonObject json) {
        json.addProperty("id", instance.getEffect().getRegistryName().toString());
        json.addProperty("duration", instance.getDuration());
        json.addProperty("amplifier", instance.getAmplifier());
        json.addProperty("ambient", instance.isAmbient());
        json.addProperty("visible", instance.isVisible());
        json.addProperty("show_icon", instance.showIcon());

        JsonArray curativeItems = new JsonArray();
        for (ItemStack itemStack : instance.getCurativeItems()) {
            curativeItems.add(itemStack.getItem().getRegistryName().toString());
        }
        json.add("curative_items", curativeItems);

        if (((IMixinMobEffectInstance) instance).getHiddenEffect() != null) {
            JsonObject hiddenEffect = new JsonObject();
            saveInstance(((IMixinMobEffectInstance) instance).getHiddenEffect(), hiddenEffect);
            json.add("hidden_effect", hiddenEffect);
        }
    }

    static JsonObject instanceToJson(MobEffectInstance instance) {
        JsonObject json = new JsonObject();
        saveInstance(instance, json);
        return json;
    }


    static void removeEffect(LivingEntity entity, Predicate<MobEffectInstance> condition) {
        List<MobEffect> toRemove = new ArrayList<>();

        entity.getActiveEffects().forEach(instance -> {
            if (condition.test(instance)) {
                toRemove.add(instance.getEffect());
            }
        });

        toRemove.forEach(effect -> entity.removeEffect(effect));
    }

    Predicate<MobEffectInstance> IS_BENEFICIAL = (e) -> e.getEffect().isBeneficial();
    Predicate<MobEffectInstance> IS_NOT_BENEFICIAL = (e) -> !e.getEffect().isBeneficial();
    Predicate<MobEffectInstance> IS_HARMFUL = (e) -> e.getEffect().getCategory() == MobEffectCategory.HARMFUL;
    Predicate<MobEffectInstance> IS_NEUTRAL = (e) -> e.getEffect().getCategory() == MobEffectCategory.NEUTRAL;


    static int getEffectLevel(LivingEntity entity, MobEffect effect) {
        MobEffectInstance instance = entity.getEffect(effect);
        if (instance == null) {
            return 0;
        }
        return instance.getAmplifier() + 1;
    }

}
