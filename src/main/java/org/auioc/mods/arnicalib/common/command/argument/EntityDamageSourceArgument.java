package org.auioc.mods.arnicalib.common.command.argument;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.Entity;

public class EntityDamageSourceArgument implements ArgumentType<Function<Entity, EntityDamageSource>> {
    private static final List<String> LIST = List.of("sting", "mob", "player", "thorns", "explosion.player", "explosion");

    public static EntityDamageSourceArgument damageSource() {
        return new EntityDamageSourceArgument();
    }

    @SuppressWarnings("unchecked")
    public static Function<Entity, EntityDamageSource> getDamageSource(CommandContext<CommandSourceStack> context, String argument) throws CommandSyntaxException {
        return context.getArgument(argument, Function.class);
    }

    @Override
    public Function<Entity, EntityDamageSource> parse(StringReader reader) throws CommandSyntaxException {
        String sourceName = reader.readString();
        return (entity) -> new EntityDamageSource(sourceName, entity);
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return SharedSuggestionProvider.suggest(LIST, builder);
    }
}
