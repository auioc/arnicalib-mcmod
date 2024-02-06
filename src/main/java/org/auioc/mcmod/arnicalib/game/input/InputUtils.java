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

package org.auioc.mcmod.arnicalib.game.input;

import net.minecraft.client.player.Input;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class InputUtils {

    public static float calculateImpulse(boolean a, boolean b, boolean isMovingSlowly) {
        return (a == b) ? 0.0F : ((a ? 1.0F : -1.0F) * (isMovingSlowly ? 0.3F : 1.0F));
    }

    public static void calculateImpulse(Input input, boolean isMovingSlowly) {
        input.forwardImpulse = calculateImpulse(input.up, input.down, isMovingSlowly);
        input.leftImpulse = calculateImpulse(input.left, input.right, isMovingSlowly);
    }

}
