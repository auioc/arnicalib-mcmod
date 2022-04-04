package org.auioc.mods.arnicalib.server.loot;

import org.auioc.mods.arnicalib.ArnicaLib;
import org.auioc.mods.arnicalib.server.loot.function.SetCustomEffectsFunction;
import org.auioc.mods.arnicalib.server.loot.function.SetRandomPotionFunction;
import net.minecraft.core.Registry;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public final class AHLootItemFunctions {

    public static final DeferredRegister<LootItemFunctionType> LOOT_FUNCTION_TYPES = DeferredRegister.create(Registry.LOOT_FUNCTION_REGISTRY, ArnicaLib.MOD_ID);

    private static RegistryObject<LootItemFunctionType> register(String id, Serializer<? extends LootItemFunction> serializer) {
        return LOOT_FUNCTION_TYPES.register(id, () -> new LootItemFunctionType(serializer));
    }

    public static final RegistryObject<LootItemFunctionType> SET_RANDOM_POTION = register("set_random_potion", new SetRandomPotionFunction.SerializerX());
    public static final RegistryObject<LootItemFunctionType> SET_CUSTOM_EFFECTS = register("set_custom_effects", new SetCustomEffectsFunction.SerializerX());

}
