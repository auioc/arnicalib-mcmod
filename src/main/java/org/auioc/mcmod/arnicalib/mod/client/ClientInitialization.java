package org.auioc.mcmod.arnicalib.mod.client;

import org.auioc.mcmod.arnicalib.game.mod.ExtensionPointUtils;
import org.auioc.mcmod.arnicalib.mod.client.config.AHClientConfig;
import org.auioc.mcmod.arnicalib.mod.client.event.AHClientEventHandler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@OnlyIn(Dist.CLIENT)
@SuppressWarnings("unused")
public final class ClientInitialization {

    public static void init() {
        ExtensionPointUtils.clientOnly();
        registerConfig();
        modSetup();
        forgeSetup();
    }

    private static final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    private static final IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;

    private static void registerConfig() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, AHClientConfig.CONFIG);
    }

    private static void modSetup() {}

    private static void forgeSetup() {
        forgeEventBus.register(AHClientEventHandler.class);
    }

}
