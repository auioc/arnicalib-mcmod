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

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import org.auioc.mcmod.arnicalib.game.registry.RegistryEntryException;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

public class ItemRegistry {

    @Nonnull
    public static Optional<Item> get(ResourceLocation id) {
        return Optional.ofNullable(BuiltInRegistries.ITEM.containsKey(id) ? BuiltInRegistries.ITEM.get(id) : null);
    }

    @Nonnull
    public static Optional<Item> get(String id) {
        return get(new ResourceLocation(id));
    }

    @Nonnull
    public static Item getOrElseThrow(ResourceLocation id) {
        return get(id).orElseThrow(RegistryEntryException.UNKNOWN_ITEM.create(id.toString()));
    }

    @Nonnull
    public static Item getOrElseThrow(String id) {
        return get(id).orElseThrow(RegistryEntryException.UNKNOWN_ITEM.create(id));
    }

    public static List<Item> getItems(List<String> idList) {
        return idList.stream().map(ItemRegistry::getOrElseThrow).toList();
    }

}
