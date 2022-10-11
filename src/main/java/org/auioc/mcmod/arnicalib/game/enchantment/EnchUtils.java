package org.auioc.mcmod.arnicalib.game.enchantment;

import org.auioc.mcmod.arnicalib.base.random.RandomUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;

@Deprecated(since = "5.6.2", forRemoval = true)
public interface EnchUtils {

    static void enchantOne(ListTag enchantments, int index, int level) {
        EnchantmentTagUtils.increaseLevel(enchantments, index, level);
    }

    static void enchantOne(CompoundTag enchantment, int level) {
        EnchantmentTagUtils.increaseLevel(enchantment, level);
    }

    static void enchantAll(ListTag enchantments, int level) {
        EnchantmentTagUtils.increaseLevel(enchantments, level);
    }

    static void enchantRandom(ListTag enchantments, int level) {
        EnchantmentTagUtils.increaseLevel(enchantments.getCompound(RandomUtils.nextInt(0, enchantments.size())), level);
    }

    static CompoundTag getHighestEnchantment(ListTag enchantments) {
        return EnchantmentTagUtils.getHighest(enchantments);
    }

    static boolean isOverLimit(ListTag enchantments) {
        return EnchantmentTagUtils.hasOverLimitEnchantment(enchantments);
    }

}
