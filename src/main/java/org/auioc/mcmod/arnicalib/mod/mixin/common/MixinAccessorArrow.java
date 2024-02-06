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
