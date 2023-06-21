package org.auioc.mcmod.arnicalib.game.chat;

import org.auioc.mcmod.arnicalib.base.function.StringUnaryOperator;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class MessageHelper {

    private final Component prefix;
    private final StringUnaryOperator i18n;

    public MessageHelper(Component prefix, StringUnaryOperator i18n) {
        this.prefix = prefix;
        this.i18n = i18n;
    }

    public MessageHelper(String modName, StringUnaryOperator i18n) {
        this(Component.literal("[" + modName + "] ").withStyle(ChatFormatting.AQUA), i18n);
    }


    public MessageHelper(StringUnaryOperator i18n) {
        this(Component.empty(), i18n);
    }


    public MutableComponent create(Component message, boolean withPrefix) {
        return withPrefix ? (Component.empty()).append(this.prefix).append(message) : (Component.empty()).append(message);
    }

    public MutableComponent create(String key, Object[] args, boolean withPrefix) {
        return create(Component.translatable(this.i18n.applyAsString(key), args), withPrefix);
    }

    public MutableComponent create(String key, boolean withPrefix) {
        return create(key, TextUtils.NO_ARGS, withPrefix);
    }

}
