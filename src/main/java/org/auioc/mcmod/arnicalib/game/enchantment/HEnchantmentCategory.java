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

package org.auioc.mcmod.arnicalib.game.enchantment;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.FoodOnAStickItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SpyglassItem;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class HEnchantmentCategory {

    public static final EnchantmentCategory HOE = EnchantmentCategory.create("HOE", (item) -> item instanceof HoeItem);
    public static final EnchantmentCategory AXE = EnchantmentCategory.create("AXE", (item) -> item instanceof AxeItem);
    public static final EnchantmentCategory SHOVEL = EnchantmentCategory.create("SHOVEL", (item) -> item instanceof ShovelItem);
    public static final EnchantmentCategory PICKAXE = EnchantmentCategory.create("PICKAXE", (item) -> item instanceof PickaxeItem);

    public static final EnchantmentCategory SHIELD = EnchantmentCategory.create("SHIELD", (item) -> item instanceof ShieldItem);

    public static final EnchantmentCategory ELYTRA = EnchantmentCategory.create("ELYTRA", (item) -> item instanceof ElytraItem);

    public static final EnchantmentCategory FOOD_ON_A_STACK = EnchantmentCategory.create("FOOD_ON_A_STACK", (item) -> item instanceof FoodOnAStickItem);

    public static final EnchantmentCategory FLINT_AND_STEEL = EnchantmentCategory.create("FLINT_AND_STEEL", (item) -> item instanceof FlintAndSteelItem);

    public static final EnchantmentCategory SPYGLASS = EnchantmentCategory.create("SPYGLASS", (item) -> item instanceof SpyglassItem);

}
