package org.auioc.mcmod.arnicalib.mod.datagen.provider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.auioc.mcmod.arnicalib.ArnicaLib;
import org.auioc.mcmod.arnicalib.game.datagen.tag.IHTagsProvider;
import org.auioc.mcmod.arnicalib.game.tag.HBlockTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public class HBlockTagsProvider extends BlockTagsProvider implements IHTagsProvider<Block> {

    public HBlockTagsProvider(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, ArnicaLib.MOD_ID, existingFileHelper);
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
    protected void addTags() {
        addFromRegistry(tag(HBlockTags.INSTABREAKABLE), (b) -> b.defaultDestroyTime() == 0.0F);
        addFromRegistry(tag(HBlockTags.RANDOMLY_TICKABLE), (b) -> b.defaultBlockState().isRandomlyTicking());
        addFromRegistry(tag(HBlockTags.LIGHT), (b) -> b.defaultBlockState().getLightEmission() > 0);
    }

}

