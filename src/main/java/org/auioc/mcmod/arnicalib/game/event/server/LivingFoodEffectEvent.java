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

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.neoforge.event.entity.living.LivingEvent;

import java.util.List;

public class LivingFoodEffectEvent extends LivingEvent implements ICancellableEvent {

    private final ItemStack food;
    private final List<MobEffectInstance> effects;

    public LivingFoodEffectEvent(LivingEntity living, ItemStack food, List<MobEffectInstance> effects) {
        super(living);
        this.food = food;
        this.effects = effects;
    }

    public ItemStack getFood() {
        return food;
    }

    public List<MobEffectInstance> getEffects() {
        return effects;
    }

}
