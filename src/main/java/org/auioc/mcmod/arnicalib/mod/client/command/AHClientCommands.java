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

package org.auioc.mcmod.arnicalib.mod.client.command;

import static net.minecraft.commands.Commands.literal;

import java.util.List;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.CommandNode;
import net.minecraft.commands.CommandSourceStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

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

}
