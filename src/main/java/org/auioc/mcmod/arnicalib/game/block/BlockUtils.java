package org.auioc.mcmod.arnicalib.game.block;

import net.minecraft.world.level.block.state.BlockState;

public class BlockUtils {

    @SuppressWarnings("deprecation")
    public static boolean canStandOn(BlockState blockStats) {
        return blockStats.blocksMotion();
    }

}
