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

package org.auioc.mcmod.arnicalib.game.tag;

import org.auioc.mcmod.arnicalib.ArnicaLib;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class HItemTags {

    public static final TagKey<Item> BRICKS = create("bricks");
    public static final TagKey<Item> MAPS = create("maps");
    public static final TagKey<Item> SPAWN_EGGS = create("spawn_eggs");

    public static final TagKey<Item> AXES = create("axes");
    public static final TagKey<Item> HOES = create("hoes");
    public static final TagKey<Item> SWORDS = create("swords");
    public static final TagKey<Item> SHOVELS = create("shovels");
    public static final TagKey<Item> PICKAXES = create("pickaxes");

    public static final TagKey<Item> FOOD = create("food");
    public static final TagKey<Item> FOOD_MEAT = create("food/meat");
    public static final TagKey<Item> FOOD_FAST = create("food/fast");
    public static final TagKey<Item> FOOD_ALWAYS_EDIBLE = create("food/always_edible");

    public static final TagKey<Item> FIRE_RESISTANT_ITEMS = create("fire_resistant_items");
    public static final TagKey<Item> DAMAGEABLE_ITEMS = create("damageable_items");

    // ====================================================================== //

    public static void init() { }

    public static TagKey<Item> create(String _path) {
        return TagCreator.item(ArnicaLib.id(_path));
    }

}
