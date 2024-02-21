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

package org.auioc.mcmod.arnicalib.game.world.phys;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

/**
 * @deprecated Moved to {@link RayCastUtils}.
 */
@Deprecated(since = "6.0.0", forRemoval = true)
public class RayTraceUtils {

    public static ViewRay getViewRay(Entity entity, double rayLength) {
        var from = entity.getEyePosition();
        var viewVector = entity.getViewVector(1.0F);
        var to = from.add(viewVector.x * rayLength, viewVector.y * rayLength, viewVector.z * rayLength);
        return new ViewRay(from, to);
    }

    // ====================================================================== //

    public static BlockHitResult getBlockHitResult(Entity entity, double rayLength, ClipContext.Block blockMode, ClipContext.Fluid fluidMode) {
        var viewRay = getViewRay(entity, rayLength);
        var rayCtx = new ClipContext(viewRay.from, viewRay.to, blockMode, fluidMode, entity);
        return entity.level().clip(rayCtx);
    }

    @Nullable
    public static EntityHitResult getEntityHitResult(Level level, Entity entity, Vec3 from, Vec3 to, AABB aabb, Predicate<Entity> predicate, float pickRadiusOffset) {
        double d0 = Double.MAX_VALUE;
        Entity target = null;

        for (var entity1 : level.getEntities(entity, aabb, predicate)) {
            var entity1aabb = entity1.getBoundingBox().inflate((double) (entity1.getPickRadius() + pickRadiusOffset));
            Optional<Vec3> optional = entity1aabb.clip(from, to);
            if (optional.isPresent()) {
                double d1 = from.distanceToSqr(optional.get());
                if (d1 < d0) {
                    target = entity1;
                    d0 = d1;
                }
            }
        }

        return target == null ? null : new EntityHitResult(target);
    }

    @Nullable
    public static EntityHitResult getEntityHitResult(Entity entity, Vec3 from, Vec3 to, Predicate<Entity> predicate, float pickRadiusOffset) {
        return getEntityHitResult(entity.level(), entity, from, to, (new AABB(from, to)).inflate(1.0D), predicate, pickRadiusOffset);
    }

    // ====================================================================== //

    @Nullable
    public static EntityHitResult getEntityHitResult(Entity entity, double rayLength, Predicate<Entity> predicate, float pickRadiusOffset, boolean blockMode) {
        var viewRay = getViewRay(entity, rayLength);
        var to = viewRay.to;
        if (blockMode) {
            var rayHitBlock = getBlockHitResult(entity, rayLength, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE);
            if (rayHitBlock.getType() != HitResult.Type.MISS) {
                to = rayHitBlock.getLocation();
            }
        }
        return getEntityHitResult(entity, viewRay.from, to, predicate, pickRadiusOffset);
    }

    @Nullable
    public static EntityHitResult getEntityHitResult(Entity entity, double rayLength, Predicate<Entity> predicate, float pickRadiusOffset) {
        var viewRay = getViewRay(entity, rayLength);
        return getEntityHitResult(entity, viewRay.from, viewRay.to, predicate, pickRadiusOffset);
    }

    @Nullable
    public static EntityHitResult getEntityHitResult(Entity entity, double rayLength, float pickRadiusOffset) {
        return getEntityHitResult(entity, rayLength, EntitySelector.NO_SPECTATORS, pickRadiusOffset);
    }

    @Nullable
    public static EntityHitResult getEntityHitResult(Entity entity, double rayLength) {
        return getEntityHitResult(entity, rayLength, 0.0F);
    }

    public static BlockHitResult getBlockHitResult(Entity entity, double rayLength) {
        return getBlockHitResult(entity, rayLength, ClipContext.Block.OUTLINE, ClipContext.Fluid.ANY);
    }

    // ====================================================================== //

    public static int rayHitEntityOrBlockOrMiss(
        Entity entity, double rayLength,
        float pickRadiusOffset, ClipContext.Block blockMode, ClipContext.Fluid fluidMode,
        ToIntFunction<EntityHitResult> e, ToIntFunction<BlockHitResult> b, ToIntFunction<BlockHitResult> m
    ) {
        EntityHitResult rayHitEntity = getEntityHitResult(entity, rayLength, pickRadiusOffset);
        if (rayHitEntity != null) {
            return e.applyAsInt(rayHitEntity);
        }

        BlockHitResult rayHitBlock = getBlockHitResult(entity, rayLength, blockMode, fluidMode);
        if (rayHitBlock.getType() != HitResult.Type.MISS) {
            return b.applyAsInt(rayHitBlock);
        } else {
            return m.applyAsInt(rayHitBlock);
        }
    }

    public static int rayHitEntityOrBlock(Entity entity, double rayLength, ToIntFunction<EntityHitResult> e, ToIntFunction<BlockHitResult> b) {
        return rayHitEntityOrBlockOrMiss(entity, rayLength, 0.0F, ClipContext.Block.OUTLINE, ClipContext.Fluid.ANY, e, b, m -> 0);
    }

    public static int rayHitLivingEntityOrBlock(Entity entity, double rayLength, ToIntFunction<EntityHitResult> e, ToIntFunction<BlockHitResult> b) {
        return rayHitEntityOrBlock(
            entity, rayLength,
            (r) -> {
                if (r.getEntity() instanceof LivingEntity) {
                    return e.applyAsInt(r);
                } else {
                    return 0;
                }
            }, b
        );
    }

    // ============================================================================================================== //


    public record ViewRay(Vec3 from, Vec3 to) { }

}
