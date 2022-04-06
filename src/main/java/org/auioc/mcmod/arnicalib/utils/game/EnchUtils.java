package org.auioc.mcmod.arnicalib.utils.game;

import org.apache.commons.lang3.RandomUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.ForgeRegistries;

public interface EnchUtils {
    static Enchantment getEnchantment(String id) {
        return ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation(id));
    }


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
        int highestIndex = 0;
        int highestLevel = 0;
        for (int i = 0, l = enchantments.size(); i < l; i++) {
            if (enchantments.getCompound(i).getShort("lvl") > highestLevel) {
                highestIndex = i;
            }
        }
        return enchantments.getCompound(highestIndex);
    }

    static boolean isOverLimit(ListTag enchantments) {
        for (int i = 0, l = enchantments.size(); i < l; i++) {
            CompoundTag ench = enchantments.getCompound(i);
            if (ench.getShort("lvl") > (EnchUtils.getEnchantment(ench.getString("id"))).getMaxLevel()) {
                return true;
            }
        }
        return false;
    }

}
