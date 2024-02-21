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

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.context.ParsedCommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.commands.CommandSourceStack;

import java.util.List;
import java.util.stream.Collectors;

public class CommandNodeUtils {

    public static String getLastLiteral(List<ParsedCommandNode<CommandSourceStack>> nodeList) {
        for (int i = nodeList.size(); i-- > 0; ) {
            if (nodeList.get(i).getNode() instanceof LiteralCommandNode) {
                return ((LiteralCommandNode<CommandSourceStack>) nodeList.get(i).getNode()).getLiteral();
            }
        }
        return null;
    }

    /**
     * @param nodeList  List of {@link ParsedCommandNode}s, from {@link CommandContext#getNodes()}
     * @param fromIndex
     * @param toIndex
     * @return String that concatenates the literals (or in its snake case) of all (or some of) {@link LiteralCommandNode}s in the {@link ParsedCommandNode} list, separated by dots
     * @since 5.1.1
     */
    public static String joinLiteralNodes(List<ParsedCommandNode<CommandSourceStack>> nodeList, int fromIndex, int toIndex) {
        return nodeList
            .subList(fromIndex, toIndex)
            .stream()
            .map(ParsedCommandNode::getNode)
            .filter(LiteralCommandNode.class::isInstance)
            .map((node) -> (LiteralCommandNode<CommandSourceStack>) node)
            .map(LiteralCommandNode::getLiteral)
            .collect(Collectors.joining("."));
    }

    /**
     * @see #joinLiteralNodes(List, int, int)
     */
    public static String joinLiteralNodes(List<ParsedCommandNode<CommandSourceStack>> nodes, int fromIndex) {
        return joinLiteralNodes(nodes, fromIndex, nodes.size());
    }

    /**
     * @see #joinLiteralNodes(List, int, int)
     */
    public static String joinLiteralNodes(List<ParsedCommandNode<CommandSourceStack>> nodes) {
        return joinLiteralNodes(nodes, 0, nodes.size());
    }

}
