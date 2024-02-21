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

package org.auioc.mcmod.arnicalib.game.entity.player;

import io.netty.channel.local.LocalAddress;
import net.minecraft.network.Connection;
import net.minecraft.server.level.ServerPlayer;

import javax.annotation.Nullable;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class AddrUtils {

    @Nullable
    public static String getIp(Connection connection) {
        SocketAddress addr = connection.getRemoteAddress();
        if (addr instanceof InetSocketAddress) {
            return ((InetSocketAddress) addr).getAddress().getHostAddress();
        } else if (addr instanceof LocalAddress) {
            return addr.toString();
        }
        return null;
    }

    @Nullable
    public static String getPlayerIp(ServerPlayer player) {
        return getIp(player.connection.connection);
    }


}
