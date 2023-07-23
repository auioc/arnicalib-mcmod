package org.auioc.mcmod.arnicalib.game.command.argument;

import org.auioc.mcmod.arnicalib.ArnicaLib;
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
