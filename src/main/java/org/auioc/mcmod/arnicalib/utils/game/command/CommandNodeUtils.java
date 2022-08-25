package org.auioc.mcmod.arnicalib.utils.game.command;

import java.util.List;
import java.util.stream.Collectors;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.context.ParsedCommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.commands.CommandSourceStack;

public class CommandNodeUtils {

    public static String getLastLiteral(List<ParsedCommandNode<CommandSourceStack>> nodeList) {
        for (int i = nodeList.size(); i-- > 0;) {
            if (nodeList.get(i).getNode() instanceof LiteralCommandNode) {
                return ((LiteralCommandNode<CommandSourceStack>) nodeList.get(i).getNode()).getLiteral();
            }
        }
        return null;
    }

    /**
     * @param nodeList           List of {@link ParsedCommandNode}s, from {@link CommandContext#getNodes()}
     * @param fromIndex
     * @param toIndex
     * @param conventToSnakeCase
     * @return String that concatenates the literals (or in its snake case) of all (or some of) {@link LiteralCommandNode}s in the {@link ParsedCommandNode} list, separated by dots
     * @since 5.1.1
     */
    public static String joinLiteralNodes(List<ParsedCommandNode<CommandSourceStack>> nodeList, int fromIndex, int toIndex) {
        return nodeList
            .subList(fromIndex, toIndex)
            .stream()
            .map(ParsedCommandNode::getNode)
            .filter((node) -> node instanceof LiteralCommandNode)
            .map((node) -> (LiteralCommandNode<CommandSourceStack>) node)
            .map(LiteralCommandNode::getLiteral)
            .collect(Collectors.joining("."));
    }

    /**
     * @see {@link #joinLiteralNodes(List, int, int, boolean)}
     */
    public static String joinLiteralNodes(List<ParsedCommandNode<CommandSourceStack>> nodes, int fromIndex) {
        return joinLiteralNodes(nodes, fromIndex, nodes.size());
    }

    /**
     * @see {@link #joinLiteralNodes(List, int, int, boolean)}
     */
    public static String joinLiteralNodes(List<ParsedCommandNode<CommandSourceStack>> nodes) {
        return joinLiteralNodes(nodes, 0, nodes.size());
    }

}
