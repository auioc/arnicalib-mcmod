function initializeCoreMod() {
    var Opcodes = Java.type('org.objectweb.asm.Opcodes');

    Java.type('net.neoforged.coremod.api.ASMAPI').loadFile('coremods/util/utils.js');

    return {
        'PistonBaseBlock#isPushable': {
            target: {
                type: 'METHOD',
                class: 'net.minecraft.world.level.block.piston.PistonBaseBlock',
                methodName: 'isPushable',
                methodDesc: '(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;ZLnet/minecraft/core/Direction;)Z'
            },
            transformer: function (methodNode) {
                var insns = methodNode.instructions;

                var label7 = null;
                var insertBefore = null;
                for (var i = 0; i < insns.size(); i++) {
                    var node = insns.get(i);
                    if (
                        isInvoke(
                            'net/minecraft/world/level/block/state/BlockState', 'is',
                            '(Lnet/minecraft/world/level/block/Block;)Z'
                        )(node)
                    ) {
                        var nextNode = insns.get(i + 1);
                        var nextOpcode = nextNode.getOpcode();
                        if (nextOpcode === Opcodes.IFNE) {
                            label7 = nextNode.label;
                        }
                        if (nextOpcode === Opcodes.IFEQ) {
                            insertBefore = nextNode;
                        }
                        if (label7 !== null && insertBefore !== null) {
                            break;
                        }
                    }
                }

                var injects = [
                    ifNotEqual(label7),
                    aLoad(0),
                    invokeStatic(
                        'org/auioc/mcmod/arnicalib/mod/coremod/AHCoreModHandler', 'checkPistonInteractivity',
                        '(Lnet/minecraft/world/level/block/state/BlockState;)Z'
                    )
                ];
                insns.insertBefore(insertBefore, toInsnList(injects));

                // printMethodNode(methodNode);
                return methodNode;
            }
        }
    };
}


//! LocalVariableTable
/*
    Slot    Name        Signature
    0       pState      Lnet/minecraft/world/level/block/state/BlockState;
*/

//! Code
/*
    public static boolean isPushable(BlockState pState, Level pLevel, BlockPos pPos, Direction pMovementDirection, boolean pAllowDestroy, Direction pPistonFacing) {
        //_ ...
        } else if (pState.is(Blocks.OBSIDIAN)
            || pState.is(Blocks.CRYING_OBSIDIAN)
            || pState.is(Blocks.RESPAWN_ANCHOR)
            || pState.is(Blocks.REINFORCED_DEEPSLATE)
+           || AHCoreModHandler.checkPistonInteractivity(pState)
            ) {
            return false;
        } else if (pMovementDirection == Direction.DOWN && pPos.getY() == pLevel.getMinBuildHeight()) {
        //_ ...
    }
*   ========== ByteCode ==========   *
    //_ ...
    L5
        LINENUMBER 275 L5
        ALOAD 0
        GETSTATIC net/minecraft/world/level/block/Blocks.OBSIDIAN : Lnet/minecraft/world/level/block/Block;
        INVOKEVIRTUAL net/minecraft/world/level/block/state/BlockState.is (Lnet/minecraft/world/level/block/Block;)Z
        IFNE L7
        ALOAD 0
        GETSTATIC net/minecraft/world/level/block/Blocks.CRYING_OBSIDIAN : Lnet/minecraft/world/level/block/Block;
    L8
        LINENUMBER 276 L8
        INVOKEVIRTUAL net/minecraft/world/level/block/state/BlockState.is (Lnet/minecraft/world/level/block/Block;)Z
        IFNE L7
        ALOAD 0
        GETSTATIC net/minecraft/world/level/block/Blocks.RESPAWN_ANCHOR : Lnet/minecraft/world/level/block/Block;
    L9
        LINENUMBER 277 L9
        INVOKEVIRTUAL net/minecraft/world/level/block/state/BlockState.is (Lnet/minecraft/world/level/block/Block;)Z
        IFNE L7
        ALOAD 0
        GETSTATIC net/minecraft/world/level/block/Blocks.REINFORCED_DEEPSLATE : Lnet/minecraft/world/level/block/Block;
    L10
        LINENUMBER 278 L10
        INVOKEVIRTUAL net/minecraft/world/level/block/state/BlockState.is (Lnet/minecraft/world/level/block/Block;)Z
+       IFNE L7
+       ALOAD 0
+       INVOKESTATIC org/auioc/mcmod/arnicalib/mod/coremod/AHCoreModHandler.checkPistonInteractivity (Lnet/minecraft/world/level/block/state/BlockState;)Z
        IFEQ L11
    L7
        LINENUMBER 279 L7
        ICONST_0
        IRETURN
    L11
        LINENUMBER 280 L11
    //_ ...
*/
