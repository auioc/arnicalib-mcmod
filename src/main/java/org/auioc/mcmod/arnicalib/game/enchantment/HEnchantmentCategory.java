package org.auioc.mcmod.arnicalib.game.enchantment;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.FoodOnAStickItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SpyglassItem;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class HEnchantmentCategory {

    public static final EnchantmentCategory HOE = EnchantmentCategory.create("HOE", (item) -> item instanceof HoeItem);
    public static final EnchantmentCategory AXE = EnchantmentCategory.create("AXE", (item) -> item instanceof AxeItem);
    public static final EnchantmentCategory SHOVEL = EnchantmentCategory.create("SHOVEL", (item) -> item instanceof ShovelItem);
    public static final EnchantmentCategory PICKAXE = EnchantmentCategory.create("PICKAXE", (item) -> item instanceof PickaxeItem);

    public static final EnchantmentCategory SHIELD = EnchantmentCategory.create("SHIELD", (item) -> item instanceof ShieldItem);

    public static final EnchantmentCategory ELYTRA = EnchantmentCategory.create("ELYTRA", (item) -> item instanceof ElytraItem);

    public static final EnchantmentCategory FOOD_ON_A_STACK = EnchantmentCategory.create("FOOD_ON_A_STACK", (item) -> item instanceof FoodOnAStickItem);

    public static final EnchantmentCategory FLINT_AND_STEEL = EnchantmentCategory.create("FLINT_AND_STEEL", (item) -> item instanceof FlintAndSteelItem);

    public static final EnchantmentCategory SPYGLASS = EnchantmentCategory.create("SPYGLASS", (item) -> item instanceof SpyglassItem);

}
