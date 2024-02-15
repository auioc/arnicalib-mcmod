function initializeCoreMod() {
    Java.type('net.neoforged.coremod.api.ASMAPI').loadFile('coremods/util/utils.js');

    return {
        'CrossbowItem#shootProjectile': {
            target: {
                type: 'METHOD',
                class: 'net.minecraft.world.item.CrossbowItem',
                methodName: 'shootProjectile',
                methodDesc: '(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;FZFFF)V'
            },
            transformer: function (methodNode) {
                var insns = methodNode.instructions;

                var injects = [
                    aLoad(1),
                    aLoad(3),
                    aLoad(11),
                    invokeStatic(
                        'org/auioc/mcmod/arnicalib/mod/coremod/AHCoreModHandler', 'preCrossbowRelease',
                        '(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/projectile/Projectile;)V'
                    )
                ];

                var at = findNodeByR(insns,
                    isInvoke(
                        'net/minecraft/world/level/Level', 'addFreshEntity',
                        '(Lnet/minecraft/world/entity/Entity;)Z'
                    ),
                    -2);
                insns.insertBefore(at, toInsnList(injects));

                // printMethodNode(methodNode);
                return methodNode;
            }
        }
    };
}


//! LocalVariableTable
/*
    Slot    Name            Signature
    11      projectile      Lnet/minecraft/world/entity/projectile/Projectile;
    11      projectile      Lnet/minecraft/world/entity/projectile/Projectile;
    0       pLevel          Lnet/minecraft/world/level/Level;
    1       pShooter        Lnet/minecraft/world/entity/LivingEntity;
    3       pCrossbowStack  Lnet/minecraft/world/item/ItemStack;
*/

//! Code
/*
    private static void shootProjectile(Level pLevel, LivingEntity pShooter, InteractionHand pHand, ItemStack pCrossbowStack, ItemStack pAmmoStack, float pSoundPitch, boolean pIsCreativeMode, float pVelocity, float pInaccuracy, float pProjectileAngle) {
        if (!pLevel.isClientSide) {
            //_...
            Projectile projectile;
            //_...
+           AHCoreModHandler.preCrossbowRelease(pShooter, pCrossbowStack, projectile);
            pLevel.addFreshEntity(projectile);
            //_...
        }
    }
*   ========== ByteCode ==========   *
    //_ ...
    L19
        LINENUMBER 253 L19
+       ALOAD 1
+       ALOAD 3
+       ALOAD 11
+       INVOKESTATIC org/auioc/mcmod/arnicalib/mod/coremod/AHCoreModHandler.preCrossbowRelease (Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/projectile/Projectile;)V
        ALOAD 0
        ALOAD 11
        INVOKEVIRTUAL net/minecraft/world/level/Level.addFreshEntity (Lnet/minecraft/world/entity/Entity;)Z
        POP
    L20
        LINENUMBER 254 L20
    //_ ...
*/
