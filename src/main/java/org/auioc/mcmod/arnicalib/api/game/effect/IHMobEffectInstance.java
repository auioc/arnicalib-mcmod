package org.auioc.mcmod.arnicalib.api.game.effect;

import javax.annotation.Nullable;
import net.minecraft.world.effect.MobEffectInstance;

public interface IHMobEffectInstance {

    @Nullable
    MobEffectInstance getHiddenEffect();

    void setDuration(int duration);

    void setAmplifier(int amplifier);

    void setAmbient(boolean ambient);

    void setVisible(boolean visible);

    void showIcon(boolean showIcon);

}
