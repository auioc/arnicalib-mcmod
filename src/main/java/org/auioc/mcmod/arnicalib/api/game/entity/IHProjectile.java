package org.auioc.mcmod.arnicalib.api.game.entity;

import javax.annotation.Nullable;
import net.minecraft.world.phys.Vec3;

public interface IHProjectile {

    @Nullable
    default Vec3 getShootingPosition() {
        return null;
    }

}
