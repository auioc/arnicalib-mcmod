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

package org.auioc.mcmod.arnicalib.game.event.server;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

public class ItemHurtEvent extends ServerPlayerEvent {

    private final ItemStack itemStack;
    private final RandomSource random;
    private int damage;

    public ItemHurtEvent(ItemStack itemStack, int damage, RandomSource random, @Nullable ServerPlayer player) {
        super(player);
        this.itemStack = itemStack;
        this.damage = damage;
        this.random = random;
    }

    @Override
    @Nullable
    public ServerPlayer getServerPlayer() {
        return super.getServerPlayer();
    }

    public ItemStack getItemStack() {
        return this.itemStack;
    }

    public RandomSource getRandom() {
        return this.random;
    }

    public int getDamage() {
        return this.damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

}
