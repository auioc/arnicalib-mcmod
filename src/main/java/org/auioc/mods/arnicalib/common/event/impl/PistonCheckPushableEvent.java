package org.auioc.mods.arnicalib.common.event.impl;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Cancelable;

@Cancelable
public class PistonCheckPushableEvent extends BlockEvent {

    private final Direction pushDirection;

    public PistonCheckPushableEvent(BlockState blockState, Level level, BlockPos blockPos, Direction pushDirection, boolean p_185646_4_, Direction p_185646_5_) {
        super(level, blockPos, blockState);
        this.pushDirection = pushDirection;
    }

    public Direction getPushDirection() {
        return pushDirection;
    }

}
