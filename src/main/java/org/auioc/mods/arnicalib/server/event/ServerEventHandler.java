package org.auioc.mods.arnicalib.server.event;

import static org.auioc.mods.arnicalib.ArnicaLib.LOGGER;
import org.apache.logging.log4j.Marker;
import org.auioc.mods.arnicalib.server.command.ServerCommandRegistry;
import org.auioc.mods.arnicalib.server.event.impl.ServerLoginEvent;
import org.auioc.mods.arnicalib.utils.LogUtil;
import net.minecraft.network.ConnectionProtocol;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ServerEventHandler {

    private static final Marker marker = LogUtil.getMarker("ServerHooks");

    @SubscribeEvent
    public static void registerCommands(final RegisterCommandsEvent event) {
        ServerCommandRegistry.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onServerLogin(final ServerLoginEvent event) {
        ConnectionProtocol intention = event.getPacket().getIntention();
        if (intention == ConnectionProtocol.STATUS) {
            LOGGER.info(
                LogUtil.getMarker("ServerListPing").addParents(marker),
                String.format("[%s] <-> InitialHandler has pinged", event.getNetworkManager().getRemoteAddress())
            );
            return;
        }
    }

}
