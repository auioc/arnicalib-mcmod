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

package org.auioc.mcmod.arnicalib.game.loot.function;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import org.auioc.mcmod.arnicalib.game.effect.MobEffectInstanceSerializer;
import org.auioc.mcmod.arnicalib.mod.server.loot.AHLootItemFunctions;

import java.util.List;

public class SetCustomEffectsFunction extends LootItemConditionalFunction {

    private final List<MobEffectInstance> effects;

    protected SetCustomEffectsFunction(List<LootItemCondition> conditions, List<MobEffectInstance> effects) {
        super(conditions);
        this.effects = effects;
    }

    @Override
    public LootItemFunctionType getType() {
        return AHLootItemFunctions.SET_CUSTOM_EFFECTS.get();
    }

    @Override
    protected ItemStack run(ItemStack stack, LootContext ctx) {
        return PotionUtils.setCustomEffects(stack, this.effects);
    }

    // ============================================================================================================== //

    public static final Codec<SetCustomEffectsFunction> CODEC =
        RecordCodecBuilder.create(
            instance -> commonFields(instance)
                .and(MobEffectInstanceSerializer.CODEC.listOf().fieldOf("effects").forGetter(o -> o.effects))
                .apply(instance, SetCustomEffectsFunction::new)
        );

}
