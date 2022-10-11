package org.auioc.mcmod.arnicalib.game.block;

import net.minecraft.world.level.block.state.BlockState;

public class BlockUtils {

    public static boolean canStandOn(BlockState blockStats) {
        return blockStats.getMaterial().blocksMotion();
    }

}
