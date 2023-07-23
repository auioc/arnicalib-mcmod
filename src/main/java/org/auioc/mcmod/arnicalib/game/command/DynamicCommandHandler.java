package org.auioc.mcmod.arnicalib.game.command;

import static org.auioc.mcmod.arnicalib.ArnicaLib.LOGGER;
import java.lang.reflect.InvocationTargetException;
import org.apache.logging.log4j.Marker;
import org.auioc.mcmod.arnicalib.base.log.LogUtil;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;

public class DynamicCommandHandler {

    private static final Marker MARKER = LogUtil.getMarker(DynamicCommandHandler.class);

    @SuppressWarnings("unchecked")
    public static LiteralArgumentBuilder<CommandSourceStack> createBuilder(String className, String methodName, LiteralArgumentBuilder<CommandSourceStack> builder) {
        try {
            var method = Class.forName(className).getMethod(methodName, LiteralArgumentBuilder.class);
            return (LiteralArgumentBuilder<CommandSourceStack>) method.invoke(null, builder);
        } catch (Exception e) {
            LOGGER.error(MARKER, "Failed to create builder", e);
        }
        return builder;
    }

    // ============================================================================================================== //

    public static int run(String className, String methodName, CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        try {
            _run(className, methodName, ctx);
        } catch (Throwable e) {
            if (e instanceof CommandSyntaxException cse) {
                LOGGER.warn(MARKER, "Command handler " + className + " throws a CommandSyntaxException");
                throw cse;
            }
            throw CommandExceptions.LOGGABLE_INTERNAL_ERROR.create();
        }
        return Command.SINGLE_SUCCESS;
    }

    private static void _run(String className, String methodName, CommandContext<CommandSourceStack> ctx) throws Throwable {
        try {
            Class.forName(className)
                .getMethod(methodName, CommandContext.class)
                .invoke(null, ctx);
        } catch (ClassNotFoundException e) {
            rethrow(e, "Cannot load class");
        } catch (NoSuchMethodException | SecurityException e) {
            rethrow(e, "Cannot get method");
        } catch (IllegalAccessException | IllegalArgumentException e) {
            rethrow(e, "Cannot invoke method");
        } catch (InvocationTargetException e) {
            if (e.getTargetException() instanceof CommandSyntaxException cse) {
                throw cse;
            }
            rethrow(e.getTargetException(), "Command handler throws an exception that is not a CommandSyntaxException");
        }
    }

    // ============================================================================================================== //

    private static void rethrow(Throwable throwable, String message) throws Throwable {
        LOGGER.error(MARKER, message, throwable);
        throw throwable;
    }

}
