package org.auioc.mcmod.arnicalib.mixin.common;

import java.util.Set;
import org.auioc.mcmod.arnicalib.game.mixin.common.IMixinArrow;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.alchemy.Potion;

@Mixin(value = Arrow.class)
public class MixinArrow implements IMixinArrow {

    @Shadow
    private Potion potion;

    @Shadow
    @Final
    private Set<MobEffectInstance> effects;

    @Override
    public Set<MobEffectInstance> getEffects() {
        return this.effects;
    }

    @Override
    public Potion getPotion() {
        return this.potion;
    }

    @Override
    public void setPotion(Potion potion) {
        this.potion = potion;
    }

}
