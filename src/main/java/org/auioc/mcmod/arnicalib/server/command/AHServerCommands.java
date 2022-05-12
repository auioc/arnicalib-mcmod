package org.auioc.mcmod.arnicalib.server.command;

import static net.minecraft.commands.Commands.literal;
import java.util.List;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.CommandNode;
import org.auioc.mcmod.arnicalib.ArnicaLib;
import org.auioc.mcmod.arnicalib.common.command.impl.VersionCommand;
import net.minecraft.commands.CommandSourceStack;

public final class AHServerCommands {

    public static final CommandNode<CommandSourceStack> NODE = literal(ArnicaLib.MOD_ID).build();

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        VersionCommand.addVersionNode(NODE, ArnicaLib.class);
        NODE.addChild(literal("test").executes((ctx) -> {
            return Command.SINGLE_SUCCESS;
        }).build());

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

}
