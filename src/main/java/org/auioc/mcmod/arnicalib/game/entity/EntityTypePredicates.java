package org.auioc.mcmod.arnicalib.game.entity;

import java.util.function.Predicate;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class EntityTypePredicates {

    public static final Predicate<EntityType<?>> IS_FRIENDLY = (type) -> getCategory(type).isFriendly();
    public static final Predicate<EntityType<?>> IS_PERSISTENT = (type) -> getCategory(type).isPersistent();

    public static final Predicate<EntityType<?>> IS_MISC = (type) -> getCategory(type) == MobCategory.MISC;
    public static final Predicate<EntityType<?>> IS_MONSTER = (type) -> getCategory(type) == MobCategory.MONSTER;
    public static final Predicate<EntityType<?>> IS_CREATURE = (type) -> getCategory(type) == MobCategory.CREATURE;
    public static final Predicate<EntityType<?>> IS_AMBIENT = (type) -> getCategory(type) == MobCategory.AMBIENT;
    public static final Predicate<EntityType<?>> IS_AXOLOTLS = (type) -> getCategory(type) == MobCategory.AXOLOTLS;
    public static final Predicate<EntityType<?>> IS_UNDERGROUND_WATER_CREATURE = (type) -> getCategory(type) == MobCategory.UNDERGROUND_WATER_CREATURE;
    public static final Predicate<EntityType<?>> IS_WATER_CREATURE = (type) -> getCategory(type) == MobCategory.WATER_CREATURE;
    public static final Predicate<EntityType<?>> IS_WATER_AMBIENT = (type) -> getCategory(type) == MobCategory.WATER_AMBIENT;

    private static MobCategory getCategory(EntityType<?> entityType) {
        return entityType.getCategory();
    }

}
