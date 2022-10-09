package org.auioc.mcmod.arnicalib.game.entity.projectile;

import javax.annotation.Nullable;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.Vec3;

public class ProjectileUtils {

    @Nullable
    public static Vec3 getShootingPosition(Projectile projectile) {
        return ((IHProjectile) projectile).getShootingPosition();
    }

}
