package org.auioc.mcmod.arnicalib.utils.game;

import org.auioc.mcmod.arnicalib.api.java.function.StringToStringFunction;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class MessageHelper {

    private final Component prefix;
    private final StringToStringFunction i18n;

    public MessageHelper(Component prefix, StringToStringFunction i18n) {
        this.prefix = prefix;
        this.i18n = i18n;
    }

    public MessageHelper(String modName, StringToStringFunction i18n) {
        this(TextUtils.literal("[" + modName + "] ").withStyle(ChatFormatting.AQUA), i18n);
    }

    public MessageHelper(StringToStringFunction i18n) {
        this(TextUtils.empty(), i18n);
    }


    public MutableComponent create(Component message, boolean withPrefix) {
        return withPrefix ? (TextUtils.empty()).append(this.prefix).append(message) : (TextUtils.empty()).append(message);
    }

    public MutableComponent create(String key, boolean withPrefix, Object... args) {
        return create(TextUtils.translatable(this.i18n.apply(key), args), withPrefix);
    }

    public MutableComponent create(String key, boolean withPrefix) {
        return create(key, withPrefix, TextUtils.NO_ARGS);
    }

}
