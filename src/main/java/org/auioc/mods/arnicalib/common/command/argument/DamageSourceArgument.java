package org.auioc.mods.arnicalib.common.command.argument;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import org.auioc.mods.arnicalib.ArnicaLib;
import org.auioc.mods.arnicalib.utils.game.TextUtils;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.world.damagesource.DamageSource;

public class DamageSourceArgument implements ArgumentType<DamageSource> {
    private static HashMap<String, DamageSource> MAP = new HashMap<String, DamageSource>();

    public static DamageSourceArgument damageSource() {
        return new DamageSourceArgument();
    }

    public static DamageSource getDamageSource(CommandContext<CommandSourceStack> context, String argument) throws CommandSyntaxException {
        return context.getArgument(argument, DamageSource.class);
    }

    @Override
    public DamageSource parse(StringReader reader) throws CommandSyntaxException {
        String sourceName = reader.readString();

        if (MAP.containsKey(sourceName)) {
            return MAP.get(sourceName);
        }

        throw (new SimpleCommandExceptionType(
            TextUtils.I18nText(ArnicaLib.MOD_ID + ".argument.damage_source.invalid", sourceName)
        )).create();
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return SharedSuggestionProvider.suggest(MAP.keySet(), builder);
    }

    static {
        {
            MAP.put("inFire", DamageSource.IN_FIRE);
            MAP.put("lightningBolt", DamageSource.LIGHTNING_BOLT);
            MAP.put("onFire", DamageSource.ON_FIRE);
            MAP.put("lava", DamageSource.LAVA);
            MAP.put("hotFloor", DamageSource.HOT_FLOOR);
            MAP.put("inWall", DamageSource.IN_WALL);
            MAP.put("cramming", DamageSource.CRAMMING);
            MAP.put("drown", DamageSource.DROWN);
            MAP.put("starve", DamageSource.STARVE);
            MAP.put("cactus", DamageSource.CACTUS);
            MAP.put("fall", DamageSource.FALL);
            MAP.put("flyIntoWall", DamageSource.FLY_INTO_WALL);
            MAP.put("outOfWorld", DamageSource.OUT_OF_WORLD);
            MAP.put("generic", DamageSource.GENERIC);
            MAP.put("magic", DamageSource.MAGIC);
            MAP.put("wither", DamageSource.WITHER);
            MAP.put("anvil", DamageSource.ANVIL);
            MAP.put("fallingBlock", DamageSource.FALLING_BLOCK);
            MAP.put("dragonBreath", DamageSource.DRAGON_BREATH);
            MAP.put("dryout", DamageSource.DRY_OUT);
            MAP.put("sweetBerryBush", DamageSource.SWEET_BERRY_BUSH);
            MAP.put("freeze", DamageSource.FREEZE);
            MAP.put("fallingStalactite", DamageSource.FALLING_STALACTITE);
            MAP.put("stalagmite", DamageSource.STALAGMITE);
        }
    };
}
