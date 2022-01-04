package org.auioc.mods.arnicalib.utils.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
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



    static MobEffectInstance getMobEffectInstance(MobEffect effect, int duration, int amplifier, boolean ambient, boolean visible, boolean showIcon) {
        return new MobEffectInstance(effect, duration, amplifier, ambient, visible, showIcon);
    }

    @Nullable
    static MobEffectInstance getMobEffectInstance(ResourceLocation id, int duration, int amplifier, boolean ambient, boolean visible, boolean showIcon) {
        MobEffect effect = getEffect(id);
        if (effect != null) {
            return getMobEffectInstance(effect, duration, amplifier, ambient, visible, showIcon);
        }
        return null;
    }

    @Nullable
    static MobEffectInstance getMobEffectInstance(String id, int duration, int amplifier, boolean ambient, boolean visible, boolean showIcon) {
        return getMobEffectInstance(new ResourceLocation(id), duration, amplifier, ambient, visible, showIcon);
    }


    @Nullable
    static MobEffectInstance getMobEffectInstance(CompoundTag effect_nbt) {
        if (effect_nbt.contains("id", 8) && effect_nbt.contains("duration", 3) && effect_nbt.contains("amplifier", 3)) {
            return getMobEffectInstance(effect_nbt.getString("id"), effect_nbt.getInt("duration"), effect_nbt.getInt("amplifier"), true, true, true);
        }
        return null;
    }



    static void addEffect(LivingEntity entity, int id, int duration, int amplifier) {
        entity.addEffect(new MobEffectInstance(getEffect(id), duration, amplifier, true, true, true));
    }

    static boolean addEffect(LivingEntity entity, String id, int duration, int amplifier) {
        MobEffectInstance effect = getMobEffectInstance(id, duration, amplifier, true, true, true);
        if (effect != null) {
            return entity.addEffect(effect);
        }
        return false;
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
