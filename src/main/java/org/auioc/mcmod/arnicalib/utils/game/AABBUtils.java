package org.auioc.mcmod.arnicalib.utils.game;

import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.AABB;

public interface AABBUtils {

    static AABB moveTo(AABB aabb, double x, double y, double z) {
        return aabb.move(
            x - ((aabb.minX + aabb.maxX) / 2), // (x+0.5)-(min+(max-min)/2)
            y - aabb.minY,
            z - ((aabb.minZ + aabb.maxZ) / 2)
        );
    }

    static AABB moveTo(AABB aabb, int x, int y, int z) {
        return moveTo(aabb, x + 0.5D, y, x + 0.5D);
    }

    static AABB moveTo(AABB aabb, BlockPos pos) {
        return moveTo(aabb, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D);
    }

}
