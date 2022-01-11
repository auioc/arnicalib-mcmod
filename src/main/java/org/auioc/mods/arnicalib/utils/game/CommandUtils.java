package org.auioc.mods.arnicalib.utils.game;

import static org.auioc.mods.arnicalib.ArnicaLib.i18n;
import static org.auioc.mods.arnicalib.utils.game.TextUtils.I18nText;
import java.lang.reflect.Field;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import org.auioc.mods.arnicalib.api.mixin.server.IMixinCommandSourceStack;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;

public interface CommandUtils {

    SimpleCommandExceptionType INTERNAL_ERROR = new SimpleCommandExceptionType(I18nText(i18n("command.failure.internal")));
    SimpleCommandExceptionType NOT_SERVER_ERROR = new SimpleCommandExceptionType(I18nText(i18n("command.failure.not_server")));
    SimpleCommandExceptionType NOT_DEDICATED_SERVER_ERROR = new SimpleCommandExceptionType(I18nText(i18n("command.failure.not_dedicated_server")));
    SimpleCommandExceptionType REFLECTION_ERROR = new SimpleCommandExceptionType(I18nText(i18n("command.failure.reflection")));

    /**
     * @param sourceStack
     * @return The real command source of the specified {@code CommandSourceStack}
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @since 4.0.0
     * @deprecated Use {@link IMixinCommandSourceStack} instead {@code ((IMixinCommandSourceStack)stack).getSource()}
     */
    @Deprecated(since = "4.1.5")
    static CommandSource getPrivateSource(CommandSourceStack sourceStack) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Field privateSourceField = CommandSourceStack.class.getDeclaredField("source");
        privateSourceField.setAccessible(true);
        return (CommandSource) privateSourceField.get(sourceStack);
    }

}
