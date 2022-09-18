package org.auioc.mcmod.arnicalib.utils.game;

import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import javax.annotation.Nonnull;
import org.apache.commons.lang3.RandomUtils;
import org.auioc.mcmod.arnicalib.api.game.enchantment.EnchantmentVisitor.BiEnchantmentVisitor;
import org.auioc.mcmod.arnicalib.api.game.enchantment.EnchantmentVisitor.QuadEnchantmentVisitor;
import org.auioc.mcmod.arnicalib.api.game.enchantment.EnchantmentVisitor.TriEnchantmentVisitor;
import org.auioc.mcmod.arnicalib.api.game.enchantment.IValidSlotsVisibleEnchantment;
import org.auioc.mcmod.arnicalib.api.game.registry.RegistryEntryException;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.registries.ForgeRegistries;

public interface EnchUtils {

    @Nonnull
    static Optional<Enchantment> getEnchantment(ResourceLocation id) {
        return Optional.ofNullable(ForgeRegistries.ENCHANTMENTS.containsKey(id) ? ForgeRegistries.ENCHANTMENTS.getValue(id) : null);
    }

    @Nonnull
    static Optional<Enchantment> getEnchantment(String id) {
        return getEnchantment(new ResourceLocation(id));
    }

    @Nonnull
    static Enchantment getEnchantmentOrElseThrow(ResourceLocation id) {
        return getEnchantment(id).orElseThrow(RegistryEntryException.UNKNOWN_ENCHANTMENT.create(id.toString()));
    }

    @Nonnull
    static Enchantment getEnchantmentOrElseThrow(String id) {
        return getEnchantment(id).orElseThrow(RegistryEntryException.UNKNOWN_ENCHANTMENT.create(id.toString()));
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
            if (ench.getShort("lvl") > (getEnchantmentOrElseThrow(ench.getString("id"))).getMaxLevel()) {
                return true;
            }
        }
        return false;
    }


    static void runIteration(BiEnchantmentVisitor visitor, Map<Enchantment, Integer> enchMap) {
        for (var enchEntry : enchMap.entrySet()) visitor.accept(enchEntry.getKey(), enchEntry.getValue());
    }

    static void runIterationOnItem(BiEnchantmentVisitor visitor, ItemStack itemStack) {
        if (!itemStack.isEmpty()) runIteration(visitor, EnchantmentHelper.getEnchantments(itemStack));
    }

    static void runIterationOnItems(TriEnchantmentVisitor visitor, Iterable<ItemStack> itemStacks, Predicate<ItemStack> predicate) {
        for (var itemStack : itemStacks) {
            if (!itemStack.isEmpty() && predicate.test(itemStack)) {
                runIterationOnItem((ench, lvl) -> visitor.accept(itemStack, ench, lvl), itemStack);
            }
        }
    }

    static void runIterationOnItems(TriEnchantmentVisitor visitor, Iterable<ItemStack> itemStacks) {
        runIterationOnItems(visitor, itemStacks, (o) -> true);
    }

    static void runIterationOnLiving(QuadEnchantmentVisitor visitor, LivingEntity living, EquipmentSlot[] slots) {
        for (EquipmentSlot slot : slots) {
            var itemStack = living.getItemBySlot(slot);
            runIterationOnItem((ench, lvl) -> {
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

    static void runIterationOnLiving(QuadEnchantmentVisitor visitor, LivingEntity living) {
        runIterationOnLiving(visitor, living, EquipmentSlot.values());
    }

}
