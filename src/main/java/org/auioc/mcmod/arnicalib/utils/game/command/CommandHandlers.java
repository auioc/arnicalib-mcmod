package org.auioc.mcmod.arnicalib.utils.game.command;

import org.auioc.mcmod.arnicalib.game.command.BiCommandAction;
import org.auioc.mcmod.arnicalib.game.command.CommandAction;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

public class CommandHandlers {

    public static int playerSelf(CommandContext<CommandSourceStack> ctx, CommandAction.SingleSuccess<ServerPlayer> action) throws CommandSyntaxException {
        action.accept(ctx.getSource().getPlayerOrException());
        return Command.SINGLE_SUCCESS;
    }

    public static int playerSelfWithMainHand(CommandContext<CommandSourceStack> ctx, BiCommandAction.SingleSuccess<ServerPlayer, ItemStack> action) throws CommandSyntaxException {
        return playerSelf(ctx, (player) -> {
            if (!player.getMainHandItem().isEmpty()) action.accept(player, player.getMainHandItem());
        });
    }


    public static class MultiSuccess {

        public static int playerSelf(CommandContext<CommandSourceStack> ctx, CommandAction.MultiSuccess<ServerPlayer> action) throws CommandSyntaxException {
            return action.applyAsInt(ctx.getSource().getPlayerOrException());
        }

        public static int playerSelfWithMainHand(CommandContext<CommandSourceStack> ctx, BiCommandAction.MultiSuccess<ServerPlayer, ItemStack> action) throws CommandSyntaxException {
            return playerSelf(ctx, (player) -> (player.getMainHandItem().isEmpty()) ? 0 : action.applyAsInt(player, player.getMainHandItem()));
        }

    }

}
