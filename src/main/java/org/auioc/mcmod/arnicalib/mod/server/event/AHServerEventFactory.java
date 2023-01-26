package org.auioc.mcmod.arnicalib.mod.server.event;

import static org.auioc.mcmod.arnicalib.ArnicaLib.LOGGER;
import org.apache.logging.log4j.Marker;
import org.auioc.mcmod.arnicalib.base.log.LogUtil;
import org.auioc.mcmod.arnicalib.game.chat.TextUtils;
import org.auioc.mcmod.arnicalib.game.event.server.ServerLoginEvent;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.handshake.ClientIntentionPacket;
import net.minecraft.network.protocol.login.ClientboundLoginDisconnectPacket;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;

public final class AHServerEventFactory {

    private static final Marker MARKER = LogUtil.getMarker("ServerHooks");
    private static final IEventBus BUS = MinecraftForge.EVENT_BUS;

    // Return true if the event was Cancelable cancelled

    /**
     * @see org.auioc.mcmod.arnicalib.mod.mixin.server.MixinServerLifecycleHooks#handleServerLogin
     */
    public static boolean onServerLogin(final ClientIntentionPacket packet, final Connection manager) {
        ServerLoginEvent event = new ServerLoginEvent(packet, manager);
        boolean cancelled = BUS.post(event);
        if (cancelled) {
            var message = TextUtils.literal(event.getMessage());
            manager.send(new ClientboundLoginDisconnectPacket(message));
            manager.disconnect(message);
            LOGGER.info(
                LogUtil.getMarker("ServerLogin").addParents(MARKER),
                String.format("Disconnecting %s connection attempt from %s: %s", event.getPacket().getIntention(), event.getNetworkManager().getRemoteAddress(), event.getMessage())
            );
            return true;
        }
        return false;
    }

}
