package org.auioc.mods.arnicalib.common.network;

import org.auioc.mods.arnicalib.ArnicaLib;
import org.auioc.mods.arnicalib.api.game.network.HPacketHandler;
import org.auioc.mods.arnicalib.api.game.network.IHPacket;
import net.minecraft.server.level.ServerPlayer;

public final class AHPacketHandler {

    private static final String PROTOCOL_VERSION = Integer.toString(1);
    private static HPacketHandler HANDLER;

    public static void init() {
        HANDLER = new HPacketHandler(ArnicaLib.id("main"), PROTOCOL_VERSION);
    }

    public static <MSG extends IHPacket> void sendToServer(MSG msg) {
        HANDLER.sendToServer(msg);
    }

    public static <MSG extends IHPacket> void sendToClient(ServerPlayer player, MSG msg) {
        HANDLER.sendToClient(player, msg);
    }

}
