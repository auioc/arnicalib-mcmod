package org.auioc.mods.arnicalib.server.loot;

import org.auioc.mods.arnicalib.Reference;
import net.minecraft.core.Registry;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;

public class LootItemFunctionRegistry {

    public static void init() {}

    private static LootItemFunctionType register(String id, Serializer<? extends LootItemFunction> serializer) {
        return Registry.register(Registry.LOOT_FUNCTION_TYPE, Reference.ResourceId(id), new LootItemFunctionType(serializer));
    }

}
