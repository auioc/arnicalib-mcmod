package org.auioc.mcmod.arnicalib.mod.datagen.provider;

import static org.auioc.mcmod.arnicalib.game.tag.HItemTags.*;
import java.util.concurrent.CompletableFuture;
import org.auioc.mcmod.arnicalib.ArnicaLib;
import org.auioc.mcmod.arnicalib.game.datagen.tag.IHTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public class HItemTagsProvider extends ItemTagsProvider implements IHTagsProvider<Item> {

    public HItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup, CompletableFuture<TagLookup<Block>> blockTags, ExistingFileHelper fileHelper) {
        super(output, lookup, blockTags, ArnicaLib.MOD_ID, fileHelper);
    }

    @Override
    public String getName() {
        return "HItemsTags";
    }

    @Override
    public IForgeRegistry<Item> getRegistry() {
        return ForgeRegistries.ITEMS;
    }

    @Override
    @SuppressWarnings("deprecation")
    protected void addTags(HolderLookup.Provider lookup) {
        tag(BRICKS).add(Items.BRICK, Items.NETHER_BRICK);
        tag(MAPS).add(Items.MAP, Items.FILLED_MAP);
        addFromRegistry(tag(SPAWN_EGGS), (item) -> item instanceof SpawnEggItem);

        tag(AXES).add(Items.WOODEN_AXE, Items.STONE_AXE, Items.IRON_AXE, Items.GOLDEN_AXE, Items.DIAMOND_AXE, Items.NETHERITE_AXE);
        tag(HOES).add(Items.WOODEN_HOE, Items.STONE_HOE, Items.IRON_HOE, Items.GOLDEN_HOE, Items.DIAMOND_HOE, Items.NETHERITE_HOE);
        tag(SWORDS).add(Items.WOODEN_SWORD, Items.STONE_SWORD, Items.IRON_SWORD, Items.GOLDEN_SWORD, Items.DIAMOND_SWORD, Items.NETHERITE_SWORD);
        tag(SHOVELS).add(Items.WOODEN_SHOVEL, Items.STONE_SHOVEL, Items.IRON_SHOVEL, Items.GOLDEN_SHOVEL, Items.DIAMOND_SHOVEL, Items.NETHERITE_SHOVEL);
        tag(PICKAXES).add(Items.WOODEN_PICKAXE, Items.STONE_PICKAXE, Items.IRON_PICKAXE, Items.GOLDEN_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE);

        addFromRegistry(tag(FOOD), (item) -> item.isEdible());
        addFromRegistry(tag(FOOD_MEAT), (item) -> item.isEdible() && item.getFoodProperties().isMeat());
        addFromRegistry(tag(FOOD_FAST), (item) -> item.isEdible() && item.getFoodProperties().isFastFood());
        addFromRegistry(tag(FOOD_ALWAYS_EDIBLE), (item) -> item.isEdible() && item.getFoodProperties().canAlwaysEat());

        addFromRegistry(tag(FIRE_RESISTANT_ITEMS), (item) -> item.isFireResistant());
        addFromRegistry(tag(DAMAGEABLE_ITEMS), (item) -> item.getMaxDamage() > 0);
    }

}
