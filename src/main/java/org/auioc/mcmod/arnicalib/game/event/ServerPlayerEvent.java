package org.auioc.mcmod.arnicalib.game.event;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;

public abstract class ServerPlayerEvent extends PlayerEvent {

    private final ServerPlayer serverPlayer;

    public ServerPlayerEvent(ServerPlayer serverPlayer) {
        super(serverPlayer);
        this.serverPlayer = serverPlayer;
    }

    @Override
    @Deprecated(since = "5.6.4", forRemoval = true)
    public ServerPlayer getPlayer() {
        return serverPlayer;
    }

    public ServerPlayer getServerPlayer() {
        return serverPlayer;
    }

}
