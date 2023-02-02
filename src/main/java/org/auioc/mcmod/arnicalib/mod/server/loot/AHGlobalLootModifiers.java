package org.auioc.mcmod.arnicalib.mod.server.loot;

import org.auioc.mcmod.arnicalib.ArnicaLib;
import org.auioc.mcmod.arnicalib.game.loot.modifier.LootTableInjector;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public final class AHGlobalLootModifiers {

    @SubscribeEvent
    public static void registerModifierSerializer(RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
        IForgeRegistry<GlobalLootModifierSerializer<?>> registry = event.getRegistry();

        registry.register((new LootTableInjector.Serializer()).setRegistryName(ArnicaLib.id("loot_table_injector")));
    }

}
