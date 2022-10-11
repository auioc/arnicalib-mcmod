package org.auioc.mcmod.arnicalib.mod.server.command;

import static net.minecraft.commands.Commands.literal;
import java.util.List;
import org.auioc.mcmod.arnicalib.ArnicaLib;
import org.auioc.mcmod.arnicalib.game.command.DynamicCommandHandler;
import org.auioc.mcmod.arnicalib.game.command.node.VersionCommand;
import org.auioc.mcmod.arnicalib.game.mod.EnvironmentUtils;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.CommandNode;
import net.minecraft.commands.CommandSourceStack;

public final class AHServerCommands {

    public static final CommandNode<CommandSourceStack> NODE = literal(ArnicaLib.MOD_ID).build();

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        VersionCommand.addVersionNode(NODE, ArnicaLib.class);
        if (EnvironmentUtils.IS_DEV) addTestNode(NODE);

        getAHNode(dispatcher).addChild(NODE);
    }

    public static CommandNode<CommandSourceStack> getAHNode(CommandDispatcher<CommandSourceStack> dispatcher) {
        CommandNode<CommandSourceStack> node = dispatcher.findNode(List.of("ah"));
        if (node == null) {
            node = dispatcher.register(literal("ah"));
        }
        return node;
    }

    private static void addTestNode(CommandNode<CommandSourceStack> node) {
        node.addChild(
            literal("test")
                .executes(
                    (ctx) -> DynamicCommandHandler.run(
                        "org.auioc.mcmod.arnicalib.mod.server.command.TestCommandHandler",
                        "run",
                        ctx
                    )
                )
                .build()
        );
    }

}
