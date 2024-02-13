function initializeCoreMod() {
    var ASMAPI = Java.type('net.neoforged.coremod.api.ASMAPI');
    var Opcodes = Java.type('org.objectweb.asm.Opcodes');
    var InsnList = Java.type('org.objectweb.asm.tree.InsnList');
    var VarInsnNode = Java.type('org.objectweb.asm.tree.VarInsnNode');
    var MethodInsnNode = Java.type('org.objectweb.asm.tree.MethodInsnNode');

    return {
        'CrossbowItem#shootProjectile': {
            target: {
                type: 'METHOD',
                class: 'net.minecraft.world.item.CrossbowItem',
                methodName: 'shootProjectile',
                methodDesc: '(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;FZFFF)V',
            },
            transformer: function (methodNode) {
                var toInject = new InsnList();
                {
                    toInject.add(new VarInsnNode(Opcodes.ALOAD, 1));
                    toInject.add(new VarInsnNode(Opcodes.ALOAD, 3));
                    toInject.add(new VarInsnNode(Opcodes.ALOAD, 11));
                    toInject.add(
                        new MethodInsnNode(
                            Opcodes.INVOKESTATIC,
                            'org/auioc/mcmod/arnicalib/mod/server/event/AHServerEventFactory',
                            'preProjectileWeaponRelease',
                            '(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/projectile/Projectile;)V',
                            false
                        )
                    );
                }

                var at = methodNode.instructions.get(
                    methodNode.instructions.indexOf(
                        ASMAPI.findFirstMethodCall(
                            methodNode,
                            ASMAPI.MethodType.VIRTUAL,
                            'net/minecraft/world/level/Level',
                            'addFreshEntity',
                            '(Lnet/minecraft/world/entity/Entity;)Z'
                        )
                    ) - 2
                );
                methodNode.instructions.insertBefore(at, toInject);

                // print(ASMAPI.methodNodeToString(methodNode));
                return methodNode;
            },
        },
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
+           org.auioc.mcmod.arnicalib.mod.server.event.AHServerEventFactory.preProjectileWeaponRelease(pShooter, pCrossbowStack, projectile);
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
+       INVOKESTATIC org/auioc/mcmod/arnicalib/mod/server/event/AHServerEventFactory.preCrossbowRelease (Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/projectile/Projectile;)V
        ALOAD 0
        ALOAD 11
        INVOKEVIRTUAL net/minecraft/world/level/Level.addFreshEntity (Lnet/minecraft/world/entity/Entity;)Z
        POP
    L20
        LINENUMBER 254 L20
    //_ ...
*/
