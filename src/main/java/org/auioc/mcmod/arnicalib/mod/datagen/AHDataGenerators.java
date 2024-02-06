/*
 * Copyright (C) 2022-2024 AUIOC.ORG
 *
 * This file is part of ArnicaLib, a mod made for Minecraft.
 *
 * ArnicaLib is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <https://www.gnu.org/licenses/>.
 */

package org.auioc.mcmod.arnicalib.mod.datagen;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.auioc.mcmod.arnicalib.mod.datagen.provider.HBlockTagsProvider;
import org.auioc.mcmod.arnicalib.mod.datagen.provider.HEntityTypeTagsProvider;
import org.auioc.mcmod.arnicalib.mod.datagen.provider.HItemTagsProvider;

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
