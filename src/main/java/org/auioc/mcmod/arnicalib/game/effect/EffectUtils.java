package org.auioc.mcmod.arnicalib.game.effect;

import java.util.function.Predicate;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

@Deprecated(since = "5.6.2", forRemoval = true)
public interface EffectUtils {

    static void removeEffect(LivingEntity entity, Predicate<MobEffectInstance> predicate) {
        MobEffectUtils.remove(entity, predicate);
    }

    static int getEffectLevel(LivingEntity entity, MobEffect effect) {
        return MobEffectUtils.getLevel(entity, effect);
    }

    static MobEffectInstance makeIncurable(MobEffectInstance instance) {
        return MobEffectUtils.makeIncurable(instance);
    }


    static void setDuration(MobEffectInstance instance, int duration) {
        MobEffectUtils.setDuration(instance, duration);
    }

    static void setAmplifier(MobEffectInstance instance, int amplifier) {
        MobEffectUtils.setAmplifier(instance, amplifier);
    }


    static void setDurationReflection(MobEffectInstance instance, int duration) {
        MobEffectUtils.setDurationReflection(instance, duration);
    }

    static void setAmplifierReflection(MobEffectInstance instance, int amplifier) {
        MobEffectUtils.setAmplifierReflection(instance, amplifier);
    }

}
