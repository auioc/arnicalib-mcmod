package org.auioc.mcmod.arnicalib.mod.mixin.common;

import javax.annotation.Nullable;
import org.auioc.mcmod.arnicalib.mod.mixinapi.common.IMixinMobEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.world.effect.MobEffectInstance;

@Mixin(value = MobEffectInstance.class)
public abstract class MixinMobEffectInstance implements IMixinMobEffectInstance {

    @Shadow
    @Nullable
    private MobEffectInstance hiddenEffect;
    @Shadow
    private int duration;
    @Shadow
    private int amplifier;
    @Shadow
    private boolean ambient;
    @Shadow
    private boolean visible;
    @Shadow
    private boolean showIcon;

    @Override
    @Nullable
    public MobEffectInstance getHiddenEffect() {
        return this.hiddenEffect;
    }

    @Override
    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public void setAmplifier(int amplifier) {
        this.amplifier = amplifier;
    }

    @Override
    public void setAmbient(boolean ambient) {
        this.ambient = ambient;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public void showIcon(boolean showIcon) {
        this.showIcon = showIcon;
    }

}
