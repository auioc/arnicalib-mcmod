function initializeCoreMod() {
    ASMAPI = Java.type('net.minecraftforge.coremod.api.ASMAPI');

    Opcodes = Java.type('org.objectweb.asm.Opcodes');

    InsnList = Java.type('org.objectweb.asm.tree.InsnList');

    VarInsnNode = Java.type('org.objectweb.asm.tree.VarInsnNode');
    MethodInsnNode = Java.type('org.objectweb.asm.tree.MethodInsnNode');
    LabelNode = Java.type('org.objectweb.asm.tree.LabelNode');
    LocalVariableNode = Java.type('org.objectweb.asm.tree.LocalVariableNode');

    return {
        'FishingRodItem#use': {
            target: {
                type: 'METHOD',
                class: 'net.minecraft.world.item.FishingRodItem',
                methodName: ASMAPI.mapMethod('m_7203_'),
                methodDesc:
                    '(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResultHolder;',
            },
            transformer: function (methodNode) {
                var toInject = new InsnList();
                {
                    var fn_start = new LabelNode();
                    var fn_end = new LabelNode();
                    methodNode.localVariables.add(
                        new LocalVariableNode(
                            'preEvent',
                            'Lorg/auioc/mcmod/arnicalib/server/event/impl/FishingRodCastEvent$Pre;',
                            null,
                            fn_start,
                            fn_end,
                            7
                        )
                    );
                    toInject.add(fn_start);
                    toInject.add(new VarInsnNode(Opcodes.ALOAD, 2));
                    toInject.add(new VarInsnNode(Opcodes.ALOAD, 1));
                    toInject.add(new VarInsnNode(Opcodes.ALOAD, 4));
                    toInject.add(new VarInsnNode(Opcodes.ILOAD, 5));
                    toInject.add(new VarInsnNode(Opcodes.ILOAD, 6));
                    toInject.add(
                        new MethodInsnNode(
                            Opcodes.INVOKESTATIC,
                            'org/auioc/mcmod/arnicalib/server/event/AHServerEventFactory',
                            'preFishingRodCast',
                            '(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;II)Lorg/auioc/mcmod/arnicalib/server/event/impl/FishingRodCastEvent$Pre;',
                            false
                        )
                    );
                    toInject.add(new VarInsnNode(Opcodes.ASTORE, 7));
                    toInject.add(new VarInsnNode(Opcodes.ALOAD, 7));
                    toInject.add(
                        new MethodInsnNode(
                            Opcodes.INVOKEVIRTUAL,
                            'org/auioc/mcmod/arnicalib/server/event/impl/FishingRodCastEvent$Pre',
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
                            'org/auioc/mcmod/arnicalib/server/event/impl/FishingRodCastEvent$Pre',
                            'getLuckBonus',
                            '()I',
                            false
                        )
                    );
                    toInject.add(new VarInsnNode(Opcodes.ISTORE, 6));
                    toInject.add(fn_end);
                }

                var at = ASMAPI.findFirstInstructionBefore(
                    methodNode,
                    Opcodes.NEW,
                    0
                );
                methodNode.instructions.insertBefore(
                    methodNode.instructions.get(
                        methodNode.instructions.indexOf(at) - 1
                    ),
                    toInject
                );

                methodNode.visitMaxs(14, 8);

                // print(ASMAPI.methodNodeToString(methodNode));
                return methodNode;
            },
        },
    };
}

//! SRG <-> MCP
/*
    m_7203_    use
*/

//! LocalVariableTable
/*
    Slot    Name         Signature
    5       i            I
    5       j            I
    6       k            I
    0       this         Lnet/minecraft/world/item/FishingRodItem;
    1       p_41290_     Lnet/minecraft/world/level/Level;
    2       p_41291_     Lnet/minecraft/world/entity/player/Player;
    3       p_41292_     Lnet/minecraft/world/InteractionHand;
    4       itemstack    Lnet/minecraft/world/item/ItemStack;
+   7       preEvent     Lorg/auioc/mcmod/arnicalib/server/event/impl/FishingRodCastEvent$Pre;
*/

//! Code
/*
    public InteractionResultHolder<ItemStack> use(Level p_41290_, Player p_41291_, InteractionHand p_41292_) {
        ItemStack itemstack = p_41291_.getItemInHand(p_41292_);
        if (p_41291_.fishing != null) {
            //_ ...
        } else {
            //_ ...
            if (!p_41290_.isClientSide) {
                int k = EnchantmentHelper.getFishingSpeedBonus(itemstack);
                int j = EnchantmentHelper.getFishingLuckBonus(itemstack);
+               var preEvent = org.auioc.mcmod.arnicalib.server.event.AHServerEventFactory.preFishingRodCast(p_41291_, p_41290_, itemstack, k, j);
+               k = preEvent.getSpeedBonus();
+               j = preEvent.getLuckBonus();
                p_41290_.addFreshEntity(new FishingHook(p_41291_, p_41290_, j, k));
            }
            //_ ...
       }
       return InteractionResultHolder.sidedSuccess(itemstack, p_41290_.isClientSide());
    }
*   ========== ByteCode ==========   *
    //_ ...
    L9
        LINENUMBER 33 L9
        ALOAD 1
        GETFIELD net/minecraft/world/level/Level.isClientSide : Z
        IFNE L10
    L11
        LINENUMBER 34 L11
        ALOAD 4
        INVOKESTATIC net/minecraft/world/item/enchantment/EnchantmentHelper.getFishingSpeedBonus (Lnet/minecraft/world/item/ItemStack;)I
        ISTORE 5
    L12
        LINENUMBER 35 L12
        ALOAD 4
        INVOKESTATIC net/minecraft/world/item/enchantment/EnchantmentHelper.getFishingLuckBonus (Lnet/minecraft/world/item/ItemStack;)I
        ISTORE 6
    L13
        LINENUMBER 36 L13
+   L14
+       ALOAD 2
+       ALOAD 1
+       ALOAD 4
+       ILOAD 5
+       ILOAD 6
+       INVOKESTATIC org/auioc/mcmod/arnicalib/server/event/AHServerEventFactory.preFishingRodCast (Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;II)Lorg/auioc/mcmod/arnicalib/server/event/impl/FishingRodCastEvent$Pre;
+       ASTORE 7
+       ALOAD 7
+       INVOKEVIRTUAL org/auioc/mcmod/arnicalib/server/event/impl/FishingRodCastEvent$Pre.getSpeedBonus ()I
+       ISTORE 5
+       ALOAD 7
+       INVOKEVIRTUAL org/auioc/mcmod/arnicalib/server/event/impl/FishingRodCastEvent$Pre.getLuckBonus ()I
+       ISTORE 6
+   L15
        ALOAD 1
        NEW net/minecraft/world/entity/projectile/FishingHook
        DUP
        ALOAD 2
        ALOAD 1
        ILOAD 6
        ILOAD 5
        INVOKESPECIAL net/minecraft/world/entity/projectile/FishingHook.<init> (Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/Level;II)V
        INVOKEVIRTUAL net/minecraft/world/level/Level.addFreshEntity (Lnet/minecraft/world/entity/Entity;)Z
        POP
    //_ ...
-    MAXLOCALS = 7
+    MAXLOCALS = 8
*/
