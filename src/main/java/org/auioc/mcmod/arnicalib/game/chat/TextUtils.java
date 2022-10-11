package org.auioc.mcmod.arnicalib.game.chat;

import java.util.List;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;

public class TextUtils {

    public static final Object[] NO_ARGS = new Object[0];

    public static TextComponent empty() {
        return new TextComponent("");
    }

    public static TextComponent literal(String text) {
        return new TextComponent(text);
    }

    public static TranslatableComponent translatable(String key) {
        return new TranslatableComponent(key);
    }

    public static TranslatableComponent translatable(String key, Object... arguments) {
        return new TranslatableComponent(key, arguments);
    }


    public static MutableComponent join(List<Component> texts, Component separator) {
        var r = empty();
        if (texts.isEmpty()) return r;

        int s = texts.size();
        for (int i = 0, l = s - 1; i < l; ++i) {
            r.append(texts.get(i)).append(separator);
        }
        r.append(texts.get(s - 1));

        return r;
    }

    public static MutableComponent join(List<Component> texts) {
        return join(texts, literal(", "));
    }

}
