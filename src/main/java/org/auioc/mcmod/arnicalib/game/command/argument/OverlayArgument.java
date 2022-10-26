package org.auioc.mcmod.arnicalib.game.command.argument;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
import org.auioc.mcmod.arnicalib.ArnicaLib;
import org.auioc.mcmod.arnicalib.game.chat.TextUtils;
import org.auioc.mcmod.arnicalib.game.gui.OverlayUtils;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.client.gui.OverlayRegistry.OverlayEntry;

@OnlyIn(Dist.CLIENT)
public class OverlayArgument implements ArgumentType<OverlayEntry> {

    private static final DynamicCommandExceptionType UNKNOWN_OVERLAY = new DynamicCommandExceptionType(
        (name) -> TextUtils.translatable(ArnicaLib.i18n("argument.overlay.unknown"), name)
    );

    public static OverlayArgument overlay() {
        return new OverlayArgument();
    }

    public static OverlayEntry getOverlay(CommandContext<CommandSourceStack> ctx, String argument) {
        return ctx.getArgument(argument, OverlayEntry.class);
    }

    @Override
    public OverlayEntry parse(StringReader reader) throws CommandSyntaxException {
        String name = reader.readString();
        return getOverlays().filter((e) -> OverlayUtils.getName(e).equals(name)).findAny().orElseThrow(() -> UNKNOWN_OVERLAY.create(name));
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return SharedSuggestionProvider.suggest(getOverlays().map(OverlayUtils::getName), builder);
    }

    private static Stream<OverlayEntry> getOverlays() {
        return OverlayRegistry.orderedEntries().stream();
    }

}
