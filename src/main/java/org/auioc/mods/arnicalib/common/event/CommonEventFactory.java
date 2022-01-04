package org.auioc.mods.arnicalib.common.event;

import org.auioc.mods.arnicalib.common.event.impl.PistonCheckPushableEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;

public class CommonEventFactory {

    private static final IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;

    @Deprecated(since = "3.1.1")
    public static boolean postPistonAddBlockLineEvent(BlockState blockState, Level level, BlockPos blockPos, Direction direction) {
        return forgeEventBus.post(new org.auioc.mods.arnicalib.common.event.impl.PistonAddBlockLineEvent(blockState, level, blockPos, direction));
    }

    public static boolean firePistonCheckPushableEvent(BlockState blockState, Level level, BlockPos blockPos, Direction pushDirection, boolean p_60209_, Direction p_60210_) {
        return forgeEventBus.post(new PistonCheckPushableEvent(blockState, level, blockPos, pushDirection, p_60209_, p_60210_));
    }

}
