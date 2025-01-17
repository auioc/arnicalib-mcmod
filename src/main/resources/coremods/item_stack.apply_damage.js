/*
 * Copyright (C) 2025 AUIOC.ORG
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

function initializeCoreMod() {
    Java.type('net.neoforged.coremod.api.ASMAPI').loadFile('coremods/util/utils.js');

    return {
        'ItemStack#hurt': {
            target: {
                type: 'METHOD',
                class: 'net.minecraft.world.item.ItemStack',
                methodName: 'applyDamage',
                methodDesc: '(ILnet/minecraft/world/entity/LivingEntity;Ljava/util/function/Consumer;)V'
            },
            transformer: function (methodNode) {
                var insns = methodNode.instructions;

                var injects = [
                    aLoad(0),
                    iLoad(1),
                    aLoad(2),
                    invokeStatic(
                        'org/auioc/mcmod/arnicalib/mod/event/AHEventHooks',
                        'onApplyItemDamage',
                        '(Lnet/minecraft/world/item/ItemStack;ILnet/minecraft/world/entity/LivingEntity;)I'
                    ),
                    iStore(1)

                ];
                insns.insert(toInsnList(injects));

                setMaxLocals(methodNode, 7);

                // printMethodNode(methodNode);
                return methodNode;
            }
        }
    };
}


//! LocalVariableTable
/*
    Slot    Name            Signature
    4       serverPlayer    Lnet/minecraft/server/level/ServerPlayer;
    4       item            Lnet/minecraft/world/item/Item;
    0       this            Lnet/minecraft/world/item/ItemStack;
    1       p_361754_       I
    2       p_364853_       Lnet/minecraft/world/entity/LivingEntity;
    3       p_360895_       Ljava/util/function/Consumer;
*/

//! Code
/*
    private void applyDamage(int p_361754_, @Nullable LivingEntity p_364853_, Consumer<Item> p_360895_) {
+       p_361754_ = AHEventHooks.onApplyItemDamage(this, p_361754_, p_364853_);
        if (p_364853_ instanceof ServerPlayer serverPlayer) {
            CriteriaTriggers.ITEM_DURABILITY_CHANGED.trigger(serverPlayer, this, p_361754_);
        }

        this.setDamageValue(p_361754_);
        if (this.isBroken()) {
            Item item = this.getItem();
            this.shrink(1);
            p_360895_.accept(item);
        }
    }
*/
