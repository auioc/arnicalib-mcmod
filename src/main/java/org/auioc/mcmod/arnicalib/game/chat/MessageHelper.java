package org.auioc.mcmod.arnicalib.game.chat;

import org.auioc.mcmod.arnicalib.base.function.StringUnaryOperator;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;

public class MessageHelper {

    private final Component prefix;
    private final StringUnaryOperator i18n;

    public MessageHelper(Component prefix, StringUnaryOperator i18n) {
        this.prefix = prefix;
        this.i18n = i18n;
    }

    public MessageHelper(String modName, StringUnaryOperator i18n) {
        this(TextUtils.literal("[" + modName + "] ").withStyle(ChatFormatting.AQUA), i18n);
    }


    public MessageHelper(StringUnaryOperator i18n) {
        this(TextUtils.empty(), i18n);
    }


    public MutableComponent create(Component message, boolean withPrefix) {
        return withPrefix ? (TextUtils.empty()).append(this.prefix).append(message) : (TextUtils.empty()).append(message);
    }

    public MutableComponent create(String key, Object[] args, boolean withPrefix) {
        return create(TextUtils.translatable(this.i18n.applyAsString(key), args), withPrefix);
    }

    public MutableComponent create(String key, boolean withPrefix) {
        return create(key, TextUtils.NO_ARGS, withPrefix);
    }


    public void send(ServerPlayer player, ChatType type, boolean withPrefix, Component message) {
        player.sendMessage(create(message, withPrefix), type, Util.NIL_UUID);
    }

    public void send(ServerPlayer player, ChatType type, boolean withPrefix, String key, Object... arg) {
        player.sendMessage(create(key, arg, withPrefix), type, Util.NIL_UUID);
    }


    public void sendSystemMessage(ServerPlayer player, Component message) {
        send(player, ChatType.SYSTEM, true, message);
    }

    public void sendChatMessage(ServerPlayer player, Component message) {
        send(player, ChatType.CHAT, true, message);
    }

    public void sendGameInfo(ServerPlayer player, Component message) {
        send(player, ChatType.GAME_INFO, false, message);
    }

    public void sendSystemMessage(ServerPlayer player, String key, Object... args) {
        send(player, ChatType.SYSTEM, true, key, args);
    }

    public void sendChatMessage(ServerPlayer player, String key, Object... args) {
        send(player, ChatType.CHAT, true, key, args);
    }

    public void sendGameInfo(ServerPlayer player, String key, Object... args) {
        send(player, ChatType.GAME_INFO, false, key, args);
    }

    public void sendSystemMessage(ServerPlayer player, String key) {
        sendSystemMessage(player, key, TextUtils.NO_ARGS);
    }

    public void sendChatMessage(ServerPlayer player, String key) {
        sendChatMessage(player, key, TextUtils.NO_ARGS);
    }

    public void sendGameInfo(ServerPlayer player, String key) {
        sendGameInfo(player, key, TextUtils.NO_ARGS);
    }

}
