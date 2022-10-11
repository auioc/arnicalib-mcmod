package org.auioc.mcmod.arnicalib.game.enchantment;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang3.Validate;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class EnchantmentUtils {

    public static List<Entry<Enchantment, Integer>> sortByLevel(Map<Enchantment, Integer> enchantmentMap) {
        Validate.notEmpty(enchantmentMap);
        return enchantmentMap.entrySet().stream().sorted(Entry.comparingByValue()).toList();
    }

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


    public static boolean isOverLimit(Enchantment enchantment, int level) {
        return level > enchantment.getMaxLevel();
    }

    public static boolean isOverLimit(Entry<Enchantment, Integer> enchantmentEntry) {
        return isOverLimit(enchantmentEntry.getKey(), enchantmentEntry.getValue());
    }

    public static boolean hasOverLimitEnchantment(Map<Enchantment, Integer> enchantmentMap) {
        for (var enchantmentEntry : enchantmentMap.entrySet()) {
            if (isOverLimit(enchantmentEntry)) return true;
        }
        return false;
    }

    public static boolean hasOverLimitEnchantment(ListTag enchantmentsTag) {
        return hasOverLimitEnchantment(EnchantmentHelper.deserializeEnchantments(enchantmentsTag));
    }

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

}
