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
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.util.context.ContextKey;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

import java.util.Set;

/**
 * @since 7.0.1
 */
public record EnchantmentLevelCondition(MinMaxBounds.Ints level) implements LootItemCondition {

    public static MapCodec<EnchantmentLevelCondition> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
        MinMaxBounds.Ints.CODEC.optionalFieldOf("level", MinMaxBounds.Ints.ANY).forGetter(o -> o.level)
    ).apply(instance, EnchantmentLevelCondition::new));

    public static final LootItemConditionType TYPE = new LootItemConditionType(CODEC);

    @Override
    public LootItemConditionType getType() { return TYPE; }

    @Override
    public Set<ContextKey<?>> getReferencedContextParams() {
        return Set.of(LootContextParams.ENCHANTMENT_LEVEL);
    }

    @Override
    public boolean test(LootContext context) {
        var lvl = context.getParameter(LootContextParams.ENCHANTMENT_LEVEL);
        return level.matches(lvl);
    }

    // ============================================================================================================== //

    public static LootItemCondition.Builder of(MinMaxBounds.Ints level) {
        return () -> new EnchantmentLevelCondition(level);
    }

}
