package org.auioc.mods.arnicalib.client.command.argument;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import org.auioc.mods.arnicalib.ArnicaLib;
import org.auioc.mods.arnicalib.utils.game.TextUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.LanguageInfo;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LanguageInfoArgument implements ArgumentType<LanguageInfo> {

    private static final DynamicCommandExceptionType UNKNOWN_LANGUAGE_CODE = new DynamicCommandExceptionType(
        (langCode) -> TextUtils.I18nText(ArnicaLib.i18n("argument.language_info.unknown"), langCode)
    );

    public static LanguageInfoArgument languageInfo() {
        return new LanguageInfoArgument();
    }

    @Override
    public LanguageInfo parse(StringReader reader) throws CommandSyntaxException {
        String langCode = reader.readString();
        return getLanguages()
            .filter((langInfo) -> langInfo.getCode().equals(langCode))
            .findAny()
            .orElseThrow(() -> UNKNOWN_LANGUAGE_CODE.create(langCode));
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return SharedSuggestionProvider.suggest(getLanguages().map(LanguageInfo::getCode), builder);
    }

    private static Stream<LanguageInfo> getLanguages() {
        return Minecraft.getInstance().getLanguageManager().getLanguages().stream();
    }

}
