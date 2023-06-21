package org.auioc.mcmod.arnicalib.game.entity.player;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import javax.annotation.Nullable;
import io.netty.channel.local.LocalAddress;
import net.minecraft.network.Connection;
import net.minecraft.server.level.ServerPlayer;

public class AddrUtils {

    @Nullable
    public static String getIp(Connection connection) {
        SocketAddress addr = connection.getRemoteAddress();
        if (addr instanceof InetSocketAddress) {
            return ((InetSocketAddress) addr).getAddress().getHostAddress();
        } else if (addr instanceof LocalAddress) {
            return ((LocalAddress) addr).toString();
        }
        return null;
    }

    @Nullable
    public static String getPlayerIp(ServerPlayer player) {
        return getIp(player.connection.connection);
    }


}
