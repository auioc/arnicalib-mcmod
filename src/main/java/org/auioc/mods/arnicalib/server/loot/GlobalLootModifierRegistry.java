package org.auioc.mods.arnicalib.server.loot;

import org.auioc.mods.arnicalib.Reference;
import org.auioc.mods.arnicalib.server.loot.impl.HLootInjector;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class GlobalLootModifierRegistry {

    @SubscribeEvent
    public static void registerModifierSerializer(RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
        IForgeRegistry<GlobalLootModifierSerializer<?>> registry = event.getRegistry();

        registry.register(
            (new HLootInjector.Serializer()).setRegistryName(Reference.ResourceId("loot_injector"))
        );
    }

}
