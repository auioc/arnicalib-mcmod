package org.auioc.mcmod.arnicalib.game.entity.player;

import javax.annotation.Nullable;
import org.auioc.mcmod.arnicalib.game.item.ItemUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeMod;

public interface PlayerUtils {


    /**
     * Gives the specified item to the specified player.
     * If the player's inventory can not hold it, the itemstack(s) will be dropped in the world at the player's position.
     *
     * @param player    the player to give the item to
     * @param item      the item to give
     * @param count     the count of the item
     * @param nbt       the nbt of the item
     * @param fakeItem  if {@code true}, will generate a fake {@link ItemEntity} at the player's position on success
     * @param playSound if {@code true}, will play the {@link SoundEvents#ITEM_PICKUP} at the player's position on success
     * @since 5.1.4
     */
    static void giveItem(ServerPlayer player, Item item, int count, @Nullable CompoundTag nbt, boolean fakeItem, boolean playSound) {
        int maxStackSize = ItemUtils.getMaxStackSize(item);
        int remainingCount = count;
        while (remainingCount > 0) {
            int stackSize = Math.min(maxStackSize, remainingCount);
            remainingCount -= stackSize;
            var itemStack = ItemUtils.createItemStack(item, stackSize, nbt);
            if (player.getInventory().add(itemStack) && itemStack.isEmpty()) {
                if (fakeItem) {
                    itemStack.setCount(1);
                    var itemEntity = player.drop(itemStack, false);
                    if (itemEntity != null) itemEntity.makeFakeItem();
                }
                if (playSound) {
                    player.level.playSound(
                        (Player) null, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS, 0.2F,
                        ((player.getRandom().nextFloat() - player.getRandom().nextFloat()) * 0.7F + 1.0F) * 2.0F
                    );
                }
                player.inventoryMenu.broadcastChanges();
            } else {
                var itemEntity = player.drop(itemStack, false);
                if (itemEntity != null) {
                    itemEntity.setNoPickUpDelay();
                    itemEntity.setOwner(player.getUUID());
                }
            }
        }
    }

    static void giveItem(ServerPlayer player, Item item) {
        giveItem(player, item, 1, null, true, true);
    }

    static void giveItem(ServerPlayer player, ItemStack itemStack) {
        giveItem(player, itemStack.getItem(), itemStack.getCount(), itemStack.getTag(), true, true);
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

    static boolean isOp(ServerPlayer player) {
        return player.getServer().getPlayerList().isOp(player.getGameProfile());
    }

}
