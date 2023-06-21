package org.auioc.mcmod.arnicalib.mod.datagen;

import org.auioc.mcmod.arnicalib.mod.datagen.provider.HBlockTagsProvider;
import org.auioc.mcmod.arnicalib.mod.datagen.provider.HEntityTypeTagsProvider;
import org.auioc.mcmod.arnicalib.mod.datagen.provider.HItemTagsProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AHDataGenerators {

    @SubscribeEvent
    public static void dataGen(GatherDataEvent event) {
        var generator = event.getGenerator();
        var output = generator.getPackOutput();
        var fileHelper = event.getExistingFileHelper();
        var lookup = event.getLookupProvider();
        boolean f = event.includeServer();

        var blockTags = new HBlockTagsProvider(output, lookup, fileHelper);
        generator.addProvider(f, blockTags);
        generator.addProvider(f, new HItemTagsProvider(output, lookup, blockTags.contentsGetter(), fileHelper));
        generator.addProvider(f, new HEntityTypeTagsProvider(output, lookup, fileHelper));
    }

}
