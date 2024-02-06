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

package org.auioc.mcmod.arnicalib.game.server;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

public class ServerUtils {

    private static final TpsRecord UNLOADED_LEVEL_TICK = new TpsRecord(-1.0D, -1.0D);

    public static MinecraftServer getServer() {
        return ServerLifecycleHooks.getCurrentServer();
    }

    public static TpsRecord calc(long[] tickArray) {
        if (tickArray == null) return UNLOADED_LEVEL_TICK;

        long sum = 0L;
        for (long tickTime : tickArray) sum += tickTime;

        double mspt = (sum / tickArray.length) * 1.0E-006D;
        double tps = Math.min(1000.0D / mspt, 20.0D);

        return new TpsRecord(mspt, tps);
    }

    public static TpsRecord getTpsMspt() {
        return calc(getServer().getTickTimesNanos());
    }

    public static TpsRecord getTpsMspt(ServerLevel level) {
        return calc(getServer().getTickTime(level.dimension()));
    }

}
