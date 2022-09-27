package org.auioc.mcmod.arnicalib.game.entity;

import java.util.Set;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;

public interface ITippedArrow {

    Set<MobEffectInstance> getEffects();

    Potion getPotion();

    void setPotion(Potion potion);

}
