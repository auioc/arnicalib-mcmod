package org.auioc.mcmod.arnicalib.mod.datagen.provider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
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
    }

}

