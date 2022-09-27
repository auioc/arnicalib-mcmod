package org.auioc.mcmod.arnicalib.game.effect;

import java.util.ArrayList;
import java.util.function.Predicate;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

public interface EffectUtils {

    static void removeEffect(LivingEntity entity, Predicate<MobEffectInstance> predicate) {
        var toRemove = new ArrayList<MobEffect>();

        entity.getActiveEffects().forEach((instance) -> {
            if (predicate.test(instance)) toRemove.add(instance.getEffect());
        });

        toRemove.forEach((effect) -> entity.removeEffect(effect));
    }

    static int getEffectLevel(LivingEntity entity, MobEffect effect) {
        var instance = entity.getEffect(effect);
        return (instance == null) ? 0 : instance.getAmplifier() + 1;
    }

    static MobEffectInstance makeIncurable(MobEffectInstance instance) {
        instance.setCurativeItems(new ArrayList<ItemStack>());
        return instance;
    }


    static void setDuration(MobEffectInstance instance, int duration) {
        ((IHMobEffectInstance) instance).setDuration(duration);
    }

    static void setAmplifier(MobEffectInstance instance, int amplifier) {
        ((IHMobEffectInstance) instance).setAmplifier(amplifier);
    }

    @Deprecated
    static void setDurationReflection(MobEffectInstance instance, int duration) {
        ObfuscationReflectionHelper.setPrivateValue(MobEffectInstance.class, instance, duration, "f_19503_");
    }

    @Deprecated
    static void setAmplifierReflection(MobEffectInstance instance, int amplifier) {
        ObfuscationReflectionHelper.setPrivateValue(MobEffectInstance.class, instance, amplifier, "f_19504_");
    }

}
