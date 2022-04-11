package org.auioc.mcmod.arnicalib.utils.game;

import java.util.List;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import com.google.gson.JsonObject;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import org.auioc.mcmod.arnicalib.utils.java.Validate;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TagParser;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;

public interface ItemUtils {

    static Item getItem(ResourceLocation id) {
        Validate.isTrue(ForgeRegistries.ITEMS.containsKey(id), "Unknown item '" + id + "'");
        return ForgeRegistries.ITEMS.getValue(id);
    }

    static Item getItem(String id) {
        return getItem(new ResourceLocation(id));
    }

    static List<Item> getItems(List<String> idList) {
        return idList.stream().map(ResourceLocation::new).map(ItemUtils::getItem).toList();
    }

    static String getRegistryName(Item item) {
        return item.getRegistryName().toString();
    }

    static String getRegistryName(ItemStack itemStack) {
        return getRegistryName(itemStack.getItem());
    }

    Predicate<ItemStack> IS_VANILLA_ITEM = (itemStack) -> RegistryUtils.IS_VANILLA.test(itemStack.getItem());
    Predicate<Item> IS_AIR = (item) -> item == Items.AIR;
    Predicate<Item> IS_CATEGORIZED = (item) -> item.getItemCategory() != null;

    @SuppressWarnings("deprecation")
    static int getMaxStackSize(Item item) {
        return item.getMaxStackSize();
    }

    @SuppressWarnings("deprecation")
    static int getMaxDamage(Item item) {
        return item.getMaxDamage();
    }


    static String toString(ItemStack itemStack) {
        return String.format("%s%s * %d", getRegistryName(itemStack), (itemStack.hasTag()) ? itemStack.getTag() : "{}", itemStack.getCount());
    }


    static void damageItem(Player player, ItemStack itemStack) {
        itemStack.hurtAndBreak(1, player, (p) -> {
            p.broadcastBreakEvent(player.getUsedItemHand());
        });
    }


    static ItemStack createItemStack(Item item, int count, @Nullable CompoundTag nbt) {
        ItemStack itemStack = new ItemStack(item, count);
        if (nbt != null) {
            itemStack.setTag(nbt);
        }
        return itemStack;
    }

    static ItemStack createItemStack(JsonObject json) {
        Item item = getItem(GsonHelper.getAsString(json, "id"));

        int count = GsonHelper.getAsInt(json, "count", 1);
        Validate.isPositive(count, "The item count must be positive: " + count);
        Validate.isTrue(count <= getMaxStackSize(item), "The specified count " + count + " is too large, the max stack size of item '" + getRegistryName(item) + "' is " + getMaxStackSize(item));

        CompoundTag nbt = null;
        String snbt = GsonHelper.getAsString(json, "tag", null);
        if (snbt != null) {
            try {
                nbt = TagParser.parseTag(snbt);
            } catch (CommandSyntaxException e) {
                Validate.throwException("Invalid NBT: " + e.getMessage());
            }
        }

        return createItemStack(item, count, nbt);
    }

}
