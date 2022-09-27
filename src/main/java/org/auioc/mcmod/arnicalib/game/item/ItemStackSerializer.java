package org.auioc.mcmod.arnicalib.game.item;

import org.auioc.mcmod.arnicalib.base.validate.Validate;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TagParser;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;

public class ItemStackSerializer {

    public static ItemStack fromJson(JsonObject json) {
        var item = ItemRegistry.getOrElseThrow(GsonHelper.getAsString(json, "id"));

        int count = GsonHelper.getAsInt(json, "count", 1);
        try {
            Validate.isPositive(count, "The item count must be positive: " + count);
            int maxStackSize = ItemUtils.getMaxStackSize(item);
            Validate.isTrue(
                count <= maxStackSize,
                "The specified count " + count + " is too large, the max stack size of item '" + ItemUtils.toString(item) + "' is " + maxStackSize
            );
        } catch (IllegalArgumentException e) {
            throw new JsonSyntaxException(e);
        }

        CompoundTag nbt = null;
        var snbt = GsonHelper.getAsString(json, "nbt", null);
        if (snbt != null) {
            try {
                nbt = TagParser.parseTag(snbt);
            } catch (CommandSyntaxException e) {
                throw new JsonSyntaxException("Invalid NBT: " + e.getMessage());
            }
        }

        return ItemUtils.createItemStack(item, count, nbt);
    }

}
