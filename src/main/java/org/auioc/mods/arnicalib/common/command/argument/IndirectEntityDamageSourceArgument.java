package org.auioc.mods.arnicalib.common.command.argument;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;

public class IndirectEntityDamageSourceArgument implements ArgumentType<BiFunction<Entity, Entity, IndirectEntityDamageSource>> {
    private static final List<String> LIST = List.of("mob", "arrow", "trident", "fireworks", "fireworks", "onFire", "fireball", "witherSkull", "thrown", "indirectMagic");

    public static IndirectEntityDamageSourceArgument damageSource() {
        return new IndirectEntityDamageSourceArgument();
    }

    @SuppressWarnings("unchecked")
    public static BiFunction<Entity, Entity, IndirectEntityDamageSource> getDamageSource(CommandContext<CommandSourceStack> context, String argument) throws CommandSyntaxException {
        return context.getArgument(argument, BiFunction.class);
    }

    @Override
    public BiFunction<Entity, Entity, IndirectEntityDamageSource> parse(StringReader reader) throws CommandSyntaxException {
        String sourceName = reader.readString();
        return (entity, owner) -> new IndirectEntityDamageSource(sourceName, entity, owner);
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return SharedSuggestionProvider.suggest(LIST, builder);
    }
}
