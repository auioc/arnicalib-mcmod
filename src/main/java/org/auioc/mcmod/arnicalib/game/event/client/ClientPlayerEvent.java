package org.auioc.mcmod.arnicalib.game.event.client;

import net.minecraft.client.player.LocalPlayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@OnlyIn(Dist.CLIENT)
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
