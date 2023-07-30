package org.auioc.mcmod.arnicalib.mod.datagen.provider;

import java.util.concurrent.CompletableFuture;
import javax.annotation.Nonnull;
import org.auioc.mcmod.arnicalib.ArnicaLib;
import org.auioc.mcmod.arnicalib.game.datagen.tag.IHTagsProvider;
import org.auioc.mcmod.arnicalib.game.tag.HBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

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
    public IForgeRegistry<Block> getRegistry() {
        return ForgeRegistries.BLOCKS;
    }

    @Override
    @SuppressWarnings("deprecation")
    protected void addTags(HolderLookup.Provider lookup) {
        addFromRegistry(tag(HBlockTags.INSTABREAKABLE), (b) -> b.defaultDestroyTime() == 0.0F);
        addFromRegistry(tag(HBlockTags.RANDOMLY_TICKABLE), (b) -> b.isRandomlyTicking(b.defaultBlockState()));
        addFromRegistry(tag(HBlockTags.LIGHT), (b) -> b.defaultBlockState().getLightEmission() > 0);
    }

}

