package org.auioc.mcmod.arnicalib.game.event.client;

import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.PlayerEvent;

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
