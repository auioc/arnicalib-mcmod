package org.auioc.mcmod.arnicalib.mod.client.event;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterClientCommandsEvent;
import org.auioc.mcmod.arnicalib.mod.client.command.AHClientCommands;

@OnlyIn(Dist.CLIENT)
public final class AHClientEventHandler {

    @SubscribeEvent
    public static void registerCommands(final RegisterClientCommandsEvent event) {
        AHClientCommands.register(event.getDispatcher());
    }

}
