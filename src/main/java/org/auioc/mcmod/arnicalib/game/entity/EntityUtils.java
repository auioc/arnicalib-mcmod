package org.auioc.mcmod.arnicalib.game.entity;

import org.auioc.mcmod.arnicalib.game.world.LevelUtils;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class EntityUtils {

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

}
