package org.auioc.mcmod.arnicalib.client.event;

import org.auioc.mcmod.arnicalib.client.command.AHClientCommands;
import org.auioc.mcmod.arnicalib.client.event.handler.TooltipEventHandler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@OnlyIn(Dist.CLIENT)
public final class AHClientEventHandler {

    @SubscribeEvent
    public static void registerCommands(final RegisterClientCommandsEvent event) {
        AHClientCommands.register(event.getDispatcher());
    }


    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        TooltipEventHandler.handle(event);
    }

}
