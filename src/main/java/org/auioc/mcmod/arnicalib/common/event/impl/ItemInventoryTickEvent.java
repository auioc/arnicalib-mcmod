package org.auioc.mcmod.arnicalib.common.event.impl;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Cancelable;

public class ItemInventoryTickEvent extends PlayerEvent {

    private final Level level;
    private final ItemStack itemStack;
    private final int index;
    private final boolean selected;

    public ItemInventoryTickEvent(Player player, Level level, ItemStack itemStack, int index, boolean selected) {
        super(player);
        this.level = level;
        this.itemStack = itemStack;
        this.index = index;
        this.selected = selected;
    }

    public Level getLevel() {
        return this.level;
    }

    public ItemStack getItemStack() {
        return this.itemStack;
    }

    public int getIndex() {
        return this.index;
    }

    public boolean isSelected() {
        return this.selected;
    }

    @Cancelable
    public static class Selected extends ItemInventoryTickEvent {

        public Selected(Player player, Level level, ItemStack itemStack, int index) {
            super(player, level, itemStack, index, true);
        }

    }

}
