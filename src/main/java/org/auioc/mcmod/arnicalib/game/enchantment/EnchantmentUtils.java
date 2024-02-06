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

import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import org.apache.commons.lang3.Validate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class EnchantmentUtils {

    public static List<Entry<Enchantment, Integer>> sortByLevel(Map<Enchantment, Integer> enchantmentMap) {
        Validate.notEmpty(enchantmentMap);
        return enchantmentMap.entrySet().stream().sorted(Entry.comparingByValue()).toList();
    }

    // ====================================================================== //

    public static Entry<Enchantment, Integer> getHighest(Map<Enchantment, Integer> enchantmentMap) {
        return sortByLevel(enchantmentMap).get(0);
    }

    public static Entry<Enchantment, Integer> getLowest(Map<Enchantment, Integer> enchantmentMap) {
        var sorted = sortByLevel(enchantmentMap);
        return sorted.get(sorted.size() - 1);
    }

    public static Entry<Enchantment, Integer> getHighest(ListTag enchantmentsTag) {
        return getHighest(EnchantmentHelper.deserializeEnchantments(enchantmentsTag));
    }

    public static Entry<Enchantment, Integer> getLowest(ListTag enchantmentsTag) {
        return getLowest(EnchantmentHelper.deserializeEnchantments(enchantmentsTag));
    }

    // ============================================================================================================== //

    public static boolean isOverLimit(Enchantment enchantment, int level) {
        return level > enchantment.getMaxLevel();
    }

    public static boolean isOverLimit(Entry<Enchantment, Integer> enchantmentEntry) {
        return isOverLimit(enchantmentEntry.getKey(), enchantmentEntry.getValue());
    }

    // ====================================================================== //

    public static boolean hasOverLimitEnchantment(Map<Enchantment, Integer> enchantmentMap) {
        for (var enchantmentEntry : enchantmentMap.entrySet()) {
            if (isOverLimit(enchantmentEntry)) return true;
        }
        return false;
    }

    public static boolean hasOverLimitEnchantment(ListTag enchantmentsTag) {
        return hasOverLimitEnchantment(EnchantmentHelper.deserializeEnchantments(enchantmentsTag));
    }

    // ====================================================================== //

    public static LinkedHashMap<Enchantment, Integer> getOverLimitEnchantments(Map<Enchantment, Integer> enchantmentMap) {
        var map = new LinkedHashMap<Enchantment, Integer>();
        for (var enchantmentEntry : enchantmentMap.entrySet()) {
            if (isOverLimit(enchantmentEntry)) {
                map.put(enchantmentEntry.getKey(), enchantmentEntry.getValue());
            }
        }
        return map;
    }

    public static LinkedHashMap<Enchantment, Integer> getOverLimitEnchantments(ListTag enchantmentsTag) {
        return getOverLimitEnchantments(EnchantmentHelper.deserializeEnchantments(enchantmentsTag));
    }

    // ============================================================================================================== //

    public static ItemStack createBook(Enchantment enchantment, int lvl) {
        return EnchantedBookItem.createForEnchantment(new EnchantmentInstance(enchantment, lvl));
    }

    public static List<ItemStack> createBooks(Enchantment enchantment) {
        int maxLevel = enchantment.getMaxLevel();
        var list = new ArrayList<ItemStack>(maxLevel);
        for (int lvl = 1; lvl <= maxLevel; ++lvl) {
            list.add(createBook(enchantment, lvl));
        }
        return list;
    }

}
