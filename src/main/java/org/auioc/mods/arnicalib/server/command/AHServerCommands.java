package org.auioc.mods.arnicalib.server.command;

import static net.minecraft.commands.Commands.literal;
import java.util.List;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.CommandNode;
import org.auioc.mods.arnicalib.ArnicaLib;
import org.auioc.mods.arnicalib.common.command.impl.VersionCommand;
import net.minecraft.commands.CommandSourceStack;

public final class AHServerCommands {

    public static final CommandNode<CommandSourceStack> NODE = literal(ArnicaLib.MOD_ID).build();

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        VersionCommand.addVersionNode(NODE, ArnicaLib.class);
        NODE.addChild(literal("test").executes((ctx) -> {
            return Command.SINGLE_SUCCESS;
        }).build());

        getRootNode(dispatcher).addChild(NODE);
    }

    public static CommandNode<CommandSourceStack> getRootNode(CommandDispatcher<CommandSourceStack> dispatcher) {
        CommandNode<CommandSourceStack> node = dispatcher.findNode(List.of("ah"));
        if (node == null) {
            node = dispatcher.register(literal("ah"));
        }
        return node;
    }

}
