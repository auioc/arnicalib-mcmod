package org.auioc.mcmod.arnicalib.utils.game;

import java.util.Random;
import org.auioc.mcmod.arnicalib.utils.java.RandomUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;


public interface PositionUtils {

    static boolean isInWorldBounds(BlockPos pos, Level level) {
        return level.getWorldBorder().isWithinBounds(pos)
            && pos.getY() < level.getMaxBuildHeight()
            && pos.getY() >= level.getMinBuildHeight();
    }

    static boolean canStandSafely(BlockPos pos, Level level) {
        return BlockUtils.canStandOnSafely(level.getBlockState(pos.below()));
    }

    static boolean canStandOnSafely(BlockPos pos, Level level) {
        return BlockUtils.canStandOnSafely(level.getBlockState(pos));
    }

    static BlockPos random(BlockPos center, int radius, Random random) {
        return new BlockPos(
            center.getX() + RandomUtils.offset(radius, random),
            center.getY() + RandomUtils.offset(radius, random),
            center.getZ() + RandomUtils.offset(radius, random)
        );

    }

}
