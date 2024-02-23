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

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantment.Rarity;
import org.auioc.mcmod.arnicalib.game.registry.RegistryUtils;
import org.auioc.mcmod.arnicalib.game.registry.VanillaPredicates;

import java.util.function.Predicate;

public class EnchantmentPredicates {

    public static final Predicate<Enchantment> IS_VANILLA = (e) -> VanillaPredicates.ID.test(RegistryUtils.id(e));

    public static final Predicate<Enchantment> IS_TREASURE_ONLY = Enchantment::isTreasureOnly;
    public static final Predicate<Enchantment> IS_CURSE = Enchantment::isCurse;
    public static final Predicate<Enchantment> IS_TRADEABLE = Enchantment::isTradeable;
    public static final Predicate<Enchantment> IS_DISCOVERABLE = Enchantment::isDiscoverable;

    public static final Predicate<Enchantment> IS_COMMON = (e) -> e.getRarity() == Rarity.COMMON;
    public static final Predicate<Enchantment> IS_UNCOMMON = (e) -> e.getRarity() == Rarity.UNCOMMON;
    public static final Predicate<Enchantment> IS_RARE = (e) -> e.getRarity() == Rarity.RARE;
    public static final Predicate<Enchantment> IS_VERY_RARE = (e) -> e.getRarity() == Rarity.VERY_RARE;

}
