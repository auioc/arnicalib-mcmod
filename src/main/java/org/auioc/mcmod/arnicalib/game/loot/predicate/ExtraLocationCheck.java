/*
 * Copyright (C) 2025 AUIOC.ORG
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

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.util.context.ContextKey;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import org.auioc.mcmod.arnicalib.game.critereon.BiomePredicate;

import java.util.Optional;
import java.util.Set;

/**
 * @since 7.0.0
 */
public record ExtraLocationCheck(Optional<LocationCheck> location, Optional<BiomePredicate> biome) implements LootItemCondition {

    public static MapCodec<ExtraLocationCheck> CODEC = RecordCodecBuilder.mapCodec(
        instance -> instance.group(
            LocationCheck.CODEC.codec().optionalFieldOf("location").forGetter(o -> o.location),
            BiomePredicate.CODEC.optionalFieldOf("biome").forGetter(o -> o.biome)
        ).apply(instance, ExtraLocationCheck::new));

    public static final LootItemConditionType TYPE = new LootItemConditionType(CODEC);

    @Override
    public LootItemConditionType getType() { return TYPE; }

    @Override
    public Set<ContextKey<?>> getReferencedContextParams() {
        return Set.of(LootContextParams.ORIGIN);
    }

    @Override
    public boolean test(LootContext context) {
        if (location.isPresent() && !location.get().test(context)) {
            return false;
        }
        var pos = context.getOptionalParameter(LootContextParams.ORIGIN);
        if (pos == null) {
            return false;
        }
        var level = context.getLevel();
        if (biome.isPresent() && !biome.get().matches(level.getBiome(BlockPos.containing(pos)))) {
            return false;
        }
        return true;
    }

    // ============================================================================================================== //

    public static LootItemCondition.Builder checkLocation(LocationPredicate.Builder location) {
        return () -> new ExtraLocationCheck(Optional.of(new LocationCheck(Optional.of(location.build()), BlockPos.ZERO)), Optional.empty());
    }

    public static LootItemCondition.Builder checkLocation(LocationPredicate.Builder location, BlockPos offset) {
        return () -> new ExtraLocationCheck(Optional.of(new LocationCheck(Optional.of(location.build()), offset)), Optional.empty());
    }

    public static LootItemCondition.Builder checkBiome(BiomePredicate biome) {
        return () -> new ExtraLocationCheck(Optional.empty(), Optional.of(biome));
    }

}
