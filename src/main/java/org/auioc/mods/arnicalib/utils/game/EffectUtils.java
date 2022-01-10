package org.auioc.mods.arnicalib.utils.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import com.google.gson.JsonObject;
import org.auioc.mods.arnicalib.api.game.registry.OrderedForgeRegistries;
import org.auioc.mods.arnicalib.utils.java.RandomUtils;
import org.auioc.mods.arnicalib.utils.java.Validate;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
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
            return RandomUtils.pickOneFromList(OrderedForgeRegistries.MobEffects.ENTRIES).getValue();
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

    static MobEffectInstance createInstance(@Nullable JsonObject json) {
        if (json == null) {
            return (MobEffectInstance) null;
        }

        ResourceLocation effectId = new ResourceLocation(GsonHelper.getAsString(json, "id"));
        MobEffect effect = ForgeRegistries.MOB_EFFECTS.getValue(effectId);
        Validate.notNull(effect, "Unknown mob effect '" + effectId + "'");

        int duration = GsonHelper.getAsInt(json, "duration", 1);
        Validate.isInCloseInterval(1, 1000000, duration);
        if (!effect.isInstantenous()) {
            duration *= 20;
        }

        int amplifier = GsonHelper.getAsInt(json, "amplifier", 0);
        Validate.isInCloseInterval(0, 255, amplifier);

        boolean ambient = GsonHelper.getAsBoolean(json, "ambient", false);
        boolean visible = GsonHelper.getAsBoolean(json, "visible", true);
        boolean showIcon = GsonHelper.getAsBoolean(json, "showIcon", true);

        MobEffectInstance hiddenEffect = createInstance(GsonHelper.getAsJsonObject(json, "hiddenEffect", null));

        return new MobEffectInstance(effect, duration, amplifier, ambient, visible, showIcon, hiddenEffect);
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

    static Predicate<MobEffectInstance> IS_BENEFICIAL = (e) -> e.getEffect().isBeneficial();
    static Predicate<MobEffectInstance> IS_NOT_BENEFICIAL = (e) -> !e.getEffect().isBeneficial();
    static Predicate<MobEffectInstance> IS_HARMFUL = (e) -> e.getEffect().getCategory() == MobEffectCategory.HARMFUL;
    static Predicate<MobEffectInstance> IS_NEUTRAL = (e) -> e.getEffect().getCategory() == MobEffectCategory.NEUTRAL;


    static int getEffectLevel(LivingEntity entity, MobEffect effect) {
        MobEffectInstance instance = entity.getEffect(effect);
        if (instance == null) {
            return 0;
        }
        return instance.getAmplifier() + 1;
    }

}
