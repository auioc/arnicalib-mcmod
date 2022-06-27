package org.auioc.mcmod.arnicalib.utils.game;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class RandomTeleporter {

    public static void unsafeTeleport(LivingEntity living, Vec3 center, double radius) {
        EntityUtils.teleportTo(living, PositionUtils.random(center, radius, living.getRandom()));
    }

    public static void unsafeTeleport(LivingEntity living, double radius) {
        unsafeTeleport(living, living.position(), radius);
    }

}
