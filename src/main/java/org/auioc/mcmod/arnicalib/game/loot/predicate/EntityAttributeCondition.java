package org.auioc.mcmod.arnicalib.game.loot.predicate;

import java.util.Set;
import org.apache.commons.lang3.function.FailableToDoubleFunction;
import org.auioc.mcmod.arnicalib.base.validate.Validate;
import org.auioc.mcmod.arnicalib.mod.server.loot.AHLootItemConditions;
import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
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
import net.minecraftforge.registries.ForgeRegistries;

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
        if (entity != null && entity instanceof LivingEntity living) {
            var instance = living.getAttribute(this.attribute);
            if (instance != null) {
                try {
                    double value = this.valueType.getValue(instance);
                    return this.value.matches(value);
                } catch (IllegalArgumentException e) {
                }
            }
        }
        return false;
    }

    // ============================================================================================================== //

    public static enum AttributeValueType {

        DEFAULT_VALUE("default_value", (i) -> i.getAttribute().getDefaultValue()),
        BASE_VALUE("base_value", (i) -> i.getBaseValue()),
        CURRENT_VALUE("current_value", (i) -> i.getValue()),
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
            throw new IllegalArgumentException("Attribute '" + attr.getRegistryName() + "' is not a RangedAttribute");
        }

    }

    // ============================================================================================================== //

    public static class Serializer implements net.minecraft.world.level.storage.loot.Serializer<EntityAttributeCondition> {

        @Override
        public void serialize(JsonObject json, EntityAttributeCondition instance, JsonSerializationContext ctx) {
            json.addProperty("attribute", instance.attribute.getRegistryName().toString());
            json.addProperty("type", instance.valueType.getName());
            json.add("value", instance.value.serializeToJson());
            json.add("entity", ctx.serialize(instance.entityTarget));
        }

        @Override
        public EntityAttributeCondition deserialize(JsonObject json, JsonDeserializationContext ctx) {
            var attributeId = new ResourceLocation(GsonHelper.getAsString(json, "attribute"));
            Validate.isTrue(ForgeRegistries.ATTRIBUTES.containsKey(attributeId), "Unknown attribute '" + attributeId + "'");

            return new EntityAttributeCondition(
                ForgeRegistries.ATTRIBUTES.getValue(attributeId),
                AttributeValueType.getByName(GsonHelper.getAsString(json, "type")),
                MinMaxBounds.Doubles.fromJson(json.get("value")),
                GsonHelper.getAsObject(json, "entity", ctx, EntityTarget.class)
            );
        }

    }

}
