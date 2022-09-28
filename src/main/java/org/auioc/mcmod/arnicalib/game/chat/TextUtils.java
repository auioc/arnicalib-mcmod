package org.auioc.mcmod.arnicalib.game.chat;

import java.util.List;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;

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

}
