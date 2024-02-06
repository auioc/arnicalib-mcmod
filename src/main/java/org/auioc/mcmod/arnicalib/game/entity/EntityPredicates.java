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

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.neoforged.neoforge.common.util.FakePlayer;

import java.util.function.Predicate;

public class EntityPredicates {

    public static final Predicate<Entity> IS_LIVING = LivingEntity.class::isInstance;
    public static final Predicate<Entity> IS_PLAYER = Player.class::isInstance;
    public static final Predicate<Entity> IS_LOCAL_PLAYER = LocalPlayer.class::isInstance;
    public static final Predicate<Entity> IS_SERVER_PLAYER = ServerPlayer.class::isInstance;
    public static final Predicate<Entity> IS_FAKE_PLAYER = FakePlayer.class::isInstance;
    public static final Predicate<Entity> IS_PROJECTILE = Projectile.class::isInstance;

    public static final Predicate<Entity> IS_FRIENDLY = (entity) -> getCategory(entity).isFriendly();
    public static final Predicate<Entity> IS_PERSISTENT = (entity) -> getCategory(entity).isPersistent();
    public static final Predicate<Entity> IS_MISC = (entity) -> getCategory(entity) == MobCategory.MISC;
    public static final Predicate<Entity> IS_MONSTER = (entity) -> getCategory(entity) == MobCategory.MONSTER;
    public static final Predicate<Entity> IS_CREATURE = (entity) -> getCategory(entity) == MobCategory.CREATURE;
    public static final Predicate<Entity> IS_AMBIENT = (entity) -> getCategory(entity) == MobCategory.AMBIENT;
    public static final Predicate<Entity> IS_AXOLOTLS = (entity) -> getCategory(entity) == MobCategory.AXOLOTLS;
    public static final Predicate<Entity> IS_UNDERGROUND_WATER_CREATURE = (entity) -> getCategory(entity) == MobCategory.UNDERGROUND_WATER_CREATURE;
    public static final Predicate<Entity> IS_WATER_CREATURE = (entity) -> getCategory(entity) == MobCategory.WATER_CREATURE;
    public static final Predicate<Entity> IS_WATER_AMBIENT = (entity) -> getCategory(entity) == MobCategory.WATER_AMBIENT;

    public static final Predicate<LivingEntity> IS_UNDEFINED = (living) -> living.getMobType() == MobType.UNDEFINED;
    public static final Predicate<LivingEntity> IS_DEFINED = (living) -> living.getMobType() != MobType.UNDEFINED;
    public static final Predicate<LivingEntity> IS_UNDEAD = (living) -> living.getMobType() == MobType.UNDEAD;
    public static final Predicate<LivingEntity> IS_ARTHROPOD = (living) -> living.getMobType() == MobType.ARTHROPOD;
    public static final Predicate<LivingEntity> IS_ILLAGER = (living) -> living.getMobType() == MobType.ILLAGER;
    public static final Predicate<LivingEntity> IS_WATER = (living) -> living.getMobType() == MobType.WATER;

    private static MobCategory getCategory(Entity entity) {
        return entity.getType().getCategory();
    }

}
