package org.auioc.mcmod.arnicalib.mod.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.NeoForge;
import org.auioc.mcmod.arnicalib.ArnicaLib;
import org.auioc.mcmod.arnicalib.game.mod.EnvironmentUtils;
import org.auioc.mcmod.arnicalib.game.mod.ExtensionPointUtils;
import org.auioc.mcmod.arnicalib.mod.client.event.AHClientEventHandler;
import org.auioc.mcmod.arnicalib.mod.client.event.AHClientModEventHandler;

@OnlyIn(Dist.CLIENT)
public final class ClientInitialization {

    public static void init() {
        ExtensionPointUtils.clientOnly();
        registerConfig();
        modSetup();
        forgeSetup();
    }

    private static final IEventBus modEventBus = ArnicaLib.getModEventBus();
    private static final IEventBus forgeEventBus = NeoForge.EVENT_BUS;

    private static void registerConfig() { }

    private static void modSetup() {
        modEventBus.register(AHClientModEventHandler.class);
    }

    private static void forgeSetup() {
        forgeEventBus.register(AHClientEventHandler.class);
        if (EnvironmentUtils.IS_DEV) {
            //            forgeEventBus.addListener(ClientInitialization::clientTestHandler);
        }
    }

    // ====================================================================== //

    //    private static void clientTestHandler(ClientChatReceivedEvent event) {
    //        if (event.getMessage().getString().equals("<Dev> .test")) {
    //            ReflectionUtils.invokeStatic("org.auioc.mcmod.arnicalib.mod.test.TestHandlerClient", "test");
    //        }
    //    }
    // TODO

}
