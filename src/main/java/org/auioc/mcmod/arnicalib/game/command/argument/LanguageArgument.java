package org.auioc.mcmod.arnicalib.game.command.argument;

import java.util.concurrent.CompletableFuture;
import org.auioc.mcmod.arnicalib.ArnicaLib;
import org.auioc.mcmod.arnicalib.game.language.LanguageUtils;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.client.resources.language.LanguageInfo;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LanguageArgument implements ArgumentType<LanguageInfo> {

    private static final DynamicCommandExceptionType ERROR_UNKNOWN_LANGUAGE = new DynamicCommandExceptionType(
        (code) -> Component.translatable(ArnicaLib.i18n("argument.language.unknown"), code)
    );

    public static LanguageArgument create() {
        return new LanguageArgument();
    }

    public static LanguageInfo get(CommandContext<CommandSourceStack> ctx, String argument) {
        return ctx.getArgument(argument, LanguageInfo.class);
    }

    @Override
    public LanguageInfo parse(StringReader reader) throws CommandSyntaxException {
        String code = reader.readString();
        return LanguageUtils.get(code).orElseThrow(() -> ERROR_UNKNOWN_LANGUAGE.create(code));
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return SharedSuggestionProvider.suggest(LanguageUtils.codes(), builder);
    }

}
