function initializeCoreMod() {
    var LabelNode = Java.type('org.objectweb.asm.tree.LabelNode');

    Java.type('net.neoforged.coremod.api.ASMAPI').loadFile('coremods/util/utils.js');

    return {
        'FishingRodItem#use': {
            target: {
                type: 'METHOD',
                class: 'net.minecraft.world.item.FishingRodItem',
                methodName: 'use',
                methodDesc: '(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResultHolder;'
            },
            transformer: function (methodNode) {
                var insns = methodNode.instructions;

                var startLabel = new LabelNode();
                var endLabel = new LabelNode();

                addLocalVariable(methodNode,
                    'event', 'Lorg/auioc/mcmod/arnicalib/game/event/server/FishingRodCastEvent;', null,
                    startLabel, endLabel, 7
                );

                var injects = [
                    startLabel,
                    aLoad(2),
                    aLoad(4),
                    iLoad(5),
                    iLoad(6),
                    invokeStatic(
                        'org/auioc/mcmod/arnicalib/mod/coremod/AHCoreModHandler', 'preFishingRodCast',
                        '(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;II)Lorg/auioc/mcmod/arnicalib/game/event/server/FishingRodCastEvent;'
                    ),
                    aStore(7),
                    aLoad(7),
                    invokeVirtual(
                        'org/auioc/mcmod/arnicalib/game/event/server/FishingRodCastEvent', 'getSpeedBonus',
                        '()I'
                    ),
                    iStore(5),
                    aLoad(7),
                    invokeVirtual(
                        'org/auioc/mcmod/arnicalib/game/event/server/FishingRodCastEvent', 'getLuckBonus',
                        '()I'
                    ),
                    iStore(6),
                    endLabel
                ];

                var at = findNodeByR(insns, isNewObject('net/minecraft/world/entity/projectile/FishingHook'), -1);
                insns.insertBefore(at, toInsnList(injects));

                setMaxLocals(methodNode, 8);

                // printMethodNode(methodNode);
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
+               var event = AHCoreModHandler.preFishingRodCast(pPlayer, itemstack, k, j);
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
