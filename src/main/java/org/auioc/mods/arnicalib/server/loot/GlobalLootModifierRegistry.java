package org.auioc.mods.arnicalib.server.loot;

import org.auioc.mods.arnicalib.ArnicaLib;
import org.auioc.mods.arnicalib.server.loot.modifier.LootTableInjector;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public final class GlobalLootModifierRegistry {

    @SubscribeEvent
    public static void registerModifierSerializer(RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
        IForgeRegistry<GlobalLootModifierSerializer<?>> registry = event.getRegistry();

        registry.register((new LootTableInjector.Serializer()).setRegistryName(ArnicaLib.id("loot_table_injector")));
    }

}
