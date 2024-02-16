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

package org.auioc.mcmod.arnicalib.mod.mixin.common;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.auioc.mcmod.arnicalib.mod.common.event.AHCommonEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = FoodData.class)
public abstract class MixinFoodData {

    /**
     * @author WakelessSloth56
     * @reason PlayerEatEvent {@link org.auioc.mcmod.arnicalib.game.event.common.PlayerEatEvent}
     */
    @Overwrite(remap = false)
    public void eat(Item p_38713_, ItemStack p_38714_, @org.jetbrains.annotations.Nullable LivingEntity entity) {
        // entity is always Player (or null), FoodData#eat is only called from net.minecraft.world.entity.player.Player#eat
        var event = AHCommonEventFactory.onPlayerEat((Player) entity, p_38714_, ((FoodData) (Object) this));
        if (!event.isCanceled()) {
            int nutrition = event.getNutrition();
            float saturationModifier = event.getSaturationModifier();
            if (nutrition != 0 || saturationModifier != 0.0F) {
                this.eat(nutrition, saturationModifier);
            }
        }
    }

    @Shadow
    public abstract void eat(int pFoodLevelModifier, float pSaturationLevelModifier);

}
