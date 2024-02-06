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

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BaseCommandBlock;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.function.Predicate;

public class CommandSourceUtils {

    public static final Predicate<CommandSourceStack> PERMISSION_LEVEL_0 = (sourceStack) -> sourceStack.hasPermission(0);
    public static final Predicate<CommandSourceStack> PERMISSION_LEVEL_1 = (sourceStack) -> sourceStack.hasPermission(1);
    public static final Predicate<CommandSourceStack> PERMISSION_LEVEL_2 = (sourceStack) -> sourceStack.hasPermission(2);
    public static final Predicate<CommandSourceStack> PERMISSION_LEVEL_3 = (sourceStack) -> sourceStack.hasPermission(3);
    public static final Predicate<CommandSourceStack> PERMISSION_LEVEL_4 = (sourceStack) -> sourceStack.hasPermission(4);

    public static final Predicate<CommandSourceStack> IS_PLAYER = (sourceStack) -> sourceStack.getEntity() instanceof Player;
    public static final Predicate<CommandSourceStack> IS_COMMAND_BLOCK = (sourceStack) -> sourceStack.source instanceof BaseCommandBlock;
    public static final Predicate<CommandSourceStack> IS_DEDICATED_SERVER = (sourceStack) -> sourceStack.source instanceof DedicatedServer;

    /**
     * @deprecated The field {@link CommandSourceStack#source} is now public.
     */
    @Deprecated(since = "6.0.0")
    public static CommandSource getRealSource(CommandSourceStack sourceStack) {
        return sourceStack.source;
    }

    @OnlyIn(Dist.CLIENT)
    public static LocalPlayer getLocalPlayerOrException(CommandSourceStack sourceStack) throws CommandSyntaxException {
        var entity = sourceStack.getEntity();
        if (entity instanceof LocalPlayer) return (LocalPlayer) entity;
        throw CommandSourceStack.ERROR_NOT_PLAYER.create();
    }

}
