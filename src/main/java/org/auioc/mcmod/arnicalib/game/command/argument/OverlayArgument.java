package org.auioc.mcmod.arnicalib.game.command.argument;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
import org.auioc.mcmod.arnicalib.ArnicaLib;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.gui.overlay.GuiOverlayManager;
import net.minecraftforge.client.gui.overlay.NamedGuiOverlay;

@OnlyIn(Dist.CLIENT)
public class OverlayArgument implements ArgumentType<NamedGuiOverlay> {

    private static final DynamicCommandExceptionType UNKNOWN_OVERLAY = new DynamicCommandExceptionType(
        (id) -> Component.translatable(ArnicaLib.i18n("argument.overlay.unknown"), id)
    );

    public static OverlayArgument overlay() {
        return new OverlayArgument();
    }

    public static NamedGuiOverlay getOverlay(CommandContext<CommandSourceStack> ctx, String argument) {
        return ctx.getArgument(argument, NamedGuiOverlay.class);
    }

    @Override
    public NamedGuiOverlay parse(StringReader reader) throws CommandSyntaxException {
        var id = new ResourceLocation(reader.readString());
        var overlay = GuiOverlayManager.findOverlay(id);
        if (overlay != null) return overlay;
        throw UNKNOWN_OVERLAY.create(id);
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return SharedSuggestionProvider.suggest(getOverlays().map(NamedGuiOverlay::id).map(ResourceLocation::toString), builder);
    }

    private static Stream<NamedGuiOverlay> getOverlays() {
        return GuiOverlayManager.getOverlays().stream();
    }

}
