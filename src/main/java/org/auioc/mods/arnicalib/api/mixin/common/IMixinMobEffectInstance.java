package org.auioc.mods.arnicalib.api.mixin.common;

import javax.annotation.Nullable;
import net.minecraft.world.effect.MobEffectInstance;

public interface IMixinMobEffectInstance {

    @Nullable
    MobEffectInstance getHiddenEffect();

}
