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

package org.auioc.mcmod.arnicalib.mod.mixin.common;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.Vec3;
import org.auioc.mcmod.arnicalib.game.entity.projectile.IHProjectile;
import org.auioc.mcmod.arnicalib.game.nbt.NbtUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(value = Projectile.class)
public class MixinProjectile implements IHProjectile {

    @Shadow
    private boolean hasBeenShot;

    @Nullable
    private Vec3 shootingPosition;

    @Nullable
    @Override
    public Vec3 getShootingPosition() {
        return this.shootingPosition;
    }

    @Inject(
        method = "Lnet/minecraft/world/entity/projectile/Projectile;tick()V",
        at = @At(value = "HEAD"),
        require = 1,
        allow = 1
    )
    private void tick(CallbackInfo ci) {
        if (!this.hasBeenShot) {
            var pos = ((Projectile) (Object) this).position();
            this.shootingPosition = new Vec3(pos.x, pos.y, pos.z);
        }
    }

    @Inject(
        method = "Lnet/minecraft/world/entity/projectile/Projectile;readAdditionalSaveData(Lnet/minecraft/nbt/CompoundTag;)V",
        at = @At(value = "TAIL"),
        require = 1,
        allow = 1
    )
    private void readAdditionalSaveData(CompoundTag nbt, CallbackInfo ci) {
        if (nbt.contains("ShootingPosition", 6)) {
            this.shootingPosition = NbtUtils.readVec3(nbt.getList("ShootingPosition", 6));
        }
    }

    @Inject(
        method = "Lnet/minecraft/world/entity/projectile/Projectile;addAdditionalSaveData(Lnet/minecraft/nbt/CompoundTag;)V",
        at = @At(value = "TAIL"),
        require = 1,
        allow = 1
    )
    private void addAdditionalSaveData(CompoundTag nbt, CallbackInfo ci) {
        if (this.shootingPosition != null) {
            nbt.put("ShootingPosition", NbtUtils.writeVec3(this.shootingPosition));
        }
    }

}
