/*
 * Copyright (C) 2022-2024 AUIOC.ORG
 *
 * This file is part of ArnicaLib, a mod made for Minecraft.
 *
 * ArnicaLib is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <https://www.gnu.org/licenses/>.
 */

package org.auioc.mcmod.arnicalib.game.item;

import org.auioc.mcmod.arnicalib.base.validate.Validate;
import org.auioc.mcmod.arnicalib.game.registry.RegistryUtils;
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

    public static void toJson(ItemStack itemStack, JsonObject json) {
        json.addProperty("id", RegistryUtils.id(itemStack.getItem()).toString());
        json.addProperty("count", itemStack.getCount());
        if (itemStack.hasTag()) {
            json.addProperty("nbt", itemStack.getTag().toString());
        }
    }

    public static JsonObject toJson(ItemStack itemStack) {
        JsonObject json = new JsonObject();
        toJson(itemStack, json);
        return json;
    }

}
