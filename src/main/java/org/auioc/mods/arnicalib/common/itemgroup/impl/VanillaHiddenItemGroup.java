package org.auioc.mods.arnicalib.common.itemgroup.impl;

import java.util.List;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;

public class VanillaHiddenItemGroup extends CreativeModeTab {

    private static final List<String> HIDDEN_ITEMS = List.of(
        "debug_stick",
        "barrier",
        "command_block",
        "repeating_command_block",
        "chain_command_block",
        "command_block_minecart",
        "spawner",
        "structure_block",
        "structure_void",
        "jigsaw",
        "dragon_egg"
    );

    public VanillaHiddenItemGroup() {
        super("vanilla_hidden_item");
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(Items.COMMAND_BLOCK);
    }

    @Override
    public void fillItemList(NonNullList<ItemStack> list) {
        for (String itemID : HIDDEN_ITEMS) {
            Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemID));
            if (item != null) {
                list.add(new ItemStack(item));
            }
        }
    }

}
