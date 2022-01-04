package org.auioc.mods.arnicalib.common.itemgroup.impl;

import java.util.List;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;

public class McHiddenItemsGroup extends CreativeModeTab {
    List<String> hiddenItems = List.of(
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

    public McHiddenItemsGroup() {
        super("mc_hidden_items_group");
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(Items.COMMAND_BLOCK);
    }

    @Override
    public void fillItemList(NonNullList<ItemStack> itemsToShowInGroup) {
        for (Item item : ForgeRegistries.ITEMS) {

            if (item != null && hiddenItems.contains(item.getRegistryName().getPath())) {
                itemsToShowInGroup.add(new ItemStack(item));
                // item.fillItemCategory(ItemGroup.TAB_SEARCH, itemsToShowInGroup);
                // item.fillItemCategory(this, itemsToShowInGroup);
            }

        }
    }
}
