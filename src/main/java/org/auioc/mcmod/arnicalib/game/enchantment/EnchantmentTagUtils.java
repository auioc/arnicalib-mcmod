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
