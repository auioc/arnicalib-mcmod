/*
 * Copyright (C) 2025 AUIOC.ORG
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

package org.auioc.mcmod.arnicalib.game.event;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.Event;
import net.neoforged.neoforge.common.NeoForge;
import org.auioc.mcmod.arnicalib.mod.event.AHEventHooks;

import javax.annotation.Nullable;
import java.util.function.Consumer;

/**
 * Fired on the {@link NeoForge#EVENT_BUS} on <b>SERVER</b> side only.
 *
 * @see ItemStack#applyDamage(int, LivingEntity, Consumer)
 * @see AHEventHooks#onApplyItemDamage
 * @since 7.0.0
 */
public class ItemDamageEvent extends Event {

    private final ItemStack item;
    private final int originalDamage;
    private int newDamage;
    @Nullable
    private final LivingEntity living;

    public ItemDamageEvent(ItemStack item, int newDamage, @Nullable LivingEntity living) {
        this.item = item;
        this.originalDamage = item.getDamageValue();
        this.newDamage = newDamage;
        this.living = living;
    }

    public ItemStack getItem() {
        return item;
    }

    public int getOriginalDamage() {
        return originalDamage;
    }

    public int getNewDamage() {
        return newDamage;
    }

    public void setNewDamage(int newDamage) {
        this.newDamage = newDamage;
    }

    @Nullable
    public LivingEntity getEntity() {
        return living;
    }

}
