package org.auioc.mcmod.arnicalib.common.itemgroup.impl;

import org.auioc.mcmod.arnicalib.game.item.ItemPredicates;
import org.auioc.mcmod.arnicalib.utils.game.VanillaPredicates;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;

public class TabVanillaHiddenItems extends CreativeModeTab {

    public TabVanillaHiddenItems() {
        super("vanillaHiddenItems");
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(Items.COMMAND_BLOCK);
    }

    @Override
    public void fillItemList(NonNullList<ItemStack> list) {
        ForgeRegistries.ITEMS.getValues()
            .stream()
            .filter(ItemPredicates.IS_AIR.negate())
            .filter(ItemPredicates.IS_CATEGORIZED.negate())
            .filter(VanillaPredicates.REGISTRY_ENTRY)
            .map(ItemStack::new)
            .forEach(list::add);
    }

}
