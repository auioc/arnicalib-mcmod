package org.auioc.mcmod.arnicalib.game.event.server;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;

public abstract class ServerPlayerEvent extends PlayerEvent {

    private final ServerPlayer serverPlayer;

    public ServerPlayerEvent(ServerPlayer serverPlayer) {
        super(serverPlayer);
        this.serverPlayer = serverPlayer;
    }

    public ServerPlayer getServerPlayer() {
        return serverPlayer;
    }

}
