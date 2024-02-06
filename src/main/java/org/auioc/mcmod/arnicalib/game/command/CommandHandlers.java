/*
 * Copyright (C) 2022-2024 AUIOC.ORG
 *
 * This file is part of ArnicaLib, a mod made for Minecraft.
 *
 * ArnicaLib is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <https://www.gnu.org/licenses/>.
 */

package org.auioc.mcmod.arnicalib.game.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import org.auioc.mcmod.arnicalib.game.command.action.BiCommandAction;
import org.auioc.mcmod.arnicalib.game.command.action.CommandAction;

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
