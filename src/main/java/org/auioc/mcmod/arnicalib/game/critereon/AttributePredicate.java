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
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.phys.Vec3;
import org.apache.commons.lang3.function.FailableToDoubleFunction;
import org.auioc.mcmod.arnicalib.game.codec.EnumCodec;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import static org.auioc.mcmod.arnicalib.ArnicaLib.LOGGER;

/**
 * @since 7.0.1
 */
public record AttributePredicate(
    Holder<Attribute> attribute,
    ValueType type,
    MinMaxBounds.Doubles value
) implements EntitySubPredicate {

    private static final Marker MARKER = MarkerFactory.getMarker("AttributePredicate");

    public static MapCodec<AttributePredicate> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
        Attribute.CODEC.fieldOf("attribute").forGetter(o -> o.attribute),
        EnumCodec.byString(ValueType.class, e -> e.name).fieldOf("type").forGetter(o -> o.type),
        MinMaxBounds.Doubles.CODEC.fieldOf("value").forGetter(o -> o.value)
    ).apply(instance, AttributePredicate::new));

    @Override
    public MapCodec<AttributePredicate> codec() { return CODEC; }

    @Override
    public boolean matches(Entity entity, ServerLevel level, @Nullable Vec3 position) {
        if (entity instanceof LivingEntity living) {
            var instance = living.getAttribute(attribute);
            if (instance != null) {
                try {
                    double value = type.getValue(instance);
                    return this.value.matches(value);
                } catch (Exception e) {
                    LOGGER.warn(MARKER, "An error occurred while matching, return false", e);
                    return false;
                }
            }
        }
        return false;
    }

    // ============================================================================================================== //

    public enum ValueType {

        DEFAULT("default", (i) -> i.getAttribute().value().getDefaultValue()),
        BASE("base", AttributeInstance::getBaseValue),
        CURRENT("current", AttributeInstance::getValue),
        MAX("max", (i) -> castToRangedAttribute(i).getMaxValue()),
        MIN("min", (i) -> castToRangedAttribute(i).getMinValue());

        private final String name;
        private final FailableToDoubleFunction<AttributeInstance, IllegalArgumentException> getter;

        ValueType(String name, FailableToDoubleFunction<AttributeInstance, IllegalArgumentException> getter) {
            this.name = name;
            this.getter = getter;
        }

        public double getValue(AttributeInstance instance) { return this.getter.applyAsDouble(instance); }

        private static RangedAttribute castToRangedAttribute(AttributeInstance instance) {
            var attr = instance.getAttribute();
            if (attr.value() instanceof RangedAttribute ranged) {
                return ranged;
            }
            throw new IllegalArgumentException("Attribute '" + attr.getRegisteredName() + "' is not a RangedAttribute");
        }

    }

}
