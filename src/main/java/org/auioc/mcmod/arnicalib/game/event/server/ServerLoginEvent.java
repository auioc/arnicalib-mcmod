package org.auioc.mcmod.arnicalib.game.event.server;

import net.minecraft.network.Connection;
import net.minecraft.network.protocol.handshake.ClientIntentionPacket;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;

public class ServerLoginEvent extends Event implements ICancellableEvent {

    private final ClientIntentionPacket packet;
    private final Connection manager;
    private String message;

    public ServerLoginEvent(ClientIntentionPacket packet, Connection manager) {
        super();
        this.packet = packet;
        this.manager = manager;
    }

    public ClientIntentionPacket getPacket() {
        return this.packet;
    }

    public Connection getNetworkManager() {
        return this.manager;
    }

    public String getMessage() {
        return this.message;
    }

    public void cancel() {
        this.message = "Disconnected because the ServerLoginEvent was cancelled.";
        this.setCanceled(true);
    }

    public void cancel(String message) {
        this.message = message;
        this.setCanceled(true);
    }

}
