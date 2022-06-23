package org.auioc.mcmod.arnicalib.server.command;

import static net.minecraft.commands.Commands.literal;
import static org.auioc.mcmod.arnicalib.ArnicaLib.LOGGER;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import org.apache.logging.log4j.Marker;
import org.auioc.mcmod.arnicalib.ArnicaLib;
import org.auioc.mcmod.arnicalib.common.command.impl.VersionCommand;
import org.auioc.mcmod.arnicalib.utils.LogUtil;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.CommandNode;
import net.minecraft.commands.CommandSourceStack;

public final class AHServerCommands {

    public static final CommandNode<CommandSourceStack> NODE = literal(ArnicaLib.MOD_ID).build();

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        VersionCommand.addVersionNode(NODE, ArnicaLib.class);
        NODE.addChild(TestCommand.TEST_NODE);

        getAHNode(dispatcher).addChild(NODE);
    }

    public static CommandNode<CommandSourceStack> getAHNode(CommandDispatcher<CommandSourceStack> dispatcher) {
        CommandNode<CommandSourceStack> node = dispatcher.findNode(List.of("ah"));
        if (node == null) {
            node = dispatcher.register(literal("ah"));
        }
        return node;
    }

    /**
     * @since 4.1.0
     * @deprecated Use {@link #getAHNode} instead
     */
    @Deprecated(since = "5.1.3", forRemoval = true)
    public static CommandNode<CommandSourceStack> getRootNode(CommandDispatcher<CommandSourceStack> dispatcher) {
        return getAHNode(dispatcher);
    }

    private class TestCommand {

        private static final Marker MARKER = LogUtil.getMarker(TestCommand.class);

        private static final String CLASS_NAME = "org.auioc.mcmod.arnicalib.server.command.TestCommandHandler";

        public static final CommandNode<CommandSourceStack> TEST_NODE = literal("test")
            .executes((ctx) -> {
                try {
                    Class<?> c = Class.forName(CLASS_NAME);
                    Method m = c.getMethod("run", CommandContext.class);
                    m.invoke(null, ctx);
                } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException e) {
                    rethrow(e, "Could not load test command");
                } catch (InvocationTargetException e) {
                    if (e.getTargetException() instanceof CommandSyntaxException) {
                        throw (CommandSyntaxException) e.getTargetException();
                    }
                    rethrow(e, "Test command executor throws an exception that is not a CommandSyntaxException");
                }
                return Command.SINGLE_SUCCESS;
            }).build();

        private static void rethrow(Exception e, String message) {
            LOGGER.error(MARKER, message, e);
            throw new RuntimeException(e);
        }

    }

}
