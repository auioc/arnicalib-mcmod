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
import net.minecraft.world.entity.EntityType;

public class HEntityTypeTags {

    public static final TagKey<EntityType<?>> UNDEFINED_MOBS = create("mob/undefined_mobs");
    public static final TagKey<EntityType<?>> UNDEAD_MOBS = create("mob/undead_mobs");
    public static final TagKey<EntityType<?>> ARTHROPODS = create("mob/arthropods");
    public static final TagKey<EntityType<?>> ILLAGERS = create("mob/illagers");
    public static final TagKey<EntityType<?>> AQUATIC_MOBS = create("mob/aquatic_mobs");

    public static final TagKey<EntityType<?>> MISC_ENTITIES = create("misc_entities");
    public static final TagKey<EntityType<?>> MONSTERS = create("monsters");
    public static final TagKey<EntityType<?>> CREATURES = create("creatures");
    public static final TagKey<EntityType<?>> AXOLOTLS = create("axolotls");
    public static final TagKey<EntityType<?>> UNDERGROUND_WATER_CREATURES = create("underground_water_creatures");
    public static final TagKey<EntityType<?>> WATER_CREATURES = create("water_creatures");
    public static final TagKey<EntityType<?>> WATER_AMBIENT_ENTITIES = create("water_ambient_entities");
    public static final TagKey<EntityType<?>> AMBIENT_ENTITIES = create("ambient_entities");

    public static final TagKey<EntityType<?>> FRIENDLY_ENTITIES = create("friendly_entities");
    public static final TagKey<EntityType<?>> PERSISTENT_ENTITIES = create("persistent_entities");

    public static void init() { }

    public static TagKey<EntityType<?>> create(String _path) {
        return TagCreator.entityType(ArnicaLib.id(_path));
    }

}
