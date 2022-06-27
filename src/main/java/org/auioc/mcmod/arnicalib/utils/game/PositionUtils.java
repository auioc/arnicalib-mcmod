package org.auioc.mcmod.arnicalib.utils.game;

import java.util.Random;
import org.auioc.mcmod.arnicalib.utils.java.RandomUtils;
import net.minecraft.core.BlockPos;
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
