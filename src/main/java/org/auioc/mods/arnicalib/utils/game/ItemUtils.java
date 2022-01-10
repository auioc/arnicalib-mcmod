package org.auioc.mods.arnicalib.utils.game;

import javax.annotation.Nullable;
import org.auioc.mods.arnicalib.utils.java.Validate;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

public interface ItemUtils {

    static Item getItem(ResourceLocation id) {
        Validate.isTrue(ForgeRegistries.ITEMS.containsKey(id), "Unknown item '" + id + "'");
        return ForgeRegistries.ITEMS.getValue(id);
    }

    static Item getItem(String id) {
        return getItem(new ResourceLocation(id));
    }


    static String getRegistryName(Item item) {
        return item.getRegistryName().toString();
    }

    static String getRegistryName(ItemStack itemStack) {
        return getRegistryName(itemStack.getItem());
    }


    @SuppressWarnings("deprecation")
    static int getMaxStackSize(Item item) {
        return item.getMaxStackSize();
    }

    @SuppressWarnings("deprecation")
    static int getMaxDamage(Item item) {
        return item.getMaxDamage();
    }


    static String toString(ItemStack itemStack) {
        return String.format("%s%s * %d", getRegistryName(itemStack), (itemStack.hasTag()) ? itemStack.getTag() : "{}", itemStack.getCount());
    }


    static void damageItem(Player player, ItemStack itemStack) {
        itemStack.hurtAndBreak(1, player, (p) -> {
            p.broadcastBreakEvent(player.getUsedItemHand());
        });
    }


    static ItemStack createItemStack(Item item, int count, @Nullable CompoundTag nbt) {
        ItemStack itemStack = new ItemStack(item, count);
        if (nbt != null) {
            itemStack.setTag(nbt);
        }
        return itemStack;
    }

}
