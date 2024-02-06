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

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.CommandNode;
import net.minecraft.commands.CommandSourceStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.auioc.mcmod.arnicalib.mod.client.command.AHClientCommands;
import org.auioc.mcmod.arnicalib.mod.server.command.AHServerCommands;

public final class AHCommands {

    public static CommandNode<CommandSourceStack> getServerNode(CommandDispatcher<CommandSourceStack> dispatcher) {
        return AHServerCommands.getAHNode(dispatcher);
    }

    @OnlyIn(Dist.CLIENT)
    public static CommandNode<CommandSourceStack> getClientNode(CommandDispatcher<CommandSourceStack> dispatcher) {
        return AHClientCommands.getAHNode(dispatcher);
    }

}
