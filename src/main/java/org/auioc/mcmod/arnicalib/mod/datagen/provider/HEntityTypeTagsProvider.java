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

package org.auioc.mcmod.arnicalib.mod.datagen.provider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.auioc.mcmod.arnicalib.ArnicaLib;
import org.auioc.mcmod.arnicalib.game.datagen.tag.IHTagsProvider;
import org.auioc.mcmod.arnicalib.game.entity.EntityTypePredicates;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

import static org.auioc.mcmod.arnicalib.game.tag.HEntityTypeTags.*;

public class HEntityTypeTagsProvider extends EntityTypeTagsProvider implements IHTagsProvider<EntityType<?>> {

    public HEntityTypeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup, ExistingFileHelper fileHelper) {
        super(output, lookup, ArnicaLib.MOD_ID, fileHelper);
    }

    @Override
    public String getName() {
        return "HEntityTypeTags";
    }

    @Nonnull
    @Override
    public Registry<EntityType<?>> getRegistry() {
        return BuiltInRegistries.ENTITY_TYPE;
    }

    @Override
    protected void addTags(HolderLookup.Provider lookup) {
        tag(UNDEFINED_MOBS);
        tag(UNDEAD_MOBS).add(
            EntityType.DROWNED, EntityType.HUSK, EntityType.PHANTOM,
            EntityType.SKELETON, EntityType.SKELETON_HORSE, EntityType.STRAY,
            EntityType.WITHER, EntityType.WITHER_SKELETON, EntityType.ZOGLIN,
            EntityType.ZOMBIE, EntityType.ZOMBIE_HORSE,
            EntityType.ZOMBIE_VILLAGER, EntityType.ZOMBIFIED_PIGLIN
        );
        tag(ARTHROPODS).add(
            EntityType.BEE, EntityType.ENDERMAN, EntityType.SILVERFISH,
            EntityType.SPIDER, EntityType.CAVE_SPIDER
        );
        tag(ILLAGERS).add(
            EntityType.PILLAGER, EntityType.ILLUSIONER, EntityType.RAVAGER,
            EntityType.EVOKER, EntityType.VINDICATOR
        );
        tag(AQUATIC_MOBS).add(
            EntityType.AXOLOTL, EntityType.COD, EntityType.DOLPHIN,
            EntityType.ELDER_GUARDIAN, EntityType.GLOW_SQUID,
            EntityType.GUARDIAN, EntityType.PUFFERFISH, EntityType.SALMON,
            EntityType.SQUID, EntityType.TROPICAL_FISH, EntityType.TURTLE
        );

        addFromRegistry(tag(MISC_ENTITIES), EntityTypePredicates.IS_MISC);
        addFromRegistry(tag(MONSTERS), EntityTypePredicates.IS_MONSTER);
        addFromRegistry(tag(CREATURES), EntityTypePredicates.IS_CREATURE);
        addFromRegistry(tag(AXOLOTLS), EntityTypePredicates.IS_AXOLOTLS);
        addFromRegistry(tag(UNDERGROUND_WATER_CREATURES), EntityTypePredicates.IS_UNDERGROUND_WATER_CREATURE);
        addFromRegistry(tag(WATER_CREATURES), EntityTypePredicates.IS_WATER_CREATURE);
        addFromRegistry(tag(WATER_AMBIENT_ENTITIES), EntityTypePredicates.IS_WATER_AMBIENT);
        addFromRegistry(tag(AMBIENT_ENTITIES), EntityTypePredicates.IS_AMBIENT);

        addFromRegistry(tag(FRIENDLY_ENTITIES), EntityTypePredicates.IS_FRIENDLY);
        addFromRegistry(tag(PERSISTENT_ENTITIES), EntityTypePredicates.IS_PERSISTENT);
    }

}
