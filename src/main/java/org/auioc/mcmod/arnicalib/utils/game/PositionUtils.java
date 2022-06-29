package org.auioc.mcmod.arnicalib.utils.game;

import java.util.Random;
import org.auioc.mcmod.arnicalib.api.java.exception.HException;
import org.auioc.mcmod.arnicalib.utils.java.RandomUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;


public interface PositionUtils {

    static boolean isInWorldBounds(BlockPos pos, Level level) {
        return level.getWorldBorder().isWithinBounds(pos)
            && pos.getY() < level.getMaxBuildHeight()
            && pos.getY() >= level.getMinBuildHeight();
    }

    static boolean canStandOn(BlockPos pos, Level level) {
        return BlockUtils.canStandOn(level.getBlockState(pos));
    }

    static boolean canStand(BlockPos pos, Level level) {
        return canStandOn(pos.below(), level);
    }

    static int findStandableY(Level level, int x, int z, int minY, int maxY) throws HException {
        minY = Math.max(minY, level.getMinBuildHeight());
        maxY = Math.min(maxY, level.getMaxBuildHeight() - 1);
        var pos = new MutableBlockPos(x, maxY, z);
        while (true) {
            if (PositionUtils.canStand(pos, level)) {
                return pos.getY();
            }
            if (pos.getY() > minY) {
                pos.move(Direction.DOWN);
            } else {
                throw new HException();
            }
        }
    }

    static int findStandableY(Level level, int x, int z) throws HException {
        return findStandableY(level, x, z, level.getMinBuildHeight(), level.getMaxBuildHeight() - 1);
    }

    static Vec3i random(Vec3i center, int radius, Random random) {
        radius += 1;
        return new Vec3i(
            center.getX() + RandomUtils.offset(radius, random),
            center.getY() + RandomUtils.offset(radius, random),
            center.getZ() + RandomUtils.offset(radius, random)
        );
    }

    static Vec3 random(Vec3 center, double radius, Random random) {
        radius += 1.0D;
        return new Vec3(
            center.x + RandomUtils.offset(radius, random),
            center.y + RandomUtils.offset(radius, random),
            center.z + RandomUtils.offset(radius, random)
        );
    }

    static BlockPos random(BlockPos center, int radius, Random random) {
        return new BlockPos(random((Vec3i) center, radius, random));
    }

}
