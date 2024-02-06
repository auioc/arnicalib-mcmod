package org.auioc.mcmod.arnicalib.mod.server.loot;

import com.mojang.serialization.Codec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.auioc.mcmod.arnicalib.ArnicaLib;
import org.auioc.mcmod.arnicalib.game.loot.function.SetCustomEffectsFunction;
import org.auioc.mcmod.arnicalib.game.loot.function.SetRandomPotionFunction;


public final class AHLootItemFunctions {

    public static final DeferredRegister<LootItemFunctionType> LOOT_FUNCTION_TYPES = DeferredRegister.create(Registries.LOOT_FUNCTION_TYPE, ArnicaLib.MOD_ID);

    private static DeferredHolder<LootItemFunctionType, LootItemFunctionType> register(String id, Codec<? extends LootItemFunction> codec) {
        return LOOT_FUNCTION_TYPES.register(id, () -> new LootItemFunctionType(codec));
    }

    public static final DeferredHolder<LootItemFunctionType, LootItemFunctionType> SET_RANDOM_POTION = register("set_random_potion", SetRandomPotionFunction.CODEC);
    public static final DeferredHolder<LootItemFunctionType, LootItemFunctionType> SET_CUSTOM_EFFECTS = register("set_custom_effects", SetCustomEffectsFunction.CODEC);

}
