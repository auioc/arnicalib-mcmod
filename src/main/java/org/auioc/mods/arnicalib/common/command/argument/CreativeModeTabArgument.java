package org.auioc.mods.arnicalib.common.command.argument;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import org.auioc.mods.arnicalib.ArnicaLib;
import org.auioc.mods.arnicalib.api.mixin.common.IMixinCreativeModeTab;
import org.auioc.mods.arnicalib.utils.game.TextUtils;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.world.item.CreativeModeTab;

public class CreativeModeTabArgument implements ArgumentType<CreativeModeTab> {

    private static final DynamicCommandExceptionType UNKNOWN_CREATIVE_MOD_TAB = new DynamicCommandExceptionType(
        (langId) -> TextUtils.I18nText(ArnicaLib.i18n("argument.creative_mod_tab.unknown"), langId)
    );

    public static CreativeModeTabArgument creativeModeTab() {
        return new CreativeModeTabArgument();
    }

    @Override
    public CreativeModeTab parse(StringReader reader) throws CommandSyntaxException {
        String langId = reader.readString();
        return getAllTabs()
            .stream()
            .filter((tab) -> getLangId(tab).equals(langId))
            .findAny()
            .orElseThrow(() -> UNKNOWN_CREATIVE_MOD_TAB.create(langId));
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return SharedSuggestionProvider.suggest(
            getAllTabs().stream().map(CreativeModeTabArgument::getLangId),
            builder
        );
    }

    public static List<CreativeModeTab> getAllTabs() {
        return Arrays.asList(CreativeModeTab.TABS);
    }

    public static String getLangId(CreativeModeTab tab) {
        // return ((TranslatableComponent) tab.getDisplayName()).getKey().replaceFirst("^itemGroup\\.", "");
        return ((IMixinCreativeModeTab) tab).getLangId();
    }

}
