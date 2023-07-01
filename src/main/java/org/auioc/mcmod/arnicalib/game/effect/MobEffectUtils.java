package org.auioc.mcmod.arnicalib.game.effect;

import java.util.ArrayList;
import java.util.function.Predicate;
import net.minecraft.network.chat.Component;
import net.minecraft.util.StringUtil;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class MobEffectUtils {

    public static void remove(LivingEntity entity, Predicate<MobEffectInstance> predicate) {
        var toRemove = new ArrayList<MobEffect>();

        entity.getActiveEffects().forEach(
            (instance) -> { if (predicate.test(instance)) toRemove.add(instance.getEffect()); }
        );

        toRemove.forEach((effect) -> entity.removeEffect(effect));
    }

    public static int getLevel(LivingEntity entity, MobEffect effect) {
        var instance = entity.getEffect(effect);
        return (instance == null) ? 0 : instance.getAmplifier() + 1;
    }

    public static MobEffectInstance makeIncurable(MobEffectInstance instance) {
        instance.setCurativeItems(new ArrayList<ItemStack>());
        return instance;
    }


    public static void setDuration(MobEffectInstance instance, int duration) {
        ((IHMobEffectInstance) instance).setDuration(duration);
    }

    public static void setAmplifier(MobEffectInstance instance, int amplifier) {
        ((IHMobEffectInstance) instance).setAmplifier(amplifier);
    }

    public static Component getDisplayString(MobEffect effect, int amplifier, int duration) {
        return Component.empty()
            .append(effect.getDisplayName())
            .append(" ")
            .append(Component.translatable("enchantment.level." + (amplifier + 1)))
            .append(" ")
            .append(StringUtil.formatTickDuration(duration));
    }

    public static Component getDisplayString(MobEffectInstance instance) {
        return getDisplayString(instance.getEffect(), instance.getAmplifier(), instance.getDuration());
    }

}
