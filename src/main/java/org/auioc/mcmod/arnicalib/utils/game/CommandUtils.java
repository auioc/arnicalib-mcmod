package org.auioc.mcmod.arnicalib.utils.game;

import java.util.List;
import java.util.function.Predicate;
import org.auioc.mcmod.arnicalib.game.command.CommandExceptions;
import org.auioc.mcmod.arnicalib.game.command.CommandNodeUtils;
import org.auioc.mcmod.arnicalib.game.command.CommandSourceUtils;
import com.mojang.brigadier.context.ParsedCommandNode;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@Deprecated(since = "5.4.4", forRemoval = true)
public interface CommandUtils {

    SimpleCommandExceptionType INTERNAL_ERROR = CommandExceptions.INTERNAL_ERROR;
    SimpleCommandExceptionType LOGGABLE_INTERNAL_ERROR = CommandExceptions.LOGGABLE_INTERNAL_ERROR;
    SimpleCommandExceptionType NOT_SERVER_ERROR = CommandExceptions.NOT_SERVER_ERROR;
    SimpleCommandExceptionType NOT_DEDICATED_SERVER_ERROR = CommandExceptions.NOT_DEDICATED_SERVER_ERROR;
    SimpleCommandExceptionType GET_REAL_SOURCE_REFLECTION_ERROR = CommandExceptions.GET_REAL_SOURCE_REFLECTION_ERROR;

    Predicate<CommandSourceStack> IS_PLAYER = CommandSourceUtils.IS_PLAYER;
    Predicate<CommandSourceStack> PERMISSION_LEVEL_0 = CommandSourceUtils.PERMISSION_LEVEL_0;
    Predicate<CommandSourceStack> PERMISSION_LEVEL_1 = CommandSourceUtils.PERMISSION_LEVEL_1;
    Predicate<CommandSourceStack> PERMISSION_LEVEL_2 = CommandSourceUtils.PERMISSION_LEVEL_2;
    Predicate<CommandSourceStack> PERMISSION_LEVEL_3 = CommandSourceUtils.PERMISSION_LEVEL_3;
    Predicate<CommandSourceStack> PERMISSION_LEVEL_4 = CommandSourceUtils.PERMISSION_LEVEL_4;

    static CommandSource getPrivateSource(CommandSourceStack sourceStack) {
        return CommandSourceUtils.getRealSourceReflection(sourceStack);
    }

    @OnlyIn(Dist.CLIENT)
    static LocalPlayer getLocalPlayerOrException(CommandSourceStack sourceStack) throws CommandSyntaxException {
        return CommandSourceUtils.getLocalPlayerOrException(sourceStack);
    }

    static String joinLiteralNodes(List<ParsedCommandNode<CommandSourceStack>> nodeList, int fromIndex, int toIndex, boolean conventToSnakeCase) {
        return CommandNodeUtils.joinLiteralNodes(nodeList, fromIndex, toIndex);
    }

    static String joinLiteralNodes(List<ParsedCommandNode<CommandSourceStack>> nodes, int fromIndex) {
        return CommandNodeUtils.joinLiteralNodes(nodes, fromIndex, nodes.size());
    }

    static String joinLiteralNodes(List<ParsedCommandNode<CommandSourceStack>> nodes) {
        return CommandNodeUtils.joinLiteralNodes(nodes, 0, nodes.size());
    }

}
