package org.auioc.mcmod.arnicalib.game.event;

import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;

public abstract class ClientPlayerEvent extends PlayerEvent {

    private final LocalPlayer clientPlayer;

    public ClientPlayerEvent(LocalPlayer clientPlayer) {
        super(clientPlayer);
        this.clientPlayer = clientPlayer;
    }

    public LocalPlayer getClientPlayer() {
        return clientPlayer;
    }

}
