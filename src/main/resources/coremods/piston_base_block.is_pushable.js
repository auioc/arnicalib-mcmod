function initializeCoreMod() {
    var ASMAPI = Java.type('net.neoforged.coremod.api.ASMAPI');
    var Opcodes = Java.type('org.objectweb.asm.Opcodes');
    var InsnList = Java.type('org.objectweb.asm.tree.InsnList');
    var VarInsnNode = Java.type('org.objectweb.asm.tree.VarInsnNode');
    var JumpInsnNode = Java.type('org.objectweb.asm.tree.JumpInsnNode');
    var MethodInsnNode = Java.type('org.objectweb.asm.tree.MethodInsnNode');

    return {
        'PistonBaseBlock#isPushable': {
            target: {
                type: 'METHOD',
                class: 'net.minecraft.world.level.block.piston.PistonBaseBlock',
                methodName: 'isPushable',
                methodDesc: '(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;ZLnet/minecraft/core/Direction;)Z'
            },
            transformer: function (methodNode) {
                var instructions = methodNode.instructions;

                var label7 = null;
                var insertBefore = null;
                for (var i = 0; i < instructions.size(); i++) {
                    var node = instructions.get(i);
                    if (
                        node instanceof MethodInsnNode &&
                        node.owner === 'net/minecraft/world/level/block/state/BlockState' &&
                        node.name === 'is' &&
                        node.desc === '(Lnet/minecraft/world/level/block/Block;)Z'
                    ) {
                        var nextNode = instructions.get(i + 1);
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

                var toInject = new InsnList();
                {
                    toInject.add(new JumpInsnNode(Opcodes.IFNE, label7));
                    toInject.add(new VarInsnNode(Opcodes.ALOAD, 0));
                    toInject.add(
                        new MethodInsnNode(
                            Opcodes.INVOKESTATIC,
                            'org/auioc/mcmod/arnicalib/mod/coremod/AHCoreModHandler',
                            'checkPistonInteractivity',
                            '(Lnet/minecraft/world/level/block/state/BlockState;)Z',
                            false
                        )
                    );
                }

                methodNode.instructions.insertBefore(insertBefore, toInject);

                // print(ASMAPI.methodNodeToString(methodNode));
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
+           || org.auioc.mcmod.arnicalib.mod.coremod.AHCoreModHandler.checkPistonInteractivity(pState)
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
