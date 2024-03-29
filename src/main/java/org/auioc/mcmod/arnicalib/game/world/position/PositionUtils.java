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

package org.auioc.mcmod.arnicalib.game.world.position;

import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.auioc.mcmod.arnicalib.base.random.RandomUtils;
import org.auioc.mcmod.arnicalib.game.block.BlockUtils;
import org.auioc.mcmod.arnicalib.game.random.GameRandomUtils;

import java.util.Optional;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Predicate;


public class PositionUtils {

    public static boolean isInWorldBounds(BlockPos pos, Level level) {
        return level.getWorldBorder().isWithinBounds(pos)
            && pos.getY() < level.getMaxBuildHeight()
            && pos.getY() >= level.getMinBuildHeight();
    }

    public static void iterateY(int x, int z, int minY, int maxY, Consumer<BlockPos> consumer) {
        var pos = new MutableBlockPos(x, maxY, z);
        while (pos.getY() >= minY) {
            consumer.accept(pos);
            pos.move(Direction.DOWN);
        }
    }

    /**
     * @param predicate return false to break loop
     */
    public static boolean iterateY(int x, int z, int minY, int maxY, Predicate<BlockPos> predicate) {
        var pos = new MutableBlockPos(x, maxY, z);
        while (pos.getY() >= minY) {
            if (!predicate.test(pos)) {
                return false;
            }
            pos.move(Direction.DOWN);
        }
        return true;
    }

    public static boolean canStandOn(BlockPos pos, Level level) {
        return BlockUtils.canStandOn(level.getBlockState(pos));
    }

    public static boolean canStand(BlockPos pos, Level level) {
        return canStandOn(pos.below(), level);
    }

    public static Optional<Integer> findStandableY(Level level, int x, int z, int minY, int maxY) {
        minY = Math.max(minY, level.getMinBuildHeight());
        maxY = Math.min(maxY, level.getMaxBuildHeight() - 1);
        var pos = new MutableBlockPos(x, maxY, z);
        while (pos.getY() >= minY) {
            if (PositionUtils.canStand(pos, level)) {
                return Optional.of(pos.getY());
            }
            pos.move(Direction.DOWN);
        }
        return Optional.empty();
    }

    public static Optional<Integer> findStandableY(Level level, int x, int z) {
        return findStandableY(level, x, z, level.getMinBuildHeight(), level.getMaxBuildHeight() - 1);
    }

    // ============================================================================================================== //

    public static Vec3i random(Vec3i center, int radius, Random random) {
        radius += 1;
        return new Vec3i(
            center.getX() + RandomUtils.offset(radius, random),
            center.getY() + RandomUtils.offset(radius, random),
            center.getZ() + RandomUtils.offset(radius, random)
        );
    }

    public static Vec3 random(Vec3 center, double radius, Random random) {
        radius += 1.0D;
        return new Vec3(
            center.x + RandomUtils.offset(radius, random),
            center.y + RandomUtils.offset(radius, random),
            center.z + RandomUtils.offset(radius, random)
        );
    }

    public static BlockPos random(BlockPos center, int radius, Random random) {
        return new BlockPos(random((Vec3i) center, radius, random));
    }

    public static Vec3i random(Vec3i center, int radius, RandomSource random) {
        radius += 1;
        return new Vec3i(
            center.getX() + GameRandomUtils.offset(radius, random),
            center.getY() + GameRandomUtils.offset(radius, random),
            center.getZ() + GameRandomUtils.offset(radius, random)
        );
    }

    public static Vec3 random(Vec3 center, double radius, RandomSource random) {
        radius += 1.0D;
        return new Vec3(
            center.x + GameRandomUtils.offset(radius, random),
            center.y + GameRandomUtils.offset(radius, random),
            center.z + GameRandomUtils.offset(radius, random)
        );
    }

    public static BlockPos random(BlockPos center, int radius, RandomSource random) {
        return new BlockPos(random((Vec3i) center, radius, random));
    }

}
