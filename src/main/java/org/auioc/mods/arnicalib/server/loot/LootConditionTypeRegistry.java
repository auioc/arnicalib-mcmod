package org.auioc.mods.arnicalib.server.loot;

import org.auioc.mods.arnicalib.Reference;
import net.minecraft.core.Registry;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

public class LootConditionTypeRegistry {

    public static void init() {}

    private static LootItemConditionType register(String id, Serializer<? extends LootItemCondition> serializer) {
        return Registry.register(Registry.LOOT_CONDITION_TYPE, Reference.ResourceId(id), new LootItemConditionType(serializer));
    }

}
