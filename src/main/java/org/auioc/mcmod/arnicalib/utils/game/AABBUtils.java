package org.auioc.mcmod.arnicalib.utils.game;

import net.minecraft.core.Position;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.AABB;

public interface AABBUtils {

    static AABB moveTo(AABB aabb, double x, double y, double z) {
        return aabb.move(
            x - ((aabb.minX + aabb.maxX) / 2), // (x+0.5)-(min+(max-min)/2)
            y - aabb.minY,
            z - ((aabb.minZ + aabb.maxZ) / 2)
        );
    }

    static AABB moveTo(AABB aabb, Position pos) {
        return moveTo(aabb, pos.x(), pos.y(), pos.z());
    }

    static AABB moveTo(AABB aabb, int x, int y, int z) {
        return moveTo(aabb, x + 0.5D, y, x + 0.5D);
    }

    static AABB moveTo(AABB aabb, Vec3i pos) {
        return moveTo(aabb, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D);
    }

    static boolean isEmpty(AABB aabb, Level level) {
        return level.getBlockStates(aabb).allMatch(BlockState::isAir);
    }

    static boolean containsSolid(AABB aabb, Level level) {
        return level.getBlockStates(aabb).map(BlockState::getMaterial).anyMatch(Material::isSolid);
    }

    static boolean containsLiquid(AABB aabb, Level level) {
        return level.getBlockStates(aabb).map(BlockState::getMaterial).anyMatch(Material::isLiquid);
    }

}
