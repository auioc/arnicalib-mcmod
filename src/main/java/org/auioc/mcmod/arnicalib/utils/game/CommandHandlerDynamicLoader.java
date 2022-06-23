package org.auioc.mcmod.arnicalib.utils.game;

import static org.auioc.mcmod.arnicalib.ArnicaLib.LOGGER;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.logging.log4j.Marker;
import org.auioc.mcmod.arnicalib.ArnicaLib;
import org.auioc.mcmod.arnicalib.api.game.command.ExceptionCommandExceptionType;
import org.auioc.mcmod.arnicalib.utils.LogUtil;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;

public class CommandHandlerDynamicLoader {

    private static final Marker MARKER = LogUtil.getMarker(CommandHandlerDynamicLoader.class);

    private static final ExceptionCommandExceptionType CLASS_NOT_FOUND_ERROR = ce("class_not_found");
    private static final ExceptionCommandExceptionType GET_RUN_METHOD_ERROR = ce("get_method");
    private static final ExceptionCommandExceptionType INVOKE_RUN_METHOD_ERROR = ce("invoke_method");
    private static final ExceptionCommandExceptionType NOT_CSE_THROWN_ERROR = ce("not_cse_thrown");

    public static int run(String className, CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        Class<?> clazz;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw CLASS_NOT_FOUND_ERROR.create(e);
        }

        Method runMethod;
        try {
            runMethod = clazz.getMethod("run", CommandContext.class);
        } catch (NoSuchMethodException | SecurityException e) {
            throw GET_RUN_METHOD_ERROR.create(e, "Failed to get \"run\" method");
        }

        try {
            runMethod.invoke(null, ctx);
        } catch (IllegalAccessException | IllegalArgumentException e) {
            throw INVOKE_RUN_METHOD_ERROR.create(e, "Failed to invoke \"run\" method");
        } catch (InvocationTargetException e) {
            if (e.getTargetException() instanceof CommandSyntaxException cse) {
                LOGGER.warn(MARKER, "Command handler " + runMethod.getDeclaringClass().getName() + "#" + runMethod.getName() + " throws a CommandSyntaxException");
                throw cse;
            }
            throw NOT_CSE_THROWN_ERROR.create((Exception) e.getTargetException(), "Command handler throws an exception that is not a CommandSyntaxException");
        }

        return Command.SINGLE_SUCCESS;
    }


    private static ExceptionCommandExceptionType ce(String key) {
        return new ExceptionCommandExceptionType(
            (e) -> TextUtils.I18nText(
                ArnicaLib.i18n("command.handler_dynamic_loader.failure." + key),
                e.getClass().getSimpleName() + ": " + e.getMessage()
            ),
            LOGGER, MARKER
        );
    }

}
