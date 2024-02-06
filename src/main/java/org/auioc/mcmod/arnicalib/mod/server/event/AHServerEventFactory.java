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

import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.handshake.ClientIntentionPacket;
import net.minecraft.network.protocol.login.ClientboundLoginDisconnectPacket;
import net.neoforged.neoforge.common.NeoForge;
import org.apache.logging.log4j.Marker;
import org.auioc.mcmod.arnicalib.base.log.LogUtil;
import org.auioc.mcmod.arnicalib.game.event.server.ServerLoginEvent;
import org.auioc.mcmod.arnicalib.mod.mixin.server.MixinServerHandshakePacketListenerImpl;

import static org.auioc.mcmod.arnicalib.ArnicaLib.LOGGER;

public final class AHServerEventFactory {

    private static final Marker MARKER = LogUtil.getMarker("ServerHooks");

    // Return true if the event was Cancelable cancelled

    /**
     * @see MixinServerHandshakePacketListenerImpl#handleServerLogin
     */
    public static boolean onServerLogin(final ClientIntentionPacket packet, final Connection connection) {
        var event = new ServerLoginEvent(packet, connection);
        boolean cancelled = NeoForge.EVENT_BUS.post(event).isCanceled();
        if (cancelled) {
            var message = Component.literal(event.getMessage());
            connection.send(new ClientboundLoginDisconnectPacket(message));
            connection.disconnect(message);
            LOGGER.info(
                LogUtil.getMarker("ServerLogin").addParents(MARKER),
                String.format("Disconnecting %s connection attempt from %s: %s",
                    event.getPacket().intention(), event.getNetworkManager().getRemoteAddress(), event.getMessage()
                )
            );
            return true;
        }
        return false;
    }

}
