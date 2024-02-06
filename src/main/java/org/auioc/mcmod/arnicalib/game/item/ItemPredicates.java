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

import java.util.function.Predicate;

import org.auioc.mcmod.arnicalib.game.registry.RegistryUtils;
import org.auioc.mcmod.arnicalib.game.registry.VanillaPredicates;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ItemPredicates {

    public static final Predicate<ItemStack> IS_VANILLA = (itemStack) -> VanillaPredicates.ID.test(RegistryUtils.id(itemStack.getItem()));
    public static final Predicate<Item> IS_AIR = (item) -> item == Items.AIR;
    // public static final Predicate<Item> IS_CATEGORIZED = (item) -> item.getItemCategory() != null;
    // TODO getItemCategory ?

}
