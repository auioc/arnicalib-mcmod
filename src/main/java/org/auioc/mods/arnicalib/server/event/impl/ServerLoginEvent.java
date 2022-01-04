package org.auioc.mods.arnicalib.server.event.impl;

import net.minecraft.network.Connection;
import net.minecraft.network.protocol.handshake.ClientIntentionPacket;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

@Cancelable
public class ServerLoginEvent extends Event {

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

    @Deprecated
    public Connection getManager() {
        return this.manager;
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
