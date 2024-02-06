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

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;

public class EnchantmentTagUtils {

    public static void increaseLevel(ListTag tag, int index, int level) {
        CompoundTag nbt = tag.getCompound(index);
        nbt.putShort("lvl", (short) (nbt.getShort("lvl") + level));
    }

    public static void increaseLevel(ListTag tag, int level) {
        for (int i = 0; i < tag.size(); i++) increaseLevel(tag.getCompound(i), level);
    }

    public static void increaseLevel(CompoundTag tag, int level) {
        tag.putShort("lvl", (short) (tag.getShort("lvl") + level));
    }

    // ====================================================================== //

    public static CompoundTag getHighest(ListTag tag) {
        int index = 0;
        short highestLevel = 0;
        for (int i = 0, l = tag.size(); i < l; i++) {
            short lvl = tag.getCompound(i).getShort("lvl");
            if (lvl > highestLevel) {
                index = i;
                highestLevel = lvl;
            }
        }
        return tag.getCompound(index);
    }

    // ====================================================================== //

    public static boolean hasOverLimitEnchantment(ListTag tag) {
        for (int i = 0, l = tag.size(); i < l; i++) {
            CompoundTag ench = tag.getCompound(i);
            if (ench.getShort("lvl") > (EnchantmentRegistry.getOrElseThrow(ench.getString("id"))).getMaxLevel()) {
                return true;
            }
        }
        return false;
    }

    // ====================================================================== //

    public static int calcTotalLevel(ListTag tag) {
        int totalLvl = 0;
        for (var enchEntry : tag) {
            if (enchEntry.getId() == Tag.TAG_COMPOUND) {
                totalLvl += ((CompoundTag) enchEntry).getInt("lvl");
            }
        }
        return totalLvl;
    }

}
