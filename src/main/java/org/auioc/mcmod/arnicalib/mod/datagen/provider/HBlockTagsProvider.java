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

package org.auioc.mcmod.arnicalib.mod.datagen.provider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.auioc.mcmod.arnicalib.ArnicaLib;
import org.auioc.mcmod.arnicalib.game.datagen.tag.IHTagsProvider;
import org.auioc.mcmod.arnicalib.game.tag.HBlockTags;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;


public class HBlockTagsProvider extends BlockTagsProvider implements IHTagsProvider<Block> {

    public HBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup, ExistingFileHelper fileHelper) {
        super(output, lookup, ArnicaLib.MOD_ID, fileHelper);
    }

    @Override
    public String getName() {
        return "HBlocksTags";
    }

    @Nonnull
    @Override
    public Registry<Block> getRegistry() {
        return BuiltInRegistries.BLOCK;
    }

    @Override
    @SuppressWarnings("deprecation")
    protected void addTags(HolderLookup.Provider lookup) {
        addFromRegistry(tag(HBlockTags.INSTABREAKABLE), (b) -> b.defaultDestroyTime() == 0.0F);
        addFromRegistry(tag(HBlockTags.RANDOMLY_TICKABLE), (b) -> b.isRandomlyTicking(b.defaultBlockState()));
        addFromRegistry(tag(HBlockTags.LIGHT), (b) -> b.defaultBlockState().getLightEmission() > 0);
        tag(HBlockTags.PISTON_NONINTERACTIVE).add(Blocks.OBSIDIAN, Blocks.CRYING_OBSIDIAN, Blocks.RESPAWN_ANCHOR, Blocks.REINFORCED_DEEPSLATE);
    }

}
