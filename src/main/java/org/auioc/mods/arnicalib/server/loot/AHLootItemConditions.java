package org.auioc.mods.arnicalib.server.loot;

import org.auioc.mods.arnicalib.ArnicaLib;
import org.auioc.mods.arnicalib.server.loot.predicate.ModLoadedCondition;
import net.minecraft.core.Registry;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

public final class AHLootItemConditions {

    public static void init() {}

    public static final LootItemConditionType MOD_LOADED = register("mod_loaded", new ModLoadedCondition.SerializerX());

    private static LootItemConditionType register(String id, Serializer<? extends LootItemCondition> serializer) {
        return Registry.register(Registry.LOOT_CONDITION_TYPE, ArnicaLib.id(id), new LootItemConditionType(serializer));
    }

}
