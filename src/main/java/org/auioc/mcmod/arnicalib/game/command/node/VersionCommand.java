package org.auioc.mcmod.arnicalib.game.command.node;

import static net.minecraft.commands.Commands.literal;
import static org.auioc.mcmod.arnicalib.ArnicaLib.LOGGER;
import java.util.function.Function;
import org.apache.logging.log4j.Marker;
import org.auioc.mcmod.arnicalib.ArnicaLib;
import org.auioc.mcmod.arnicalib.base.log.LogUtil;
import org.auioc.mcmod.arnicalib.base.version.HVersion;
import org.auioc.mcmod.arnicalib.game.chat.TextUtils;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.tree.CommandNode;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class VersionCommand {

    private static final Marker MARKER = LogUtil.getMarker(VersionCommand.class);

    private static final SimpleCommandExceptionType GET_VERSION_REFLECTION_ERROR = new SimpleCommandExceptionType(i18n("failure.reflection"));

    public static final Function<Class<?>, Command<CommandSourceStack>> HANDLER_BUILDER = (modClass) -> (ctx) -> getModVersion(ctx, modClass);

    public static final Function<Class<?>, CommandNode<CommandSourceStack>> NODE_BUILDER = (modClass) -> literal("version").executes(HANDLER_BUILDER.apply(modClass)).build();

    public static void addVersionNode(CommandNode<CommandSourceStack> node, Class<?> modClass) {
        node.addChild(NODE_BUILDER.apply(modClass));
    }

    public static int getModVersion(CommandContext<CommandSourceStack> ctx, String mainVersion, String fullVersion, String modName) {
        MutableComponent message = Component.empty();

        message.append(Component.literal("[" + modName + "] ").withStyle(ChatFormatting.AQUA));

        if (mainVersion.equals("0") && fullVersion.equals("0")) {
            message.append(i18n("failure.zero"));
        } else {
            message.append(i18n("success", mainVersion, fullVersion));
        }

        ctx.getSource().sendSuccess(() -> message, false);
        return Command.SINGLE_SUCCESS;
    }

    public static int getModVersion(CommandContext<CommandSourceStack> ctx, Class<?> modClazz) throws CommandSyntaxException {
        try {
            var hVersion = (HVersion) modClazz.getField("VERSION").get(modClazz);
            return getModVersion(
                ctx, hVersion.main, hVersion.full,
                (String) modClazz.getField("MOD_NAME").get(modClazz)
            );
        } catch (Exception e) {
            var commandException = GET_VERSION_REFLECTION_ERROR.create();
            LOGGER.error(MARKER, commandException.getMessage(), e);
            throw commandException;
        }
    }

    // ====================================================================== //

    private static MutableComponent i18n(String key, Object... args) {
        return Component.translatable(ArnicaLib.i18n("command.version." + key), args);
    }

    private static MutableComponent i18n(String key) {
        return i18n(key, TextUtils.NO_ARGS);
    }

}
