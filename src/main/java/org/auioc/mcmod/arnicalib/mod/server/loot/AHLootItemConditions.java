package org.auioc.mcmod.arnicalib.mod.server.loot;

import org.auioc.mcmod.arnicalib.ArnicaLib;
import org.auioc.mcmod.arnicalib.game.loot.predicate.EntityAttributeCondition;
import org.auioc.mcmod.arnicalib.game.loot.predicate.ModLoadedCondition;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public final class AHLootItemConditions {

    public static final DeferredRegister<LootItemConditionType> LOOT_CONDITION_TYPES = DeferredRegister.create(Registries.LOOT_CONDITION_TYPE, ArnicaLib.MOD_ID);

    private static RegistryObject<LootItemConditionType> register(String id, Serializer<? extends LootItemCondition> serializer) {
        return LOOT_CONDITION_TYPES.register(id, () -> new LootItemConditionType(serializer));
    }

    public static final RegistryObject<LootItemConditionType> MOD_LOADED = register("mod_loaded", new ModLoadedCondition.Serializer());
    public static final RegistryObject<LootItemConditionType> ENTITY_ATTRIBUTE = register("entity_attribute", new EntityAttributeCondition.Serializer());

}
