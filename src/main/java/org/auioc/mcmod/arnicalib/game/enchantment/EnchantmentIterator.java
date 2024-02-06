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

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.auioc.mcmod.arnicalib.game.enchantment.visitor.BiEnchantmentVisitor;
import org.auioc.mcmod.arnicalib.game.enchantment.visitor.QuadEnchantmentVisitor;
import org.auioc.mcmod.arnicalib.game.enchantment.visitor.TriEnchantmentVisitor;

import java.util.Map;
import java.util.function.Predicate;

public class EnchantmentIterator {

    public static void run(BiEnchantmentVisitor visitor, Map<Enchantment, Integer> enchMap) {
        for (var enchEntry : enchMap.entrySet()) visitor.accept(enchEntry.getKey(), enchEntry.getValue());
    }

    // ====================================================================== //

    public static void runOnItem(BiEnchantmentVisitor visitor, ItemStack itemStack) {
        if (!itemStack.isEmpty()) run(visitor, EnchantmentHelper.getEnchantments(itemStack));
    }

    // ====================================================================== //

    public static void runOnItems(TriEnchantmentVisitor visitor, Iterable<ItemStack> itemStacks, Predicate<ItemStack> predicate) {
        for (var itemStack : itemStacks) {
            if (!itemStack.isEmpty() && predicate.test(itemStack)) {
                runOnItem((ench, lvl) -> visitor.accept(itemStack, ench, lvl), itemStack);
            }
        }
    }

    public static void runOnItems(TriEnchantmentVisitor visitor, Iterable<ItemStack> itemStacks) {
        runOnItems(visitor, itemStacks, (o) -> true);
    }

    // ====================================================================== //

    public static void runOnLiving(QuadEnchantmentVisitor visitor, LivingEntity living, EquipmentSlot[] slots) {
        for (EquipmentSlot slot : slots) {
            var itemStack = living.getItemBySlot(slot);
            runOnItem((ench, lvl) -> {
                if (ench instanceof IValidSlotsVisibleEnchantment _ench) {
                    if (_ench.isValidSlot(slot)) {
                        visitor.accept(slot, itemStack, ench, lvl);
                    }
                } else {
                    visitor.accept(slot, itemStack, ench, lvl);
                }
            }, itemStack);
        }
    }

    public static void runOnLiving(QuadEnchantmentVisitor visitor, LivingEntity living) {
        runOnLiving(visitor, living, EquipmentSlot.values());
    }

    // ====================================================================== //

    public static void runOnInventory(TriEnchantmentVisitor visitor, Inventory inventory) {
        runOnItems(visitor, inventory.items);
        runOnItems(visitor, inventory.offhand);
        runOnItems(visitor, inventory.armor);
    }

}
