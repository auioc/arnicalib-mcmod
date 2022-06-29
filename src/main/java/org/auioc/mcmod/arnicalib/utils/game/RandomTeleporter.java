package org.auioc.mcmod.arnicalib.utils.game;

import org.auioc.mcmod.arnicalib.api.java.exception.HException;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class RandomTeleporter {

    public static BlockPos findRandomSafePos(LivingEntity living, BlockPos center, int radius, boolean surface) throws HException {
        var level = living.getLevel();
        var pos = PositionUtils.random(center, radius, living.getRandom()).mutable();
        if (PositionUtils.isInWorldBounds(pos, level)) {
            pos.setY(
                surface
                    ? PositionUtils.findStandableY(level, pos.getX(), pos.getZ())
                    : PositionUtils.findStandableY(level, pos.getX(), pos.getZ(), center.getY() - radius, pos.getY())
            ); // throws HException
            if (AABBUtils.isEmpty(AABBUtils.moveTo(living.getBoundingBox(), pos), level)) {
                return pos.immutable();
            }
        }
        throw new HException();
    }

    public static boolean safeTeleport(LivingEntity living, BlockPos center, int radius, boolean surface, int maxTries) {
        if (living.level.isClientSide) return false;

        for (int t = 0; t < maxTries; t++) {
            try {
                EntityUtils.teleportTo(living, findRandomSafePos(living, center, radius, surface));
                return true;
            } catch (HException e) {
                continue;
            }
        }
        return false;
    }

    public static boolean safeTeleport(LivingEntity living, int radius, int maxTries) {
        return safeTeleport(living, living.blockPosition(), radius, false, maxTries);
    }

    public static void unsafeTeleport(LivingEntity living, Vec3 center, double radius) {
        EntityUtils.teleportTo(living, PositionUtils.random(center, radius, living.getRandom()));
    }

    public static void unsafeTeleport(LivingEntity living, double radius) {
        unsafeTeleport(living, living.position(), radius);
    }

}
