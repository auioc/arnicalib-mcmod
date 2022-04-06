package org.auioc.mcmod.arnicalib.server.loot;

import org.auioc.mcmod.arnicalib.ArnicaLib;
import org.auioc.mcmod.arnicalib.server.loot.predicate.ModLoadedCondition;
import net.minecraft.core.Registry;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public final class AHLootItemConditions {

    public static final DeferredRegister<LootItemConditionType> LOOT_CONDITION_TYPES = DeferredRegister.create(Registry.LOOT_ITEM_REGISTRY, ArnicaLib.MOD_ID);

    private static RegistryObject<LootItemConditionType> register(String id, Serializer<? extends LootItemCondition> serializer) {
        return LOOT_CONDITION_TYPES.register(id, () -> new LootItemConditionType(serializer));
    }

    public static final RegistryObject<LootItemConditionType> MOD_LOADED = register("mod_loaded", new ModLoadedCondition.Serializer());

}
