package org.auioc.mcmod.arnicalib.utils.game;

import net.minecraft.world.level.block.state.BlockState;

public interface BlockUtils {

    static boolean canStandOn(BlockState blockStats) {
        return blockStats.getMaterial().blocksMotion();
    }

}
