package org.auioc.mcmod.arnicalib.game.item;

import javax.annotation.Nullable;
import org.auioc.mcmod.arnicalib.game.registry.RegistryUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemUtils {

    @SuppressWarnings("deprecation")
    public static int getMaxStackSize(Item item) {
        return item.getMaxStackSize();
    }

    @SuppressWarnings("deprecation")
    public static int getMaxDamage(Item item) {
        return item.getMaxDamage();
    }

    public static ItemStack createItemStack(Item item, int count, @Nullable CompoundTag nbt) {
        ItemStack itemStack = new ItemStack(item, count);
        if (nbt != null) {
            itemStack.setTag(nbt);
        }
        return itemStack;
    }

    public static void hurt(Player player, ItemStack itemStack) {
        itemStack.hurtAndBreak(
            1, player, (p) -> {
                p.broadcastBreakEvent(player.getUsedItemHand());
            }
        );
    }

    public static String toString(ItemStack itemStack) {
        return String.format("%s%s * %d", toString(itemStack), (itemStack.hasTag()) ? itemStack.getTag() : "{}", itemStack.getCount());
    }

    public static String toString(Item item) {
        return RegistryUtils.id(item).toString();
    }

}
