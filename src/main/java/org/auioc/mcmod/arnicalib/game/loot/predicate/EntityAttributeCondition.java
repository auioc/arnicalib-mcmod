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

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootContext.EntityTarget;
import net.minecraft.world.level.storage.loot.parameters.LootContextParam;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import org.apache.commons.lang3.function.FailableToDoubleFunction;
import org.auioc.mcmod.arnicalib.game.codec.EnumCodec;
import org.auioc.mcmod.arnicalib.mod.server.loot.AHLootItemConditions;

import java.util.Set;

public class EntityAttributeCondition implements LootItemCondition {

    private final Attribute attribute;
    private final ValueType valueType;
    private final MinMaxBounds.Doubles value;
    private final EntityTarget entityTarget;

    public EntityAttributeCondition(Attribute attribute, ValueType valueType, MinMaxBounds.Doubles value, EntityTarget entityTarget) {
        this.attribute = attribute;
        this.valueType = valueType;
        this.value = value;
        this.entityTarget = entityTarget;
    }

    @Override
    public LootItemConditionType getType() {
        return AHLootItemConditions.ENTITY_ATTRIBUTE.get();
    }

    public Set<LootContextParam<?>> getReferencedContextParams() {
        return ImmutableSet.of(LootContextParams.ORIGIN, this.entityTarget.getParam());
    }

    @Override
    public boolean test(LootContext ctx) {
        var entity = ctx.getParamOrNull(this.entityTarget.getParam());
        if (entity instanceof LivingEntity living) {
            var instance = living.getAttribute(this.attribute);
            if (instance != null) {
                try {
                    double value = this.valueType.getValue(instance);
                    return this.value.matches(value);
                } catch (IllegalArgumentException ignored) {
                }
            }
        }
        return false;
    }

    // ============================================================================================================== //


    public enum ValueType {

        DEFAULT((i) -> i.getAttribute().getDefaultValue()),
        BASE(AttributeInstance::getBaseValue),
        CURRENT(AttributeInstance::getValue),
        MAX((i) -> castToRangedAttribute(i).getMaxValue()),
        MIN((i) -> castToRangedAttribute(i).getMinValue());
        private final FailableToDoubleFunction<AttributeInstance, IllegalArgumentException> getter;

        ValueType(FailableToDoubleFunction<AttributeInstance, IllegalArgumentException> getter) {
            this.getter = getter;
        }

        public double getValue(AttributeInstance instance) { return this.getter.applyAsDouble(instance); }

        private static RangedAttribute castToRangedAttribute(AttributeInstance instance) {
            var attr = instance.getAttribute();
            if (attr instanceof RangedAttribute rangeAttr) return rangeAttr;
            throw new IllegalArgumentException("Attribute '" + attr.getDescriptionId() + "' is not a RangedAttribute");
        }

    }

    // ============================================================================================================== //

    public static final Codec<EntityAttributeCondition> CODEC =
        RecordCodecBuilder.create(
            instance -> instance
                .group(
                    BuiltInRegistries.ATTRIBUTE.byNameCodec().fieldOf("attribute").forGetter(o -> o.attribute),
                    EnumCodec.byNameLowerCase(ValueType.class).fieldOf("type").forGetter((o -> o.valueType)),
                    MinMaxBounds.Doubles.CODEC.fieldOf("value").forGetter((o -> o.value)),
                    EntityTarget.CODEC.fieldOf("entity").forGetter((o -> o.entityTarget))
                )
                .apply(instance, EntityAttributeCondition::new)
        );

}
