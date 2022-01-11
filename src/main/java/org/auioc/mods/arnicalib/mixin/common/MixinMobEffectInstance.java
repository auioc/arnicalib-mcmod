package org.auioc.mods.arnicalib.mixin.common;

import javax.annotation.Nullable;
import org.auioc.mods.arnicalib.api.mixin.common.IMixinMobEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.world.effect.MobEffectInstance;

@Mixin(value = MobEffectInstance.class)
public class MixinMobEffectInstance implements IMixinMobEffectInstance {

    @Shadow
    @Nullable
    private MobEffectInstance hiddenEffect;

    @Override
    @Nullable
    public MobEffectInstance getHiddenEffect() {
        return this.hiddenEffect;
    }

}
