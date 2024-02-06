/*
 * Copyright (C) 2022-2024 AUIOC.ORG
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

package org.auioc.mcmod.arnicalib.game.entity;

import java.util.function.Predicate;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class EntityTypePredicates {

    public static final Predicate<EntityType<?>> IS_FRIENDLY = (type) -> getCategory(type).isFriendly();
    public static final Predicate<EntityType<?>> IS_PERSISTENT = (type) -> getCategory(type).isPersistent();

    public static final Predicate<EntityType<?>> IS_MISC = (type) -> getCategory(type) == MobCategory.MISC;
    public static final Predicate<EntityType<?>> IS_MONSTER = (type) -> getCategory(type) == MobCategory.MONSTER;
    public static final Predicate<EntityType<?>> IS_CREATURE = (type) -> getCategory(type) == MobCategory.CREATURE;
    public static final Predicate<EntityType<?>> IS_AMBIENT = (type) -> getCategory(type) == MobCategory.AMBIENT;
    public static final Predicate<EntityType<?>> IS_AXOLOTLS = (type) -> getCategory(type) == MobCategory.AXOLOTLS;
    public static final Predicate<EntityType<?>> IS_UNDERGROUND_WATER_CREATURE = (type) -> getCategory(type) == MobCategory.UNDERGROUND_WATER_CREATURE;
    public static final Predicate<EntityType<?>> IS_WATER_CREATURE = (type) -> getCategory(type) == MobCategory.WATER_CREATURE;
    public static final Predicate<EntityType<?>> IS_WATER_AMBIENT = (type) -> getCategory(type) == MobCategory.WATER_AMBIENT;

    private static MobCategory getCategory(EntityType<?> entityType) {
        return entityType.getCategory();
    }

}
