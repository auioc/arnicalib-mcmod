package org.auioc.mcmod.arnicalib.mod.mixin.common;

import java.util.Set;
import org.auioc.mcmod.arnicalib.game.entity.projectile.ITippedArrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.alchemy.Potion;

@Mixin(value = Arrow.class)
public interface MixinAccessorArrow extends ITippedArrow {

    @Accessor("effects")
    Set<MobEffectInstance> getEffects();

    @Accessor("potion")
    Potion getPotion();

    @Accessor("potion")
    void setPotion(Potion potion);

}
