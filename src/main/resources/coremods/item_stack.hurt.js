function initializeCoreMod() {
    var ASMAPI = Java.type('net.neoforged.coremod.api.ASMAPI');
    var Opcodes = Java.type('org.objectweb.asm.Opcodes');
    var InsnList = Java.type('org.objectweb.asm.tree.InsnList');
    var VarInsnNode = Java.type('org.objectweb.asm.tree.VarInsnNode');
    var MethodInsnNode = Java.type('org.objectweb.asm.tree.MethodInsnNode');

    return {
        'ItemStack#hurt': {
            target: {
                type: 'METHOD',
                class: 'net.minecraft.world.item.ItemStack',
                methodName: 'hurt',
                methodDesc: '(ILnet/minecraft/util/RandomSource;Lnet/minecraft/server/level/ServerPlayer;)Z'
            },
            transformer: function (methodNode) {
                var toInject = new InsnList();
                {
                    toInject.add(new VarInsnNode(Opcodes.ALOAD, 0));
                    toInject.add(new VarInsnNode(Opcodes.ILOAD, 1));
                    toInject.add(new VarInsnNode(Opcodes.ALOAD, 2));
                    toInject.add(new VarInsnNode(Opcodes.ALOAD, 3));
                    toInject.add(
                        new MethodInsnNode(
                            Opcodes.INVOKESTATIC,
                            'org/auioc/mcmod/arnicalib/mod/coremod/AHCoreModHandler',
                            'onItemHurt',
                            '(Lnet/minecraft/world/item/ItemStack;ILnet/minecraft/util/RandomSource;Lnet/minecraft/server/level/ServerPlayer;)I',
                            false
                        )
                    );
                    toInject.add(new VarInsnNode(Opcodes.ISTORE, 1));
                }

                var at = ASMAPI.findFirstInstructionAfter(methodNode, Opcodes.ILOAD, 0);
                methodNode.instructions.insertBefore(at, toInject);

                methodNode.visitMaxs(6, 7);

                // print(ASMAPI.methodNodeToString(methodNode));
                return methodNode;
            }
        }
    };
}


//! LocalVariableTable
/*
    Slot    Name        Signature
    0       this        Lnet/minecraft/world/item/ItemStack;
    1       pAmount     I
    2       pRandom     Lnet/minecraft/util/RandomSource;
    3       pUser       Lnet/minecraft/server/level/ServerPlayer;
*/

//! Code
/*
    public boolean hurt(int pAmount, RandomSource pRandom, @Nullable ServerPlayer pUser) {
        if (!this.isDamageableItem()) {
            return false;
        } else {
+           pAmount = org.auioc.mcmod.arnicalib.mod.coremod.AHCoreModHandler.onItemHurt(this, pAmount, pRandom, pUser);
            if (pAmount > 0) {
                //_ ...
            }
            //_ ...
        }
    }
*   ========== ByteCode ==========   *
    //_ ...
    L1
        LINENUMBER 406 L1
        ALOAD 0
        ILOAD 1
        ALOAD 2
        ALOAD 3
        INVOKESTATIC org/auioc/mcmod/arnicalib/mod/coremod/AHCoreModHandler.onItemHurt (Lnet/minecraft/world/item/ItemStack;ILnet/minecraft/util/RandomSource;Lnet/minecraft/server/level/ServerPlayer;)I
        ISTORE 1
        ILOAD 1
        IFLE L3
    L4
        LINENUMBER 407 L4
        GETSTATIC net/minecraft/world/item/enchantment/Enchantments.UNBREAKING : Lnet/minecraft/world/item/enchantment/Enchantment;
    //_ ...
-   MAXSTACK = 5
+   MAXSTACK = 6
*/
