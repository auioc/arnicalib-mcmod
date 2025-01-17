/*
 * Copyright (C) 2022-2025 AUIOC.ORG
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

package org.auioc.mcmod.arnicalib.mod.loot;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.auioc.mcmod.arnicalib.ArnicaLib;
import org.auioc.mcmod.arnicalib.game.loot.predicate.BlockStateCondition;
import org.auioc.mcmod.arnicalib.game.loot.predicate.EntityAttributeCondition;
import org.auioc.mcmod.arnicalib.game.loot.predicate.ExtraLocationCheck;

public class AHLootItemConditions {

    public static final DeferredRegister<LootItemConditionType> TYPES = DeferredRegister.create(BuiltInRegistries.LOOT_CONDITION_TYPE, ArnicaLib.MOD_ID);

    public static final DeferredHolder<LootItemConditionType, LootItemConditionType> ENTITY_ATTRIBUTE = TYPES.register("entity_attribute", () -> EntityAttributeCondition.TYPE);
    public static final DeferredHolder<LootItemConditionType, LootItemConditionType> BLOCK_STATE = TYPES.register("block_state", () -> BlockStateCondition.TYPE);
    public static final DeferredHolder<LootItemConditionType, LootItemConditionType> LOCATION_CHECK = TYPES.register("location_check", () -> ExtraLocationCheck.TYPE);

}
