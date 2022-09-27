package org.auioc.mcmod.arnicalib.game.entity;

import java.util.function.Function;
import java.util.function.Predicate;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;

public class EntityFunctions {

    public static final Predicate<Entity> IS_LIVING = (entity) -> entity instanceof LivingEntity;
    public static final Predicate<Entity> IS_PLAYER = (entity) -> entity instanceof Player;
    public static final Predicate<Entity> IS_LOCAL_PLAYER = (entity) -> entity instanceof net.minecraft.client.player.LocalPlayer;
    public static final Predicate<Entity> IS_SERVER_PLAYER = (entity) -> entity instanceof ServerPlayer;
    public static final Predicate<Entity> IS_FAKE_PLAYER = (entity) -> entity instanceof net.minecraftforge.common.util.FakePlayer;
    public static final Predicate<Entity> IS_PROJECTILE = (entity) -> entity instanceof net.minecraft.world.entity.projectile.Projectile;

    public static final Predicate<Entity> IS_FRIENDLY = (entity) -> getCategory(entity).isFriendly();
    public static final Predicate<Entity> IS_PERSISTENT = (entity) -> getCategory(entity).isPersistent();
    public static final Predicate<Entity> IS_MISC = (entity) -> getCategory(entity) == MobCategory.MISC;
    public static final Predicate<Entity> IS_MONSTER = (entity) -> getCategory(entity) == MobCategory.MONSTER;
    public static final Predicate<Entity> IS_CREATURE = (entity) -> getCategory(entity) == MobCategory.CREATURE;
    public static final Predicate<Entity> IS_AMBIENT = (entity) -> getCategory(entity) == MobCategory.AMBIENT;
    public static final Predicate<Entity> IS_AXOLOTLS = (entity) -> getCategory(entity) == MobCategory.AXOLOTLS;
    public static final Predicate<Entity> IS_UNDERGROUND_WATER_CREATURE = (entity) -> getCategory(entity) == MobCategory.UNDERGROUND_WATER_CREATURE;
    public static final Predicate<Entity> IS_WATER_CREATURE = (entity) -> getCategory(entity) == MobCategory.WATER_CREATURE;
    public static final Predicate<Entity> IS_WATER_AMBIENT = (entity) -> getCategory(entity) == MobCategory.WATER_AMBIENT;

    public static final Predicate<LivingEntity> IS_UNDEFINED = (living) -> living.getMobType() == MobType.UNDEFINED;
    public static final Predicate<LivingEntity> IS_DEFINED = (living) -> living.getMobType() != MobType.UNDEFINED;
    public static final Predicate<LivingEntity> IS_UNDEAD = (living) -> living.getMobType() == MobType.UNDEAD;
    public static final Predicate<LivingEntity> IS_ARTHROPOD = (living) -> living.getMobType() == MobType.ARTHROPOD;
    public static final Predicate<LivingEntity> IS_ILLAGER = (living) -> living.getMobType() == MobType.ILLAGER;
    public static final Predicate<LivingEntity> IS_WATER = (living) -> living.getMobType() == MobType.WATER;

    public static final Function<Entity, LivingEntity> CAST_TO_LIVING = (entity) -> (LivingEntity) entity;
    public static final Function<Player, ServerPlayer> CAST_TO_SERVER_PLAYER = (player) -> (ServerPlayer) player;

    private static MobCategory getCategory(Entity entity) {
        return entity.getType().getCategory();
    }

}
