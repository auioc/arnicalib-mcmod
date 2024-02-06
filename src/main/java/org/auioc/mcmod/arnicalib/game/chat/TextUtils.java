package org.auioc.mcmod.arnicalib.game.chat;

import com.google.gson.JsonElement;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.JsonOps;
import net.minecraft.network.chat.*;
import org.apache.logging.log4j.Marker;
import org.auioc.mcmod.arnicalib.base.log.LogUtil;

import javax.annotation.Nullable;
import java.util.List;

import static org.auioc.mcmod.arnicalib.ArnicaLib.LOGGER;

public class TextUtils {

    private static final Marker MARKER = LogUtil.getMarker(TextUtils.class);

    public static final Object[] NO_ARGS = new Object[0];

    /**
     * @deprecated Use {@link net.minecraft.network.chat.Component#empty()} instead
     */
    @Deprecated(since = "6.0.0", forRemoval = true)
    public static MutableComponent empty() {
        return Component.empty();

    }

    /**
     * @deprecated Use {@link net.minecraft.network.chat.Component#literal(String)} instead
     */
    @Deprecated(since = "6.0.0", forRemoval = true)
    public static MutableComponent literal(String text) {
        return Component.literal(text);
    }

    /**
     * @deprecated Use {@link net.minecraft.network.chat.Component#translatable(String)} instead
     */
    @Deprecated(since = "6.0.0", forRemoval = true)
    public static MutableComponent translatable(String key) {
        return Component.translatable(key);
    }

    /**
     * @deprecated Use {@link net.minecraft.network.chat.Component#translatable(String, Object...)} instead
     */
    @Deprecated(since = "6.0.0", forRemoval = true)
    public static MutableComponent translatable(String key, Object... arguments) {
        return Component.translatable(key, arguments);
    }


    public static Style copyable(Style style, String c) {
        return style.withClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, c)).withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, translatable("chat.copy.click")));
    }

    public static MutableComponent copyable(Component c) {
        return TextUtils.empty().append(c).withStyle((s) -> copyable(s, c.getString()));
    }

    public static MutableComponent copyable(String c) {
        return copyable(TextUtils.literal(c));
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

    @Nullable
    public static Style deserializeStyle(JsonElement json) {
        return Style.Serializer.CODEC
            .parse(new Dynamic<>(JsonOps.INSTANCE, json))
            .getOrThrow(false, (error) -> LOGGER.error(MARKER, error));
    }

}
