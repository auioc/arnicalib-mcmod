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

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.living.LivingEvent;

public class ProjectileWeaponReleaseEvent extends LivingEvent {

    private final ItemStack weapon;
    private final Projectile projectile;

    public ProjectileWeaponReleaseEvent(LivingEntity living, ItemStack weapon, Projectile projectile) {
        super(living);
        this.weapon = weapon;
        this.projectile = projectile;
    }

    public ItemStack getWeapon() {
        return weapon;
    }

    public Projectile getProjectile() {
        return projectile;
    }

}
