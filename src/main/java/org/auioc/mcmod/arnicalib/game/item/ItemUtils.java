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

package org.auioc.mcmod.arnicalib.game.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.auioc.mcmod.arnicalib.game.registry.RegistryUtils;

import javax.annotation.Nullable;

public class ItemUtils {

    @SuppressWarnings("deprecation")
    public static int getMaxStackSize(Item item) {
        return item.getMaxStackSize();
    }

    @SuppressWarnings("deprecation")
    public static int getMaxDamage(Item item) {
        return item.getMaxDamage();
    }

    public static ItemStack createItemStack(Item item, int count, @Nullable CompoundTag nbt) {
        ItemStack itemStack = new ItemStack(item, count);
        if (nbt != null) {
            itemStack.setTag(nbt);
        }
        return itemStack;
    }

    public static void hurt(Player player, ItemStack itemStack) {
        itemStack.hurtAndBreak(
            1, player, (p) -> {
                p.broadcastBreakEvent(player.getUsedItemHand());
            }
        );
    }

    public static String toString(ItemStack itemStack) {
        return String.format(
            "%s%s * %d",
            toString(itemStack.getItem()),
            itemStack.hasTag() ? itemStack.getTag() : "{}",
            itemStack.getCount()
        );
    }

    public static String toString(Item item) {
        return RegistryUtils.id(item).toString();
    }

}
