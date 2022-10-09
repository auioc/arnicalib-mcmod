package org.auioc.mcmod.arnicalib.game.item;

import java.util.function.Predicate;
import org.auioc.mcmod.arnicalib.game.registry.VanillaPredicates;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ItemPredicates {

    public static final Predicate<ItemStack> IS_VANILLA = (itemStack) -> VanillaPredicates.REGISTRY_ENTRY.test(itemStack.getItem());
    public static final Predicate<Item> IS_AIR = (item) -> item == Items.AIR;
    public static final Predicate<Item> IS_CATEGORIZED = (item) -> item.getItemCategory() != null;

    @Deprecated(since = "5.6.2", forRemoval = true)
    public static final Predicate<ItemStack> IS_VANILLA_ITEM = IS_VANILLA;

}
