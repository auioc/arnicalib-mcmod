/*
 * Copyright (C) 2022-2024 AUIOC.ORG
 *
 * This file is part of ArnicaLib, a mod made for Minecraft.
 *
 * ArnicaLib is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <https://www.gnu.org/licenses/>.
 */

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
