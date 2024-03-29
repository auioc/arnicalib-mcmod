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

import org.auioc.mcmod.arnicalib.game.world.LevelUtils;
import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class EntityUtils {

    public static void teleportTo(Entity entity, Vec3i pos) {
        entity.teleportTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D);
    }

    public static void teleportTo(Entity entity, Vec3 pos) {
        entity.teleportTo(pos.x, pos.y, pos.z);
    }

    public static void teleportTo(Entity entity, ResourceKey<Level> dim, Vec3 pos) {
        entity.changeDimension(LevelUtils.getLevel(dim), LevelUtils.createSimpleTeleporter(pos));
    }

    public static void teleportTo(Entity entity, ResourceKey<Level> dim, Vec3i pos) {
        entity.changeDimension(LevelUtils.getLevel(dim), LevelUtils.createSimpleTeleporter(pos));
    }


    public static Vec3 calcVelocity(Entity entity) {
        double vX = entity.getX() - entity.xOld;
        double vY = entity.getY() - entity.yOld;
        double vZ = entity.getZ() - entity.zOld;
        return new Vec3(vX, vY, vZ);
    }

    public static double calcSpeed(Entity entity) {
        return calcVelocity(entity).length();
    }


    public static Component getFacing8WindDirection(Entity entity) {
        float yaw = Mth.wrapDegrees(entity.getYRot());
        int d8 = (int) Math.floor((yaw - 180.0F) / 45.0F + 0.5F) & 7;
        return Component.translatable("direction.8wind." + d8);
    }


    public static Component getAxolotlVariantName(Axolotl.Variant variant) {
        return Component.translatable("entity.minecraft.axolotl.variant." + variant.getName());
    }

}
