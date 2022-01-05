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
        "barrier",
        "chain_command_block",
        "command_block",
        "command_block_minecart",
        "debug_stick",
        "dragon_egg",
        "jigsaw",
        "repeating_command_block",
        "spawner",
        "structure_block",
        "structure_void"
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
