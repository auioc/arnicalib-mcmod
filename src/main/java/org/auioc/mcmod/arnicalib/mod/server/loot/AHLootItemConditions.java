package org.auioc.mcmod.arnicalib.mod.server.loot;

import com.mojang.serialization.Codec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.auioc.mcmod.arnicalib.ArnicaLib;
import org.auioc.mcmod.arnicalib.game.loot.predicate.EntityAttributeCondition;
import org.auioc.mcmod.arnicalib.game.loot.predicate.ModLoadedCondition;

public final class AHLootItemConditions {

    public static final DeferredRegister<LootItemConditionType> LOOT_CONDITION_TYPES = DeferredRegister.create(Registries.LOOT_CONDITION_TYPE, ArnicaLib.MOD_ID);

    private static DeferredHolder<LootItemConditionType, LootItemConditionType> register(String id, Codec<? extends LootItemCondition> codec) {
        return LOOT_CONDITION_TYPES.register(id, () -> new LootItemConditionType(codec));
    }

    public static final DeferredHolder<LootItemConditionType, LootItemConditionType> MOD_LOADED = register("mod_loaded", ModLoadedCondition.CODEC);

    public static final DeferredHolder<LootItemConditionType, LootItemConditionType> ENTITY_ATTRIBUTE = register("entity_attribute", EntityAttributeCondition.CODEC);

}
