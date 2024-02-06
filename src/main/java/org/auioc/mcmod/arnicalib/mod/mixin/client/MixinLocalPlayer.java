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

package org.auioc.mcmod.arnicalib.mod.mixin.client;

import org.auioc.mcmod.arnicalib.mod.client.event.AHClientEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.player.LocalPlayer;

@Mixin(value = LocalPlayer.class)
public class MixinLocalPlayer {

    @Shadow
    private int permissionLevel;

    @Inject(
        method = "Lnet/minecraft/client/player/LocalPlayer;setPermissionLevel(I)V",
        at = @At(value = "HEAD"),
        require = 1,
        allow = 1
    )
    private void setPermissionLevel(int pPermissionLevel, CallbackInfo ci) {
        AHClientEventFactory.onPermissionChanged(((LocalPlayer) (Object) this), this.permissionLevel, pPermissionLevel);
    }

}
