package org.auioc.mcmod.arnicalib.mod.mixin.common;

import org.auioc.mcmod.arnicalib.game.effect.IHMobEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.world.effect.MobEffectInstance;

@Mixin(value = MobEffectInstance.class)
public interface MixinAccessorMobEffectInstance extends IHMobEffectInstance {

    @Accessor("hiddenEffect")
    MobEffectInstance getHiddenEffect();

    @Accessor("duration")
    void setDuration(int duration);

    @Accessor("amplifier")
    void setAmplifier(int amplifier);

    @Accessor("ambient")
    void setAmbient(boolean ambient);

    @Accessor("visible")
    void setVisible(boolean visible);

    @Accessor("showIcon")
    void setShowIcon(boolean showIcon);

}
