package org.auioc.mcmod.arnicalib.server.event.impl;

import java.util.List;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Cancelable;

@Cancelable
public class LivingEatAddEffectEvent extends LivingEvent {

    private final ItemStack food;
    private final List<MobEffectInstance> effects;

    public LivingEatAddEffectEvent(LivingEntity entity, ItemStack food, List<MobEffectInstance> effects) {
        super(entity);
        this.food = food;
        this.effects = effects;
    }

    public ItemStack getFood() {
        return food;
    }

    public List<MobEffectInstance> getEffects() {
        return effects;
    }

}
