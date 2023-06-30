package org.auioc.mcmod.arnicalib.game.world.phys;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import javax.annotation.Nullable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class RayCastUtils { // TODO Test

    public static final float DEFAULT_PICK_INFLATION = 0.3F;
    public static final ToIntFunction<HitResult> DEFAULT_ON_MISS = (m) -> -1;

    // ============================================================================================================== //

    public static record Ray(Vec3 from, Vec3 to) {}

    // ============================================================================================================== //

    public static BlockHitResult block(Entity source, Ray ray, ClipContext.Block blockMode, ClipContext.Fluid fluidMode) {
        return (source.level()).clip(new ClipContext(ray.from, ray.to, blockMode, fluidMode, source));
    }

    // ====================================================================== //

    @Nullable
    public static EntityHitResult entity(Entity source, Ray ray, AABB aabb, Predicate<Entity> predicate, float pickInflation) {
        double d0 = Double.MAX_VALUE;
        Entity target = null;

        for (var entity : (source.level()).getEntities(source, aabb, predicate)) {
            var entity1aabb = entity.getBoundingBox().inflate((double) (entity.getPickRadius() + pickInflation));
            Optional<Vec3> optional = entity1aabb.clip(ray.from, ray.to);
            if (optional.isPresent()) {
                double d1 = (ray.from).distanceToSqr(optional.get());
                if (d1 < d0) {
                    target = entity;
                    d0 = d1;
                }
            }
        }

        return target == null ? null : new EntityHitResult(target);
    }

    @Nullable
    public static EntityHitResult entity(Entity source, Ray ray, Predicate<Entity> predicate, float pickInflation) {
        return entity(source, ray, (new AABB(ray.from, ray.to)).inflate(1.0D), predicate, pickInflation);
    }

    // ============================================================================================================== //

    public static Ray viewRay(Entity entity, double rayLength) {
        var from = entity.getEyePosition();
        var view = entity.getViewVector(1.0F);
        var to = from.add(view.x * rayLength, view.y * rayLength, view.z * rayLength);
        return new Ray(from, to);
    }

    // ====================================================================== //

    public static BlockHitResult blockOnView(Entity entity, double rayLength, ClipContext.Block blockMode, ClipContext.Fluid fluidMode) {
        return block(entity, viewRay(entity, rayLength), blockMode, fluidMode);
    }

    public static BlockHitResult blockOnView(Entity entity, double rayLength) {
        return blockOnView(entity, rayLength, ClipContext.Block.OUTLINE, ClipContext.Fluid.ANY);
    }

    // ====================================================================== //

    @Nullable
    public static EntityHitResult entityOnView(Entity entity, double rayLength, Predicate<Entity> predicate, float pickInflation, boolean blockMode) {
        var viewRay = viewRay(entity, rayLength);
        if (blockMode) {
            var rayHitBlock = block(entity, viewRay, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE);
            if (rayHitBlock.getType() != HitResult.Type.MISS) {
                viewRay = new Ray(viewRay.from, rayHitBlock.getLocation());
            }
        }
        return entity(entity, viewRay, predicate, pickInflation);
    }

    @Nullable
    public static EntityHitResult entityOnView(Entity entity, double rayLength, Predicate<Entity> predicate, float pickInflation) {
        return entity(entity, viewRay(entity, rayLength), predicate, pickInflation);
    }

    @Nullable
    public static EntityHitResult entityOnView(Entity entity, double rayLength, Predicate<Entity> predicate) {
        return entityOnView(entity, rayLength, predicate, DEFAULT_PICK_INFLATION);
    }

    @Nullable
    public static EntityHitResult entityOnView(Entity entity, double rayLength, float pickInflation) {
        return entityOnView(entity, rayLength, EntitySelector.NO_SPECTATORS, pickInflation);
    }

    @Nullable
    public static EntityHitResult entityOnView(Entity entity, double rayLength) {
        return entityOnView(entity, rayLength, EntitySelector.NO_SPECTATORS, DEFAULT_PICK_INFLATION);
    }

    // ====================================================================== //

    public static int onView(
        Entity entity, double rayLength,
        Predicate<Entity> predicate, float pickInflation, ToIntFunction<EntityHitResult> onHitEntity,
        ClipContext.Block blockMode, ClipContext.Fluid fluidMode, ToIntFunction<BlockHitResult> onHitBlock,
        ToIntFunction<HitResult> onMiss
    ) {
        var viewRay = viewRay(entity, rayLength);

        var hitEntity = entity(entity, viewRay, predicate, pickInflation);
        if (hitEntity != null) {
            return onHitEntity.applyAsInt(hitEntity);
        }

        var hitBlock = block(entity, viewRay, blockMode, fluidMode);
        if (hitBlock.getType() == HitResult.Type.BLOCK) {
            return onHitBlock.applyAsInt(hitBlock);
        }

        return onMiss.applyAsInt(hitBlock);
    }

    public static int onView(
        Entity entity, double rayLength,
        Predicate<Entity> predicate, float pickInflation, ToIntFunction<EntityHitResult> onHitEntity,
        ToIntFunction<BlockHitResult> onHitBlock
    ) {
        return onView(
            entity, rayLength,
            predicate, pickInflation, onHitEntity,
            ClipContext.Block.OUTLINE, ClipContext.Fluid.ANY, onHitBlock,
            DEFAULT_ON_MISS
        );
    }

    public static int onView(
        Entity entity, double rayLength,
        ToIntFunction<EntityHitResult> onHitEntity,
        ToIntFunction<BlockHitResult> onHitBlock
    ) {
        return onView(
            entity, rayLength,
            EntitySelector.NO_SPECTATORS, DEFAULT_PICK_INFLATION, onHitEntity,
            onHitBlock
        );
    }

}
