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

package org.auioc.mcmod.arnicalib.game.enchantment;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import org.auioc.mcmod.arnicalib.game.registry.RegistryEntryException;

import javax.annotation.Nonnull;
import java.util.Optional;

public class EnchantmentRegistry {

    @Nonnull
    public static Optional<Enchantment> get(ResourceLocation id) {
        return Optional.ofNullable(BuiltInRegistries.ENCHANTMENT.containsKey(id) ? BuiltInRegistries.ENCHANTMENT.get(id) : null);
    }

    @Nonnull
    public static Optional<Enchantment> get(String id) {
        return get(new ResourceLocation(id));
    }

    @Nonnull
    public static Enchantment getOrElseThrow(ResourceLocation id) {
        return get(id).orElseThrow(RegistryEntryException.UNKNOWN_ENCHANTMENT.create(id.toString()));
    }

    @Nonnull
    public static Enchantment getOrElseThrow(String id) {
        return get(id).orElseThrow(RegistryEntryException.UNKNOWN_ENCHANTMENT.create(id));
    }

}
