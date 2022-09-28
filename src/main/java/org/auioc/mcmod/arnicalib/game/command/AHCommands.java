package org.auioc.mcmod.arnicalib.game.command;

import org.auioc.mcmod.arnicalib.mod.client.command.AHClientCommands;
import org.auioc.mcmod.arnicalib.server.command.AHServerCommands;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.CommandNode;
import net.minecraft.commands.CommandSourceStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public final class AHCommands {

    public static CommandNode<CommandSourceStack> getServerNode(CommandDispatcher<CommandSourceStack> dispatcher) {
        return AHServerCommands.getAHNode(dispatcher);
    }

    @OnlyIn(Dist.CLIENT)
    public static CommandNode<CommandSourceStack> getClientNode(CommandDispatcher<CommandSourceStack> dispatcher) {
        return AHClientCommands.getAHNode(dispatcher);
    }

}
