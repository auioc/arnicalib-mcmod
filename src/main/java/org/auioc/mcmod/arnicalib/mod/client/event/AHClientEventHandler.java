package org.auioc.mcmod.arnicalib.mod.client.event;

import org.auioc.mcmod.arnicalib.mod.client.command.AHClientCommands;
import org.auioc.mcmod.arnicalib.mod.client.widget.AdvancedItemTooltip;
import org.auioc.mcmod.arnicalib.mod.client.widget.DebugScreenInformation;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@OnlyIn(Dist.CLIENT)
public final class AHClientEventHandler {

    private static final Minecraft MC = Minecraft.getInstance();

    @SubscribeEvent
    public static void registerCommands(final RegisterClientCommandsEvent event) {
        AHClientCommands.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        AdvancedItemTooltip.handle(event);
    }

    @SubscribeEvent
    public static void onRenderTextOverlay(final RenderGameOverlayEvent.Text event) {
        if (MC.options.renderDebug) DebugScreenInformation.handle(event.getLeft(), event.getRight());
    }

}
