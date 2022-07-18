package org.auioc.mcmod.arnicalib.utils.game;

import java.util.Optional;
import javax.annotation.Nonnull;
import org.apache.commons.lang3.RandomUtils;
import org.auioc.mcmod.arnicalib.api.game.registry.RegistryEntryException;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
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

}
