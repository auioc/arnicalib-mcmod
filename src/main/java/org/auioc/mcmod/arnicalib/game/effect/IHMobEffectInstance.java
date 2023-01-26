package org.auioc.mcmod.arnicalib.game.effect;

import net.minecraft.world.effect.MobEffectInstance;

public interface IHMobEffectInstance {

    MobEffectInstance getHiddenEffect();

    void setDuration(int duration);

    void setAmplifier(int amplifier);

    void setAmbient(boolean ambient);

    void setVisible(boolean visible);

    void setShowIcon(boolean showIcon);

}
