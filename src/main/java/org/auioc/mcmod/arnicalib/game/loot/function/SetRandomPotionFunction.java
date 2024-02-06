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
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import org.auioc.mcmod.arnicalib.base.random.RandomUtils;
import org.auioc.mcmod.arnicalib.game.registry.RegistryUtils;
import org.auioc.mcmod.arnicalib.mod.server.loot.AHLootItemFunctions;

import java.util.List;

public class SetRandomPotionFunction extends LootItemConditionalFunction {

    private final List<Potion> potions;
    private final boolean isBlacklist;

    protected SetRandomPotionFunction(List<LootItemCondition> conditions, List<Potion> potions, boolean isBlacklist) {
        super(conditions);
        this.potions = potions;
        this.isBlacklist = isBlacklist;
    }

    @Override
    public LootItemFunctionType getType() {
        return AHLootItemFunctions.SET_RANDOM_POTION.get();
    }

    @Override
    protected ItemStack run(ItemStack stack, LootContext ctx) {
        if (this.potions.isEmpty()) {
            return PotionUtils.setPotion(stack, getRandomPotion(ctx.getRandom()));
        } else {
            if (this.isBlacklist) {
                Potion potion;
                while (true) {
                    potion = getRandomPotion(ctx.getRandom());
                    if (!this.potions.contains(potion)) {
                        break;
                    }
                }
                return PotionUtils.setPotion(stack, potion);
            }
            return PotionUtils.setPotion(stack, RandomUtils.pickOneFromList(this.potions));
        }
    }

    private static Potion getRandomPotion(RandomSource random) {
        return RegistryUtils.random(BuiltInRegistries.POTION, random);
    }

    // ============================================================================================================== //

    public static final Codec<SetRandomPotionFunction> CODEC =
        RecordCodecBuilder.create(
            instance -> commonFields(instance)
                .and(
                    instance.group(
                        BuiltInRegistries.POTION.byNameCodec().listOf().fieldOf("potions").forGetter(o -> o.potions),
                        Codec.BOOL.optionalFieldOf("blacklist", Boolean.TRUE).forGetter(o -> o.isBlacklist)
                    )
                )
                .apply(instance, SetRandomPotionFunction::new)
        );

}
