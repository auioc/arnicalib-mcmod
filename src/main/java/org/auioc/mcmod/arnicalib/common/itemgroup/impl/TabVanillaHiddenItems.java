package org.auioc.mcmod.arnicalib.common.itemgroup.impl;

import org.auioc.mcmod.arnicalib.utils.game.ItemUtils;
import org.auioc.mcmod.arnicalib.utils.game.RegistryUtils;
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
            .filter(ItemUtils.IS_AIR.negate())
            .filter(ItemUtils.IS_CATEGORIZED.negate())
            .filter(RegistryUtils.IS_VANILLA)
            .map(ItemStack::new)
            .forEach(list::add);
    }

}
