package org.auioc.mcmod.arnicalib.game.chat;

import java.util.List;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;

public interface TextUtils {

    Object[] NO_ARGS = new Object[0];

    static TextComponent empty() {
        return new TextComponent("");
    }

    static TextComponent literal(String text) {
        return new TextComponent(text);
    }

    static TranslatableComponent translatable(String key) {
        return new TranslatableComponent(key);
    }

    static TranslatableComponent translatable(String key, Object... arguments) {
        return new TranslatableComponent(key, arguments);
    }


    static MutableComponent join(List<Component> texts, Component separator) {
        var r = empty();
        if (texts.isEmpty()) return r;

        int s = texts.size();
        for (int i = 0, l = s - 1; i < l; ++i) {
            r.append(texts.get(i)).append(separator);
        }
        r.append(texts.get(s - 1));

        return r;
    }

    static MutableComponent join(List<Component> texts) {
        return join(texts, literal(", "));
    }


    /* ============================================================================================================== */
    // #region Deprecated

    @Deprecated(since = "5.4.1", forRemoval = true)
    static void chat(Player player, String message) {
        player.sendMessage(new TextComponent(message), Util.NIL_UUID);
    }

    @Deprecated(since = "5.4.1", forRemoval = true)
    static void chat(Player player, Component message) {
        player.sendMessage(message, Util.NIL_UUID);
    }

    @Deprecated(since = "5.4.1", forRemoval = true)
    static TextComponent emptyText() {
        return new TextComponent("");
    }

    @Deprecated(since = "5.4.1", forRemoval = true)
    static TextComponent EmptyText() {
        return new TextComponent("");
    }

    @Deprecated(since = "5.4.1", forRemoval = true)
    static TextComponent getStringText(String text) {
        return new TextComponent(text);
    }

    @Deprecated(since = "5.4.1", forRemoval = true)
    static TextComponent StringText(String text) {
        return new TextComponent(text);
    }

    @Deprecated(since = "5.4.1", forRemoval = true)
    static TranslatableComponent getI18nText(String key) {
        return new TranslatableComponent(key);
    }

    @Deprecated(since = "5.4.1", forRemoval = true)
    static TranslatableComponent I18nText(String key) {
        return new TranslatableComponent(key);
    }

    @Deprecated(since = "5.4.1", forRemoval = true)
    static TranslatableComponent getI18nText(String key, Object... arguments) {
        return new TranslatableComponent(key, arguments);
    }

    @Deprecated(since = "5.4.1", forRemoval = true)
    static TranslatableComponent I18nText(String key, Object... arguments) {
        return new TranslatableComponent(key, arguments);
    }

    // #endregion Deprecated

}