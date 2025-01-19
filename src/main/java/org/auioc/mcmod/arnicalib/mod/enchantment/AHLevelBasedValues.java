/*
 * Copyright (C) 2025 AUIOC.ORG
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

package org.auioc.mcmod.arnicalib.mod.enchantment;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.auioc.mcmod.arnicalib.ArnicaLib;
import org.auioc.mcmod.arnicalib.game.enchantment.HLevelBasedValue;

public class AHLevelBasedValues {

    public static final DeferredRegister<MapCodec<? extends LevelBasedValue>> TYPES = DeferredRegister.create(Registries.ENCHANTMENT_LEVEL_BASED_VALUE_TYPE, ArnicaLib.MOD_ID);

    public static final DeferredHolder<MapCodec<? extends LevelBasedValue>, MapCodec<HLevelBasedValue.Sum>> SUM = TYPES.register("sum", () -> HLevelBasedValue.Sum.CODEC);

}
