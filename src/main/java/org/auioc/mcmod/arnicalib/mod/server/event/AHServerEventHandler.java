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

package org.auioc.mcmod.arnicalib.mod.server.event;

import net.minecraft.network.protocol.handshake.ClientIntent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import org.apache.logging.log4j.Marker;
import org.auioc.mcmod.arnicalib.base.log.LogUtil;
import org.auioc.mcmod.arnicalib.game.event.server.ServerLoginEvent;
import org.auioc.mcmod.arnicalib.mod.server.command.AHServerCommands;

import static org.auioc.mcmod.arnicalib.ArnicaLib.LOGGER;

public final class AHServerEventHandler {

    private static final Marker MARKER = LogUtil.getMarker("ServerHooks");


    @SubscribeEvent
    public static void registerCommands(final RegisterCommandsEvent event) {
        AHServerCommands.register(event.getDispatcher());
    }


    private static final Marker SLP_MARKER = LogUtil.getMarker("ServerListPing").addParents(MARKER);

    @SubscribeEvent
    public static void onServerLogin(final ServerLoginEvent event) {
        if (event.getPacket().intention() == ClientIntent.STATUS) {
            LOGGER.debug(
                SLP_MARKER,
                String.format("[%s] <-> InitialHandler has pinged", event.getNetworkManager().getRemoteAddress())
            );
        }
    }

}
