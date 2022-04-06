package org.auioc.mcmod.arnicalib.common.event.impl;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

@Deprecated(since = "3.1.1")
@Cancelable
public class PistonAddBlockLineEvent extends Event {

    private final BlockState blockState;
    private final Level level;
    private final BlockPos blockPos;
    private final Direction direction;

    public PistonAddBlockLineEvent(BlockState blockState, Level level, BlockPos blockPos, Direction direction) {
        super();
        this.blockState = blockState;
        this.level = level;
        this.blockPos = blockPos;
        this.direction = direction;
    }

    public BlockPos getBlockPos() {
        return this.blockPos;
    }

    public Level getLevel() {
        return this.level;
    }

    public BlockState getBlockState() {
        return this.blockState;
    }

    public Direction getDirection() {
        return this.direction;
    }

}
