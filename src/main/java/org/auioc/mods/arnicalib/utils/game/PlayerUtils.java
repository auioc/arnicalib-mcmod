package org.auioc.mods.arnicalib.utils.game;

import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeMod;

public interface PlayerUtils {

    @SuppressWarnings("deprecation")
    static void giveItem(ServerPlayer player, Item item, @Nullable CompoundTag nbt, int count) {
        int i = count;
        while (i > 0) {
            int j = Math.min(item.getMaxStackSize(), i);
            i -= j;
            ItemStack itemStack = ItemUtils.createItemStack(item, nbt, j);
            boolean flag = player.getInventory().add(itemStack);
            ItemEntity itementity = player.drop(itemStack, false);
            if (flag && itemStack.isEmpty()) {
                itemStack.setCount(1);
                if (itementity != null) {
                    itementity.makeFakeItem();
                }
                player.inventoryMenu.broadcastChanges();
            } else {
                if (itementity != null) {
                    itementity.setNoPickUpDelay();
                    itementity.setOwner(player.getUUID());
                }
            }
        }
    }

    static void giveItem(ServerPlayer player, ItemStack itemStack) {
        giveItem(player, itemStack.getItem(), itemStack.getTag(), itemStack.getCount());
    }

    static String toString(Player player) {
        return String.format(
            "%s(%s) at %s in %s",
            player.getName().getString(),
            player.getStringUUID(),
            player.position().toString(),
            (player.level == null) ? "~NULL~" : player.level.toString()
        );
    }

    static void setPlayerReach(Player player, int reach) {
        player.getAttribute(ForgeMod.REACH_DISTANCE.get()).setBaseValue(reach);
    }

}
