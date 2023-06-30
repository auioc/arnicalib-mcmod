package org.auioc.mcmod.arnicalib.game.command;

import org.auioc.mcmod.arnicalib.ArnicaLib;
import com.mojang.brigadier.exceptions.BuiltInExceptionProvider;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.network.chat.Component;

public class CommandExceptions {

    public static final BuiltInExceptionProvider BUILT_IN_EXCEPTIONS = CommandSyntaxException.BUILT_IN_EXCEPTIONS;

    public static final SimpleCommandExceptionType INTERNAL_ERROR = simple("command.failure.internal");
    public static final SimpleCommandExceptionType LOGGABLE_INTERNAL_ERROR = simple("command.failure.internal.loggable");
    public static final SimpleCommandExceptionType NOT_SERVER_ERROR = simple("command.failure.not_server");
    public static final SimpleCommandExceptionType NOT_DEDICATED_SERVER_ERROR = simple("command.failure.not_dedicated_server");

    private static SimpleCommandExceptionType simple(String key) {
        return new SimpleCommandExceptionType(Component.translatable(ArnicaLib.i18n("command.failure.internal")));
    }

}
