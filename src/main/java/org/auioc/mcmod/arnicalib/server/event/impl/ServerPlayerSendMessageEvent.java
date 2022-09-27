package org.auioc.mcmod.arnicalib.server.event.impl;

import java.util.UUID;
import org.auioc.mcmod.arnicalib.game.event.ServerPlayerEvent;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.eventbus.api.Cancelable;

@Cancelable
public class ServerPlayerSendMessageEvent extends ServerPlayerEvent {

    private final Component message;
    private final ChatType chatType;
    private final UUID uuid;

    public ServerPlayerSendMessageEvent(ServerPlayer player, Component message, ChatType chatType, UUID uuid) {
        super(player);
        this.message = message;
        this.chatType = chatType;
        this.uuid = uuid;
    }

    public Component getMessage() {
        return this.message;
    }

    public ChatType getChatType() {
        return this.chatType;
    }

    public UUID getUUID() {
        return this.uuid;
    }

}
