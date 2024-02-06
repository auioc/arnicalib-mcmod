package org.auioc.mcmod.arnicalib.mod.client.event;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent;
import org.auioc.mcmod.arnicalib.game.resource.model.CustomModelLoader;
import org.auioc.mcmod.arnicalib.mod.client.resource.AHClientReloadListener;

@OnlyIn(Dist.CLIENT)
public final class AHClientModEventHandler {

    @SubscribeEvent
    public static void onRegisterAdditionalModel(ModelEvent.RegisterAdditional event) {
        CustomModelLoader.list(Minecraft.getInstance().getResourceManager(), "custom").forEach(event::register);
    }

    @SubscribeEvent
    public static void registerReloadListeners(final RegisterClientReloadListenersEvent event) {
        event.registerReloadListener(new AHClientReloadListener());
    }

}
