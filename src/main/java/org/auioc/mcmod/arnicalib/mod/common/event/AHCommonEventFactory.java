/*
 * Copyright (C) 2024 AUIOC.ORG
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

package org.auioc.mcmod.arnicalib.mod.common.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.NeoForge;
import org.auioc.mcmod.arnicalib.game.event.common.PistonPushableEvent;

public class AHCommonEventFactory {

    private static final IEventBus BUS = NeoForge.EVENT_BUS;

    /**
     * @see org.auioc.mcmod.arnicalib.mod.mixin.common.MixinPistonBaseBlock#isPushable
     */
    public static boolean onPistonCheckPushable(BlockState blockState, Level level, BlockPos blockPos, Direction movement, boolean allowDestroy, Direction facing) {
        return BUS.post(new PistonPushableEvent(blockState, level, blockPos, movement, allowDestroy, facing)).isCanceled();
    }

}
