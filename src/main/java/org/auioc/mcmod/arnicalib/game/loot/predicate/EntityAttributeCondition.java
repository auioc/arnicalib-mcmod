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
    private final AttributeValueType valueType;
    private final MinMaxBounds.Doubles value;
    private final EntityTarget entityTarget;

    public EntityAttributeCondition(Attribute attribute, AttributeValueType valueType, MinMaxBounds.Doubles value, EntityTarget entityTarget) {
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


    public static enum AttributeValueType {

        DEFAULT_VALUE("default_value", (i) -> i.getAttribute().getDefaultValue()),
        BASE_VALUE("base_value", AttributeInstance::getBaseValue),
        CURRENT_VALUE("current_value", AttributeInstance::getValue),
        MAX_VALUE("max_value", (i) -> castToRangedAttribute(i).getMaxValue()),
        MIN_VALUE("min_value", (i) -> castToRangedAttribute(i).getMinValue());

        private final String name;
        private final FailableToDoubleFunction<AttributeInstance, IllegalArgumentException> getter;

        private AttributeValueType(String name, FailableToDoubleFunction<AttributeInstance, IllegalArgumentException> getter) {
            this.name = name;
            this.getter = getter;
        }

        public String getName() { return this.name; }

        public double getValue(AttributeInstance instance) { return this.getter.applyAsDouble(instance); }

        public static AttributeValueType getByName(String name) {
            for (var target : values()) if (target.name.equals(name)) return target;
            throw new IllegalArgumentException("Invalid attribute value type '" + name + "'");
        }

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
                    EnumCodec.nameLowercaseWithCache(AttributeValueType.class).fieldOf("valueType").forGetter((o -> o.valueType)),
                    MinMaxBounds.Doubles.CODEC.fieldOf("value").forGetter((o -> o.value)),
                    EntityTarget.CODEC.fieldOf("").forGetter((o -> o.entityTarget))
                )
                .apply(instance, EntityAttributeCondition::new)
        );

}
