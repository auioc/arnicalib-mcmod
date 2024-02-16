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
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

public class PlayerEatEvent extends PlayerEvent implements ICancellableEvent {

    private final ItemStack food;
    private final FoodData foodData;
    private int nutrition = 0;
    private float saturationModifier = 0.0F;

    public PlayerEatEvent(Player player, ItemStack food, FoodData foodData) {
        super(player);
        this.food = food;
        this.foodData = foodData;
        if (food.isEdible()) {
            var p = food.getFoodProperties(player);
            if (p != null) {
                this.nutrition = p.getNutrition();
                this.saturationModifier = p.getSaturationModifier();
            }
        }
    }

    public ItemStack getFood() {
        return food;
    }

    public FoodData getFoodData() {
        return foodData;
    }

    public int getNutrition() {
        return this.nutrition;
    }

    public float getSaturationModifier() {
        return this.saturationModifier;
    }

    public void setNutrition(int nutrition) {
        this.nutrition = nutrition;
    }

    public void setSaturationModifier(float saturationModifier) {
        this.saturationModifier = saturationModifier;
    }

}
