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
import net.minecraft.world.item.ItemStack;

public class FishingRodCastEvent extends ServerPlayerEvent {

    private final ItemStack fishingRod;
    private int speedBonus;
    private int luckBonus;

    public FishingRodCastEvent(ServerPlayer serverPlayer, ItemStack fishingRod, int speedBonus, int luckBonus) {
        super(serverPlayer);
        this.fishingRod = fishingRod;
        this.speedBonus = speedBonus;
        this.luckBonus = luckBonus;
    }

    public ItemStack getFishingRod() {
        return fishingRod;
    }

    public int getSpeedBonus() {
        return speedBonus;
    }

    public void setSpeedBonus(int speedBonus) {
        this.speedBonus = speedBonus;
    }

    public int getLuckBonus() {
        return luckBonus;
    }

    public void setLuckBonus(int luckBonus) {
        this.luckBonus = luckBonus;
    }

}
