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

package org.auioc.mcmod.arnicalib.game.critereon;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.critereon.EntitySubPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

/**
 * @since 7.0.1
 */
public record HealthPredicate(MinMaxBounds.Doubles current, MinMaxBounds.Doubles percent) implements EntitySubPredicate {

    public static MapCodec<HealthPredicate> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
        MinMaxBounds.Doubles.CODEC.optionalFieldOf("current", MinMaxBounds.Doubles.ANY).forGetter(o -> o.current),
        MinMaxBounds.Doubles.CODEC.optionalFieldOf("percent", MinMaxBounds.Doubles.ANY).forGetter(o -> o.percent)
    ).apply(instance, HealthPredicate::new));

    @Override
    public MapCodec<HealthPredicate> codec() { return CODEC; }

    @Override
    public boolean matches(Entity entity, ServerLevel level, @Nullable Vec3 position) {
        if (entity instanceof LivingEntity living) {
            if (!current.matches(living.getHealth())) {
                return false;
            }
            if (!percent.matches(living.getHealth() / living.getMaxHealth())) {
                return false;
            }
            return true;
        }
        return false;
    }


    // ============================================================================================================== //

    public static HealthPredicate current(MinMaxBounds.Doubles value) {
        return new HealthPredicate(value, MinMaxBounds.Doubles.ANY);
    }

    public static HealthPredicate percent(MinMaxBounds.Doubles value) {
        return new HealthPredicate(MinMaxBounds.Doubles.ANY, value);
    }

}
