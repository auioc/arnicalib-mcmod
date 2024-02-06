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
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.auioc.mcmod.arnicalib.ArnicaLib;
import org.auioc.mcmod.arnicalib.game.loot.function.SetCustomEffectsFunction;
import org.auioc.mcmod.arnicalib.game.loot.function.SetRandomPotionFunction;


public final class AHLootItemFunctions {

    public static final DeferredRegister<LootItemFunctionType> LOOT_FUNCTION_TYPES = DeferredRegister.create(Registries.LOOT_FUNCTION_TYPE, ArnicaLib.MOD_ID);

    private static DeferredHolder<LootItemFunctionType, LootItemFunctionType> register(String id, Codec<? extends LootItemFunction> codec) {
        return LOOT_FUNCTION_TYPES.register(id, () -> new LootItemFunctionType(codec));
    }

    public static final DeferredHolder<LootItemFunctionType, LootItemFunctionType> SET_RANDOM_POTION = register("set_random_potion", SetRandomPotionFunction.CODEC);
    public static final DeferredHolder<LootItemFunctionType, LootItemFunctionType> SET_CUSTOM_EFFECTS = register("set_custom_effects", SetCustomEffectsFunction.CODEC);

}
