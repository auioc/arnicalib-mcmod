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

package org.auioc.mcmod.arnicalib.game.enchantment;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.enchantment.LevelBasedValue;

import java.util.List;

/**
 * @author LainIO24
 * @since 7.0.0
 */
public interface HLevelBasedValue extends LevelBasedValue {

    static Sum sum(LevelBasedValue... values) {
        return new Sum(List.of(values));
    }

    record Sum(List<LevelBasedValue> values) implements LevelBasedValue {

        public static final MapCodec<Sum> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                LevelBasedValue.CODEC.listOf().fieldOf("values").forGetter(o -> o.values)
            ).apply(instance, Sum::new)
        );

        public static Sum of(LevelBasedValue... values) {
            return new Sum(List.of(values));
        }

        @Override
        public float calculate(int level) {
            float r = 0.0F;
            for (var i : values) {
                r += i.calculate(level);
            }
            return r;
        }

        @Override
        public MapCodec<Sum> codec() { return CODEC; }

    }

}
