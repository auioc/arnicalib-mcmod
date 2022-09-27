package org.auioc.mcmod.arnicalib.utils.game;

import org.auioc.mcmod.arnicalib.game.chat.TextUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

/**
 * @deprecated Use {@link TextUtils} instead
 */
@Deprecated(since = "3.1.3", forRemoval = true)
public interface I18nUtils {
    static Component getTranslatedText(String key) {
        return new TranslatableComponent(key);
    }

    static Component getTranslatedText(String key, Object... arguments) {
        return new TranslatableComponent(key, arguments);
    }
}
