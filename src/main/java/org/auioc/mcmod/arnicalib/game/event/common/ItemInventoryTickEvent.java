/*
 * Copyright (C) 2024 AUIOC.ORG
 *
 * This file is part of ArnicaLib, a mod made for Minecraft.
 *
 * ArnicaLib is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <https://www.gnu.org/licenses/>.
 */

package org.auioc.mcmod.arnicalib.game.event.common;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

public class ItemInventoryTickEvent extends PlayerEvent implements ICancellableEvent {

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

}
