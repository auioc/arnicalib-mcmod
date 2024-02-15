function initializeCoreMod() {
    Java.type('net.neoforged.coremod.api.ASMAPI').loadFile('coremods/util/utils.js');

    return {
        'ItemStack#hurt': {
            target: {
                type: 'METHOD',
                class: 'net.minecraft.world.item.ItemStack',
                methodName: 'hurt',
                methodDesc: '(ILnet/minecraft/util/RandomSource;Lnet/minecraft/server/level/ServerPlayer;)Z'
            },
            transformer: function (methodNode) {
                var insns = methodNode.instructions;

                var injects = [
                    aLoad(0),
                    iLoad(1),
                    aLoad(2),
                    aLoad(3),
                    invokeStatic(
                        'org/auioc/mcmod/arnicalib/mod/coremod/AHCoreModHandler', 'onItemHurt',
                        '(Lnet/minecraft/world/item/ItemStack;ILnet/minecraft/util/RandomSource;Lnet/minecraft/server/level/ServerPlayer;)I'
                    ),
                    iStore(1)

                ];
                var at = findNodeBy(insns, isILoad(1), 0);
                insns.insertBefore(at, toInsnList(injects));

                setMaxLocals(methodNode, 7);

                // printMethodNode(methodNode);
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
+           pAmount = AHCoreModHandler.onItemHurt(this, pAmount, pRandom, pUser);
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
+       ALOAD 0
+       ILOAD 1
+       ALOAD 2
+       ALOAD 3
+       INVOKESTATIC org/auioc/mcmod/arnicalib/mod/coremod/AHCoreModHandler.onItemHurt (Lnet/minecraft/world/item/ItemStack;ILnet/minecraft/util/RandomSource;Lnet/minecraft/server/level/ServerPlayer;)I
+       ISTORE 1
        ILOAD 1
        IFLE L3
    L4
        LINENUMBER 407 L4
        GETSTATIC net/minecraft/world/item/enchantment/Enchantments.UNBREAKING : Lnet/minecraft/world/item/enchantment/Enchantment;
    //_ ...
-   MAXSTACK = 5
+   MAXSTACK = 6
*/
