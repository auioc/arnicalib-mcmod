package org.auioc.mcmod.arnicalib.utils.game;

import java.util.function.Function;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class EntityUtils {

    /*================================================================================================================*/
    // #region Teleport

    public static void teleportTo(Entity entity, Vec3i pos) {
        entity.teleportTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D);
    }

    public static void teleportTo(Entity entity, Vec3 pos) {
        entity.teleportTo(pos.x, pos.y, pos.z);
    }

    public static void teleportTo(Entity entity, ResourceKey<Level> dim, Vec3 pos) {
        entity.changeDimension(LevelUtils.getLevel(dim), LevelUtils.createSimpleTeleporter(pos));
    }

    public static void teleportTo(Entity entity, ResourceKey<Level> dim, Vec3i pos) {
        entity.changeDimension(LevelUtils.getLevel(dim), LevelUtils.createSimpleTeleporter(pos));
    }

    // #endregion Teleport

    /*================================================================================================================*/
    // #region PredicatesFunctions

    public static final Predicate<Entity> IS_LIVING = (entity) -> entity instanceof LivingEntity;
    public static final Function<Entity, LivingEntity> CAST_TO_LIVING = (entity) -> (LivingEntity) entity;
    public static final Predicate<Entity> IS_PLAYER = (entity) -> entity instanceof net.minecraft.world.entity.player.Player;
    public static final Predicate<Entity> IS_LOCAL_PLAYER = (entity) -> entity instanceof net.minecraft.client.player.LocalPlayer;
    public static final Predicate<Entity> IS_SERVER_PLAYER = (entity) -> entity instanceof net.minecraft.server.level.ServerPlayer;
    public static final Predicate<Entity> IS_FAKE_PLAYER = (entity) -> entity instanceof net.minecraftforge.common.util.FakePlayer;
    public static final Predicate<Entity> IS_PROJECTILE = (entity) -> entity instanceof net.minecraft.world.entity.projectile.Projectile;

    public static MobCategory getCategory(Entity entity) {
        return entity.getType().getCategory();
    }

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

    // #endregion PredicatesFunctions

    /*================================================================================================================*/
    // #region RayTrace

    @Deprecated(since = "5.3.8")
    public static Vec3[] getEntityViewRay(Entity entity, double rayLength) {
        return RayTraceUtils.getEntityViewRay(entity, rayLength);
    }

    @Nullable
    @Deprecated(since = "5.3.8")
    public static EntityHitResult getEntityHitResult(Level level, Entity entity, Vec3 from, Vec3 to, AABB aabb, Predicate<Entity> predicate, float pickRadiusAddition) {
        return RayTraceUtils.getEntityHitResult(level, entity, from, to, aabb, predicate, pickRadiusAddition);
    }

    @Deprecated(since = "5.3.8")
    public static BlockHitResult getBlockHitResult(Entity entity, double rayLength, ClipContext.Block blockMode, ClipContext.Fluid fluidMode) {
        return RayTraceUtils.getBlockHitResult(entity, rayLength, blockMode, fluidMode);
    }

    @Nullable
    @Deprecated(since = "5.3.8")
    public static EntityHitResult getEntityHitResult(Entity entity, double rayLength, float pickRadiusAddition, boolean blockMode) {
        return RayTraceUtils.getEntityHitResult(entity, rayLength, pickRadiusAddition, blockMode);
    }

    @Nullable
    @Deprecated(since = "5.3.8")
    public static EntityHitResult getEntityHitResult(Entity entity, Vec3 from, Vec3 to, float pickRadiusAddition) {
        return getEntityHitResult(entity.level, entity, from, to, entity.getBoundingBox().expandTowards(to).inflate(1.0D), EntitySelector.NO_SPECTATORS, pickRadiusAddition);
    }

    @Nullable
    @Deprecated(since = "5.3.8")
    public static EntityHitResult getEntityHitResult(Entity entity, Vec3 from, Vec3 to) {
        return getEntityHitResult(entity, from, to, 0.0F);
    }

    @Deprecated(since = "5.3.8")
    public static BlockHitResult getBlockHitResult(Entity entity, double rayLength) {
        return getBlockHitResult(entity, rayLength, ClipContext.Block.OUTLINE, ClipContext.Fluid.ANY);
    }

    @Deprecated(since = "5.3.8")
    public static EntityHitResult getEntityHitResult(Entity entity, double rayLength) {
        return getEntityHitResult(entity, rayLength, 0.0F, false);
    }

    @Deprecated(since = "5.3.8")
    public static int rayHitEntityOrBlockOrMiss(
        Entity entity, double rayLength,
        float pickRadiusAddition, ClipContext.Block blockMode, ClipContext.Fluid fluidMode,
        Function<EntityHitResult, Integer> e, Function<BlockHitResult, Integer> b, Function<BlockHitResult, Integer> m
    ) {
        return RayTraceUtils.rayHitEntityOrBlockOrMiss(entity, rayLength, pickRadiusAddition, blockMode, fluidMode, e, b, m);
    }

    @Deprecated(since = "5.3.8")
    public static int rayHitEntityOrBlock(Entity entity, double rayLength, Function<EntityHitResult, Integer> e, Function<BlockHitResult, Integer> b) {
        return rayHitEntityOrBlockOrMiss(entity, rayLength, 0.0F, ClipContext.Block.OUTLINE, ClipContext.Fluid.ANY, e, b, m -> 0);
    }

    @Deprecated(since = "5.3.8")
    public static int rayHitLivingEntityOrBlock(Entity entity, double rayLength, Function<EntityHitResult, Integer> e, Function<BlockHitResult, Integer> b) {
        return RayTraceUtils.rayHitLivingEntityOrBlock(entity, rayLength, e, b);
    }

    // #endregion RayTrace

}
