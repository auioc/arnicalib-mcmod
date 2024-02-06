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
