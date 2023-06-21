package org.auioc.mcmod.arnicalib.mod.client.event;

import org.auioc.mcmod.arnicalib.game.resource.model.CustomModelLoader;
import org.auioc.mcmod.arnicalib.mod.client.resource.AHClientReloadListener;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

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
