package org.auioc.mcmod.arnicalib.game.enchantment;

import java.util.Map;
import java.util.function.Predicate;
import org.auioc.mcmod.arnicalib.game.enchantment.EnchantmentVisitor.BiEnchantmentVisitor;
import org.auioc.mcmod.arnicalib.game.enchantment.EnchantmentVisitor.QuadEnchantmentVisitor;
import org.auioc.mcmod.arnicalib.game.enchantment.EnchantmentVisitor.TriEnchantmentVisitor;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class EnchantmentIterator {

    public static void run(BiEnchantmentVisitor visitor, Map<Enchantment, Integer> enchMap) {
        for (var enchEntry : enchMap.entrySet()) visitor.accept(enchEntry.getKey(), enchEntry.getValue());
    }

    public static void runOnItem(BiEnchantmentVisitor visitor, ItemStack itemStack) {
        if (!itemStack.isEmpty()) run(visitor, EnchantmentHelper.getEnchantments(itemStack));
    }

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

}
