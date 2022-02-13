package org.auioc.mods.arnicalib.api.game.event;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;

public class ServerPlayerEvent extends LivingEvent {

    private final ServerPlayer player;

    public ServerPlayerEvent(ServerPlayer player) {
        super(player);
        this.player = player;
    }

    public ServerPlayer getPlayer() {
        return this.player;
    }

}
