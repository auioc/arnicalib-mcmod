package org.auioc.mods.arnicalib.utils.game;

import java.util.List;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.ClientLanguage;
import net.minecraft.client.resources.language.LanguageInfo;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public interface LanguageUtils {

    LanguageInfo DEFAULT_LANGUAGE = new LanguageInfo("en_us", "US", "English", false);

    SuggestionProvider<CommandSourceStack> ALL_LANGUAGES_SUGGESTION = (ctx, builder) -> {
        return SharedSuggestionProvider.suggest(Minecraft.getInstance().getLanguageManager().getLanguages().stream().map(LanguageInfo::getCode), builder);
    };

    static ClientLanguage getLanguage(LanguageInfo langInfo) {
        return ClientLanguage.loadFrom(
            Minecraft.getInstance().getResourceManager(),
            List.of(langInfo)
        );
    }

    static ClientLanguage getLanguage(String langCode) {
        var langInfo = Minecraft.getInstance().getLanguageManager().getLanguage(langCode);
        if (langInfo == null) {
            langInfo = DEFAULT_LANGUAGE;
        }

        return getLanguage(langInfo);
    }

}
