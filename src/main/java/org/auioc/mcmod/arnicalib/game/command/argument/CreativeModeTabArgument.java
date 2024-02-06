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

package org.auioc.mcmod.arnicalib.game.command.argument;

import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import org.auioc.mcmod.arnicalib.ArnicaLib;

public class CreativeModeTabArgument {

    private static final DynamicCommandExceptionType ERROR_UNKNOWN = new DynamicCommandExceptionType(
        (langId) -> Component.translatable(ArnicaLib.i18n("argument.creative_mode_tab.unknown"), langId)
    );

    public static final SuggestionProvider<CommandSourceStack> SUGGESTIONS = (ctx, builder) -> SharedSuggestionProvider.suggest(
        BuiltInRegistries.CREATIVE_MODE_TAB.keySet().stream().map(ResourceLocation::toString),
        builder
    );

    public static RequiredArgumentBuilder<CommandSourceStack, ResourceLocation> create(String name) {
        return Commands.argument(name, ResourceLocationArgument.id()).suggests(SUGGESTIONS);
    }

    public static CreativeModeTab get(CommandContext<CommandSourceStack> ctx, String name) throws CommandSyntaxException {
        var id = ResourceLocationArgument.getId(ctx, name);
        return BuiltInRegistries.CREATIVE_MODE_TAB
            .getOptional(id)
            .orElseThrow(() -> ERROR_UNKNOWN.create(id));
    }

}
