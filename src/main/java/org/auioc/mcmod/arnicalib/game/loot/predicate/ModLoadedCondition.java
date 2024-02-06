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

package org.auioc.mcmod.arnicalib.game.loot.predicate;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.neoforged.fml.ModList;
import org.auioc.mcmod.arnicalib.mod.server.loot.AHLootItemConditions;

public class ModLoadedCondition implements LootItemCondition {

    private final String modId;

    public ModLoadedCondition(String modId) {
        this.modId = modId;
    }

    @Override
    public LootItemConditionType getType() {
        return AHLootItemConditions.MOD_LOADED.get();
    }

    @Override
    public boolean test(LootContext ctx) {
        return ModList.get().isLoaded(this.modId);
    }

    // ============================================================================================================== //

    public static final Codec<ModLoadedCondition> CODEC =
        RecordCodecBuilder.create(
            instance -> instance
                .group(
                    Codec.STRING.fieldOf("mod").forGetter(o -> o.modId)
                )
                .apply(instance, ModLoadedCondition::new)
        );

}
