package org.auioc.mcmod.arnicalib.server.command;

import static net.minecraft.commands.Commands.literal;
import java.util.List;
import org.auioc.mcmod.arnicalib.ArnicaLib;
import org.auioc.mcmod.arnicalib.common.command.impl.VersionCommand;
import org.auioc.mcmod.arnicalib.server.command.impl.RtpCommand;
import org.auioc.mcmod.arnicalib.utils.game.EnvironmentUtils;
import org.auioc.mcmod.arnicalib.utils.game.command.DynamicCommandHandler;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.CommandNode;
import net.minecraft.commands.CommandSourceStack;

public final class AHServerCommands {

    public static final CommandNode<CommandSourceStack> NODE = literal(ArnicaLib.MOD_ID).build();

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        VersionCommand.addVersionNode(NODE, ArnicaLib.class);
        NODE.addChild(RtpCommand.NODE);
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

    /**
     * @since 4.1.0
     * @deprecated Use {@link #getAHNode} instead
     */
    @Deprecated(since = "5.1.3", forRemoval = true)
    public static CommandNode<CommandSourceStack> getRootNode(CommandDispatcher<CommandSourceStack> dispatcher) {
        return getAHNode(dispatcher);
    }

    private static void addTestNode(CommandNode<CommandSourceStack> node) {
        node.addChild(
            literal("test")
                .executes(
                    (ctx) -> DynamicCommandHandler.run(
                        "org.auioc.mcmod.arnicalib.server.command.TestCommandHandler",
                        "run",
                        ctx
                    )
                )
                .build()
        );
    }

}
