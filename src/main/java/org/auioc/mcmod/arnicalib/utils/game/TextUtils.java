package org.auioc.mcmod.arnicalib.utils.game;

import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;

public interface TextUtils {

    Object[] NO_ARGS = new Object[0];

    static void chat(Player player, String message) {
        player.sendMessage(new TextComponent(message), Util.NIL_UUID);
    }

    static void chat(Player player, Component message) {
        player.sendMessage(message, Util.NIL_UUID);
    }

    static TextComponent empty() {
        return new TextComponent("");
    }

    static TextComponent emptyText() {
        return new TextComponent("");
    }

    static TextComponent EmptyText() {
        return new TextComponent("");
    }

    static TextComponent getStringText(String text) {
        return new TextComponent(text);
    }

    static TextComponent StringText(String text) {
        return new TextComponent(text);
    }

    static TranslatableComponent getI18nText(String key) {
        return new TranslatableComponent(key);
    }

    static TranslatableComponent I18nText(String key) {
        return new TranslatableComponent(key);
    }

    static TranslatableComponent getI18nText(String key, Object... arguments) {
        return new TranslatableComponent(key, arguments);
    }

    static TranslatableComponent I18nText(String key, Object... arguments) {
        return new TranslatableComponent(key, arguments);
    }

}
