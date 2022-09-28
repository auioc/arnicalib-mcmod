package org.auioc.mcmod.arnicalib.common.event;

import org.auioc.mcmod.arnicalib.common.event.impl.ItemInventoryTickEvent;
import org.auioc.mcmod.arnicalib.common.event.impl.PistonCheckPushableEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;

public final class AHCommonEventFactory {

    private static final IEventBus BUS = MinecraftForge.EVENT_BUS;

    @Deprecated(since = "3.1.1")
    public static boolean postPistonAddBlockLineEvent(BlockState blockState, Level level, BlockPos blockPos, Direction direction) {
        return BUS.post(new org.auioc.mcmod.arnicalib.common.event.impl.PistonAddBlockLineEvent(blockState, level, blockPos, direction));
    }

    public static boolean onPistonCheckPushable(BlockState blockState, Level level, BlockPos blockPos, Direction pushDirection, boolean p_60209_, Direction p_60210_) {
        return BUS.post(new PistonCheckPushableEvent(blockState, level, blockPos, pushDirection, p_60209_, p_60210_));
    }

    public static boolean onSelectedItemItemInventoryTick(Player player, Level level, ItemStack itemStack, int index) {
        return BUS.post(new ItemInventoryTickEvent.Selected(player, level, itemStack, index));
    }

}
