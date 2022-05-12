package org.auioc.mcmod.arnicalib.client.command;

import static net.minecraft.commands.Commands.literal;
import java.util.List;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.CommandNode;
import net.minecraft.commands.CommandSourceStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public final class AHClientCommands {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        getAHNode(dispatcher);
    }

    public static CommandNode<CommandSourceStack> getAHNode(CommandDispatcher<CommandSourceStack> dispatcher) {
        CommandNode<CommandSourceStack> node = dispatcher.findNode(List.of("ah"));
        if (node == null) {
            node = dispatcher.register(literal("ah"));
        }
        return node;
    }

    /**
     * @since 5.0.2
     * @deprecated Use {@link #getAHNode} instead
     */
    @Deprecated(since = "5.1.3", forRemoval = true)
    public static CommandNode<CommandSourceStack> getRootNode(CommandDispatcher<CommandSourceStack> dispatcher) {
        return getAHNode(dispatcher);
    }

}
