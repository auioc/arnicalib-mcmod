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

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.gui.overlay.GuiOverlayManager;
import net.neoforged.neoforge.client.gui.overlay.NamedGuiOverlay;
import org.auioc.mcmod.arnicalib.ArnicaLib;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@OnlyIn(Dist.CLIENT)
public class OverlayArgument implements ArgumentType<NamedGuiOverlay> {

    private static final DynamicCommandExceptionType ERROR_UNKNOWN_OVERLAY = new DynamicCommandExceptionType(
        (id) -> Component.translatable(ArnicaLib.i18n("argument.overlay.unknown"), id)
    );

    public static OverlayArgument create() {
        return new OverlayArgument();
    }

    public static NamedGuiOverlay get(CommandContext<CommandSourceStack> ctx, String argument) {
        return ctx.getArgument(argument, NamedGuiOverlay.class);
    }

    @Override
    public NamedGuiOverlay parse(StringReader reader) throws CommandSyntaxException {
        var id = new ResourceLocation(reader.readString());
        var overlay = GuiOverlayManager.findOverlay(id);
        if (overlay != null) return overlay;
        throw ERROR_UNKNOWN_OVERLAY.create(id);
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return SharedSuggestionProvider.suggest(getOverlays().map(NamedGuiOverlay::id).map(ResourceLocation::toString), builder);
    }

    private static Stream<NamedGuiOverlay> getOverlays() {
        return GuiOverlayManager.getOverlays().stream();
    }

}
