function initializeCoreMod() {
    var ASMAPI = Java.type('net.neoforged.coremod.api.ASMAPI');
    var Opcodes = Java.type('org.objectweb.asm.Opcodes');
    var InsnList = Java.type('org.objectweb.asm.tree.InsnList');
    var VarInsnNode = Java.type('org.objectweb.asm.tree.VarInsnNode');
    var MethodInsnNode = Java.type('org.objectweb.asm.tree.MethodInsnNode');
    var LabelNode = Java.type('org.objectweb.asm.tree.LabelNode');
    var LocalVariableNode = Java.type('org.objectweb.asm.tree.LocalVariableNode');

    return {
        'FishingRodItem#use': {
            target: {
                type: 'METHOD',
                class: 'net.minecraft.world.item.FishingRodItem',
                methodName: 'use',
                methodDesc: '(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResultHolder;'
            },
            transformer: function (methodNode) {
                var toInject = new InsnList();
                {
                    var fn_start = new LabelNode();
                    var fn_end = new LabelNode();

                    methodNode.localVariables.add(
                        new LocalVariableNode(
                            'event',
                            'Lorg/auioc/mcmod/arnicalib/game/event/server/FishingRodCastEvent;',
                            null,
                            fn_start,
                            fn_end,
                            7
                        )
                    );

                    toInject.add(fn_start);
                    toInject.add(new VarInsnNode(Opcodes.ALOAD, 2));
                    toInject.add(new VarInsnNode(Opcodes.ALOAD, 4));
                    toInject.add(new VarInsnNode(Opcodes.ILOAD, 5));
                    toInject.add(new VarInsnNode(Opcodes.ILOAD, 6));
                    toInject.add(
                        new MethodInsnNode(
                            Opcodes.INVOKESTATIC,
                            'org/auioc/mcmod/arnicalib/mod/coremod/AHCoreModHandler',
                            'preFishingRodCast',
                            '(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;II)Lorg/auioc/mcmod/arnicalib/game/event/server/FishingRodCastEvent;',
                            false
                        )
                    );
                    toInject.add(new VarInsnNode(Opcodes.ASTORE, 7));
                    toInject.add(new VarInsnNode(Opcodes.ALOAD, 7));
                    toInject.add(
                        new MethodInsnNode(
                            Opcodes.INVOKEVIRTUAL,
                            'org/auioc/mcmod/arnicalib/game/event/server/FishingRodCastEvent',
                            'getSpeedBonus',
                            '()I',
                            false
                        )
                    );
                    toInject.add(new VarInsnNode(Opcodes.ISTORE, 5));
                    toInject.add(new VarInsnNode(Opcodes.ALOAD, 7));
                    toInject.add(
                        new MethodInsnNode(
                            Opcodes.INVOKEVIRTUAL,
                            'org/auioc/mcmod/arnicalib/game/event/server/FishingRodCastEvent',
                            'getLuckBonus',
                            '()I',
                            false
                        )
                    );
                    toInject.add(new VarInsnNode(Opcodes.ISTORE, 6));
                    toInject.add(fn_end);
                }

                var at = methodNode.instructions.get(
                    methodNode.instructions.indexOf(
                        ASMAPI.findFirstInstructionBefore(
                            methodNode,
                            Opcodes.NEW,
                            0
                        )
                    ) - 1
                );
                methodNode.instructions.insertBefore(at, toInject);

                methodNode.visitMaxs(14, 8);

                // print(ASMAPI.methodNodeToString(methodNode));
                return methodNode;
            }
        }
    };
}

//! LocalVariableTable
/*
    Slot    Name        Signature
    5       k           I
    6       j           I
    0       this        Lnet/minecraft/world/item/FishingRodItem;
    2       pPlayer     Lnet/minecraft/world/entity/player/Player;
    4       itemstack   Lnet/minecraft/world/item/ItemStack;
+   7       event       Lorg/auioc/mcmod/hulsealib/game/event/server/PreFishingRodCastEvent;
*/

//! Code
/*
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (pPlayer.fishing != null) {
            //_ ...
        } else {
            //_ ...
            if (!pLevel.isClientSide) {
                int k = EnchantmentHelper.getFishingSpeedBonus(itemstack);
                int j = EnchantmentHelper.getFishingLuckBonus(itemstack);
+               var event = org.auioc.mcmod.arnicalib.mod.coremod.AHCoreModHandler.preFishingRodCast(pPlayer, itemstack, k, j);
+               k = event.getSpeedBonus();
+               j = event.getLuckBonus();
                pLevel.addFreshEntity(new FishingHook(pPlayer, pLevel, j, k));
            }
            //_ ...
       }
       //_ ...
    }
*   ========== ByteCode ==========   *
    //_ ...
    L24
        LINENUMBER 58 L24
        ALOAD 4
        INVOKESTATIC net/minecraft/world/item/enchantment/EnchantmentHelper.getFishingSpeedBonus (Lnet/minecraft/world/item/ItemStack;)I
        ISTORE 5
    L25
        LINENUMBER 59 L25
        ALOAD 4
        INVOKESTATIC net/minecraft/world/item/enchantment/EnchantmentHelper.getFishingLuckBonus (Lnet/minecraft/world/item/ItemStack;)I
        ISTORE 6
    L26
        LINENUMBER 60 L26
+   L27
+       ALOAD 2
+       ALOAD 4
+       ILOAD 5
+       ILOAD 6
+       INVOKESTATIC org/auioc/mcmod/arnicalib/mod/coremod/AHCoreModHandler.preFishingRodCast (Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;II)Lorg/auioc/mcmod/arnicalib/game/event/server/FishingRodCastEvent;
+       ASTORE 7
+       ALOAD 7
+       INVOKEVIRTUAL org/auioc/mcmod/arnicalib/game/event/server/FishingRodCastEvent.getSpeedBonus ()I
+       ISTORE 5
+       ALOAD 7
+       INVOKEVIRTUAL org/auioc/mcmod/arnicalib/game/event/server/FishingRodCastEvent.getLuckBonus ()I
+       ISTORE 6
+   L28
    //_ ...
-   MAXLOCALS = 7
+   MAXLOCALS = 8
*/
