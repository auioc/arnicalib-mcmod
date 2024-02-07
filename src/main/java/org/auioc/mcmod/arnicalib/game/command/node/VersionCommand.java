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

package org.auioc.mcmod.arnicalib.game.command.node;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.tree.CommandNode;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import org.apache.logging.log4j.Marker;
import org.auioc.mcmod.arnicalib.ArnicaLib;
import org.auioc.mcmod.arnicalib.base.log.LogUtil;
import org.auioc.mcmod.arnicalib.game.chat.TextUtils;
import org.auioc.mcmod.arnicalib.game.mod.BuildInfo;

import java.util.function.Function;

import static net.minecraft.commands.Commands.literal;
import static org.auioc.mcmod.arnicalib.ArnicaLib.LOGGER;

public class VersionCommand {

    private static final Marker MARKER = LogUtil.getMarker(VersionCommand.class);

    private static final SimpleCommandExceptionType GET_VERSION_REFLECTION_ERROR = new SimpleCommandExceptionType(i18n("failure", TextUtils.NO_ARGS));

    public static final Function<Class<?>, Command<CommandSourceStack>> HANDLER_BUILDER = (modClass) -> (ctx) -> getModVersion(ctx, modClass);

    public static final Function<Class<?>, CommandNode<CommandSourceStack>> NODE_BUILDER = (modClass) -> literal("version").executes(HANDLER_BUILDER.apply(modClass)).build();

    public static void addVersionNode(CommandNode<CommandSourceStack> node, Class<?> modClass) {
        node.addChild(NODE_BUILDER.apply(modClass));
    }

    public static int getModVersion(CommandContext<CommandSourceStack> ctx, Class<?> modClazz) throws CommandSyntaxException {
        BuildInfo buildInfo;
        String modName;
        try {
            buildInfo = (BuildInfo) modClazz.getField("BUILD_INFO").get(modClazz);
            modName = (String) modClazz.getField("MOD_NAME").get(modClazz);
        } catch (Exception e) {
            var commandException = GET_VERSION_REFLECTION_ERROR.create();
            LOGGER.error(MARKER, commandException.getMessage(), e);
            throw commandException;
        }
        var message = Component.empty();
        message.append(Component.literal("[" + modName + "] ").withStyle(ChatFormatting.AQUA));
        message.append(i18n("success", buildInfo.version(), buildInfo));
        ctx.getSource().sendSuccess(() -> message, false);
        return Command.SINGLE_SUCCESS;
    }

    // ============================================================================================================== //

    private static MutableComponent i18n(String key, Object... args) {
        return Component.translatable(ArnicaLib.i18n("command.version." + key), args);
    }

}
