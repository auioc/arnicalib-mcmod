package org.auioc.mcmod.arnicalib.utils.game;

import java.util.Optional;
import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class RandomTeleporter {

    public static Optional<BlockPos> findSafePosition(Level level, BlockPos center, int radius, boolean surface, AABB aabb, Random random) {
        var pos = PositionUtils.random(center, radius, random).mutable();
        if (PositionUtils.isInWorldBounds(pos, level)) {
            var y = surface
                ? PositionUtils.findStandableY(level, pos.getX(), pos.getZ())
                : PositionUtils.findStandableY(level, pos.getX(), pos.getZ(), center.getY() - radius, pos.getY());
            if (y.isPresent()) {
                pos.setY(y.get());
            } else {
                return Optional.empty();
            }
            if (AABBUtils.isEmpty(AABBUtils.moveTo(aabb, pos), level)) {
                return Optional.of(pos.immutable());
            }
        }
        return Optional.empty();
    }

    public static Optional<BlockPos> findSafePosition(LivingEntity living, BlockPos center, int radius, boolean surface) {
        return findSafePosition(living.getLevel(), center, radius, surface, living.getBoundingBox(), living.getRandom());
    }

    public static Optional<BlockPos> findSafePosition(LivingEntity living, BlockPos center, int radius, boolean surface, int maxTries) {
        for (int t = 0; t < maxTries; t++) {
            var pos = findSafePosition(living, center, radius, surface);
            if (pos.isPresent()) return pos;
        }
        return Optional.empty();
    }

    public static boolean teleport(LivingEntity living, BlockPos center, int radius, boolean surface, int maxTries) {
        if (living.level.isClientSide) return false;

        var pos = findSafePosition(living, center, radius, surface, maxTries);
        if (pos.isPresent()) {
            EntityUtils.teleportTo(living, pos.get());
            return true;
        }
        return false;
    }

    public static boolean teleport(LivingEntity living, int radius, int maxTries) {
        return teleport(living, living.blockPosition(), radius, false, maxTries);
    }

    public static void teleportUnsafe(LivingEntity living, Vec3 center, double radius) {
        EntityUtils.teleportTo(living, PositionUtils.random(center, radius, living.getRandom()));
    }

    public static void teleportUnsafe(LivingEntity living, double radius) {
        teleportUnsafe(living, living.position(), radius);
    }

}
