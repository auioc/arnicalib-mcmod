package org.auioc.mcmod.arnicalib.game.enchantment;

import org.apache.commons.lang3.RandomUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;

public interface EnchUtils {

    static void enchantOne(ListTag enchantments, int index, int level) {
        CompoundTag nbt = enchantments.getCompound(index);
        nbt.putShort("lvl", (short) (nbt.getShort("lvl") + level));
    }

    static void enchantOne(CompoundTag enchantment, int level) {
        enchantment.putShort("lvl", (short) (enchantment.getShort("lvl") + level));
    }

    static void enchantAll(ListTag enchantments, int level) {
        for (int i = 0; i < enchantments.size(); i++) {
            enchantOne(enchantments.getCompound(i), level);
        }
    }

    static void enchantRandom(ListTag enchantments, int level) {
        enchantOne(enchantments.getCompound(RandomUtils.nextInt(0, enchantments.size())), level);
    }


    static CompoundTag getHighestEnchantment(ListTag enchantments) {
        int index = 0;
        short highestLevel = 0;
        for (int i = 0, l = enchantments.size(); i < l; i++) {
            short lvl = enchantments.getCompound(i).getShort("lvl");
            if (lvl > highestLevel) {
                index = i;
                highestLevel = lvl;
            }
        }
        return enchantments.getCompound(index);
    }

    static boolean isOverLimit(ListTag enchantments) {
        for (int i = 0, l = enchantments.size(); i < l; i++) {
            CompoundTag ench = enchantments.getCompound(i);
            if (ench.getShort("lvl") > (EnchantmentRegistry.getOrElseThrow(ench.getString("id"))).getMaxLevel()) {
                return true;
            }
        }
        return false;
    }

}
