package org.auioc.mcmod.arnicalib.mod;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import org.auioc.mcmod.arnicalib.ArnicaLib;
import org.auioc.mcmod.arnicalib.game.mod.ExtensionPointUtils;
import org.auioc.mcmod.arnicalib.mod.client.ClientInitialization;
import org.auioc.mcmod.arnicalib.mod.common.tag.HTags;
import org.auioc.mcmod.arnicalib.mod.server.event.AHServerEventHandler;
import org.auioc.mcmod.arnicalib.mod.server.loot.AHGlobalLootModifiers;
import org.auioc.mcmod.arnicalib.mod.server.loot.AHLootItemConditions;
import org.auioc.mcmod.arnicalib.mod.server.loot.AHLootItemFunctions;

public final class Initialization {

    public static void init() {
        registerConfig();
        modSetup();
        forgeSetup();
        if (FMLEnvironment.dist == Dist.CLIENT) {
            ClientInitialization.init();
        } else {
            ExtensionPointUtils.serverOnly();
        }
    }

    private static final IEventBus modEventBus = ArnicaLib.getModEventBus();
    private static final IEventBus forgeEventBus = NeoForge.EVENT_BUS;

    private static void registerConfig() { }

    private static void modSetup() {
        AHGlobalLootModifiers.GLOBAL_LOOT_MODIFIERS.register(modEventBus);
        AHLootItemConditions.LOOT_CONDITION_TYPES.register(modEventBus);
        AHLootItemFunctions.LOOT_FUNCTION_TYPES.register(modEventBus);
        HTags.init();
    }

    private static void forgeSetup() {
        forgeEventBus.register(AHServerEventHandler.class);
    }

}
