/*
 * Copyright (C) 2022-2024 AUIOC.ORG
 *
 * This file is part of ArnicaLib, a mod made for Minecraft.
 *
 * ArnicaLib is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <https://www.gnu.org/licenses/>.
 */

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
