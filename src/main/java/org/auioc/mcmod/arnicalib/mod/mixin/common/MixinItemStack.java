/*
 * Copyright (C) 2024 AUIOC.ORG
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

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.auioc.mcmod.arnicalib.mod.common.event.AHCommonEventFactory;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ItemStack.class)
public class MixinItemStack {

    @Inject(
        method = "Lnet/minecraft/world/item/ItemStack;inventoryTick(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/Entity;IZ)V",
        at = @At(
            value = "JUMP",
            opcode = Opcodes.IFNULL,
            shift = At.Shift.AFTER
        ),
        require = 1,
        allow = 1,
        cancellable = true
    )
    private void inventoryTick(Level pLevel, Entity pEntity, int pInventorySlot, boolean pIsCurrentItem, CallbackInfo ci) {
        // pEntity is always Player, inventoryTick is only called from net.minecraft.world.entity.player.Inventory#tick
        if (AHCommonEventFactory.onItemInventoryTick((Player) pEntity, pLevel, ((ItemStack) (Object) this), pInventorySlot, pIsCurrentItem)) {
            ci.cancel();
        }
    }

}
