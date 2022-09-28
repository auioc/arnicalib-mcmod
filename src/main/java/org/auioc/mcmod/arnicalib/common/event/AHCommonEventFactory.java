package org.auioc.mcmod.arnicalib.common.event;

import org.auioc.mcmod.arnicalib.common.event.impl.ItemInventoryTickEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;

public final class AHCommonEventFactory {

    private static final IEventBus BUS = MinecraftForge.EVENT_BUS;

    public static boolean onSelectedItemItemInventoryTick(Player player, Level level, ItemStack itemStack, int index) {
        return BUS.post(new ItemInventoryTickEvent.Selected(player, level, itemStack, index));
    }

}
