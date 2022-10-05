package org.auioc.mcmod.arnicalib.mod.datagen;

import org.auioc.mcmod.arnicalib.mod.datagen.provider.HEntityTypeTagsProvider;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AHDataGenerators {

    @SubscribeEvent
    public static void dataGen(GatherDataEvent event) {
        var generator = event.getGenerator();
        var existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(new HEntityTypeTagsProvider(generator, existingFileHelper));
        // var blockTags = new HBlockTagsProvider(generator, existingFileHelper);
        // generator.addProvider(new HItemTagsProvider(generator, blockTags, existingFileHelper));
    }

}
