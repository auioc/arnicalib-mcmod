package org.auioc.mcmod.arnicalib.server.event;

import static org.auioc.mcmod.arnicalib.ArnicaLib.LOGGER;
import org.apache.logging.log4j.Marker;
import org.auioc.mcmod.arnicalib.server.command.AHServerCommands;
import org.auioc.mcmod.arnicalib.server.event.impl.ServerLoginEvent;
import org.auioc.mcmod.arnicalib.utils.LogUtil;
import net.minecraft.network.ConnectionProtocol;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class ServerEventHandler {

    private static final Marker MARKER = LogUtil.getMarker("ServerHooks");


    @SubscribeEvent
    public static void registerCommands(final RegisterCommandsEvent event) {
        AHServerCommands.register(event.getDispatcher());
    }


    private static final Marker SERVER_LIST_PING_MARKER = LogUtil.getMarker("ServerListPing").addParents(MARKER);

    @SubscribeEvent
    public static void onServerLogin(final ServerLoginEvent event) {
        if (event.getPacket().getIntention() == ConnectionProtocol.STATUS) {
            LOGGER.info(
                SERVER_LIST_PING_MARKER,
                String.format("[%s] <-> InitialHandler has pinged", event.getNetworkManager().getRemoteAddress())
            );
        }
    }

}