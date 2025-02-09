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

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.context.ContextKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

import java.util.Set;

/**
 * Notice:
 * In general, the LootTable for Block is only called when it has been checked that player has used the correct tool (see {@link Block#playerDestroy}),
 * so this predicate can be used for other purposes, such as enchantment or advancement.
 *
 * @since 7.0.2
 */
public record ToolCorrectCheck(boolean correct) implements LootItemCondition {

    public static final MapCodec<ToolCorrectCheck> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
        Codec.BOOL.optionalFieldOf("correct", true).forGetter(o -> o.correct)
    ).apply(instance, ToolCorrectCheck::new));

    public static final LootItemConditionType TYPE = new LootItemConditionType(CODEC);

    @Override
    public LootItemConditionType getType() { return TYPE; }

    @Override
    public Set<ContextKey<?>> getReferencedContextParams() {
        return Set.of(LootContextParams.TOOL, LootContextParams.BLOCK_STATE);
    }

    @Override
    public boolean test(LootContext context) {
        var stack = context.getOptionalParameter(LootContextParams.TOOL);
        var state = context.getOptionalParameter(LootContextParams.BLOCK_STATE);
        if (stack != null && state != null) {
            if (!state.requiresCorrectToolForDrops()) {
                return true;
            }
            return stack.isCorrectToolForDrops(state) == correct;
        }
        return false;
    }

}
