package org.auioc.mods.arnicalib.server.command;

import static net.minecraft.commands.Commands.literal;
import java.util.List;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.CommandNode;
import org.auioc.mods.arnicalib.ArnicaLib;
import org.auioc.mods.arnicalib.server.command.impl.VersionCommand;
import net.minecraft.commands.CommandSourceStack;

public class ServerCommandRegistry {

    public static CommandNode<CommandSourceStack> getRootNode(CommandDispatcher<CommandSourceStack> dispatcher) {
        CommandNode<CommandSourceStack> node = dispatcher.findNode(List.of("ah"));
        if (node == null) {
            node = dispatcher.register(literal("ah"));
        }
        return node;
    }

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        getRootNode(dispatcher).addChild(
            literal(ArnicaLib.MOD_ID).executes((ctx) -> VersionCommand.getModVersion(ctx, ArnicaLib.MAIN_VERSION, ArnicaLib.FULL_VERSION, ArnicaLib.MOD_NAME))
                .build()
        );
    }

}
