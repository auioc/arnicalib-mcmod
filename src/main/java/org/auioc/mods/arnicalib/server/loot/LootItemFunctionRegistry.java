package org.auioc.mods.arnicalib.server.loot;

import org.auioc.mods.arnicalib.Reference;
import org.auioc.mods.arnicalib.server.loot.function.SetRandomPotionFunction;
import net.minecraft.core.Registry;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;

public class LootItemFunctionRegistry {

    public static void init() {}

    public static final LootItemFunctionType SET_RANDOM_POTION = register("set_random_potion", new SetRandomPotionFunction.SerializerX());

    private static LootItemFunctionType register(String id, Serializer<? extends LootItemFunction> serializer) {
        return Registry.register(Registry.LOOT_FUNCTION_TYPE, Reference.ResourceId(id), new LootItemFunctionType(serializer));
    }

}