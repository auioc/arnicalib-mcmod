function initializeCoreMod() {
    var ASMAPI = Java.type('net.neoforged.coremod.api.ASMAPI');
    var Opcodes = Java.type('org.objectweb.asm.Opcodes');
    var InsnList = Java.type('org.objectweb.asm.tree.InsnList');
    var InsnNode = Java.type('org.objectweb.asm.tree.InsnNode');
    var LabelNode = Java.type('org.objectweb.asm.tree.LabelNode');
    var FrameNode = Java.type('org.objectweb.asm.tree.FrameNode');
    var VarInsnNode = Java.type('org.objectweb.asm.tree.VarInsnNode');
    var JumpInsnNode = Java.type('org.objectweb.asm.tree.JumpInsnNode');
    var TypeInsnNode = Java.type('org.objectweb.asm.tree.TypeInsnNode');
    var FieldInsnNode = Java.type('org.objectweb.asm.tree.FieldInsnNode');
    var MethodInsnNode = Java.type('org.objectweb.asm.tree.MethodInsnNode');
    var LocalVariableNode = Java.type('org.objectweb.asm.tree.LocalVariableNode');

    function findEndLabelNode(instructions) {
        for (var i = instructions.size() - 1; i > -1; i--) {
            var node = instructions.get(i);
            if (
                node instanceof InsnNode
                && node.getOpcode() === Opcodes.RETURN
            ) {
                return instructions.get(i - 3);
            }
        }
    }

    function findInsertAfterNode(instructions) {
        for (var i = 0; i < instructions.size(); i++) {
            var node = instructions.get(i);
            if (
                node instanceof MethodInsnNode
                && node.owner === 'net/minecraft/world/item/Item'
                && node.name === 'isEdible'
            ) {
                return instructions.get(i + 1);
            }
        }
    }

    function findInsertisClient(instructions) {
        for (var i = instructions.size() - 1; i > -1; i--) {
            var node = instructions.get(i);
            if (
                node instanceof FieldInsnNode
                && node.getOpcode() === Opcodes.GETFIELD
                && node.owner === 'net/minecraft/world/level/Level'
                && node.name === 'isClientSide'
            ) {
                return [instructions.get(i - 1), node, instructions.get(i + 1)];
            }
        }
    }

    function findVarAload3(instructions) {
        for (var i = instructions.size() - 1; i > -1; i--) {
            var node = instructions.get(i);
            if (
                node instanceof VarInsnNode
                && node.getOpcode() === Opcodes.ALOAD
                && node.var === 3
            ) {
                return node;
            }
        }
    }

    function findLoopEndJump(instructions) {
        for (var i = 0; i < instructions.size(); i++) {
            var node = instructions.get(i);
            if (
                node instanceof MethodInsnNode
                && node.owner === 'java/util/Iterator'
                && node.name === 'hasNext'
            ) {
                return instructions.get(i + 1);
            }
        }
    }

    function findInvokeAddEffect(instructions) {
        for (var i = instructions.size() - 1; i > -1; i--) {
            var node = instructions.get(i);
            if (
                node instanceof MethodInsnNode
                && node.owner === 'net/minecraft/world/entity/LivingEntity'
                && node.name === 'addEffect'
            ) {
                return node;
            }
        }
    }

    return {
        'LivingEntity#addEatEffect': {
            target: {
                type: 'METHOD',
                class: 'net.minecraft.world.entity.LivingEntity',
                methodName: 'addEatEffect',
                methodDesc: '(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;)V'
            },
            transformer: function (methodNode) {
                var instructions = methodNode.instructions;


                var toRemove = findInsertisClient(instructions);
                toRemove.forEach(function (node) {
                    instructions.remove(node);
                });

                var startLabel = new LabelNode();
                var endLabel = findEndLabelNode(instructions);

                methodNode.localVariables.add(
                    new LocalVariableNode(
                        'effects',
                        'Ljava/util/ArrayList;',
                        'Ljava/util/ArrayList<Lnet/minecraft/world/effect/MobEffectInstance;>;',
                        startLabel,
                        endLabel,
                        7
                    )
                );

                var insnList1 = new InsnList();
                {
                    insnList1.add(new VarInsnNode(Opcodes.ALOAD, 2));
                    insnList1.add(
                        new FieldInsnNode(
                            Opcodes.GETFIELD,
                            'net/minecraft/world/level/Level',
                            'isClientSide',
                            'Z'
                        )
                    );
                    insnList1.add(new JumpInsnNode(Opcodes.IFNE, endLabel));
                    insnList1.add(startLabel);
                    insnList1.add(
                        new TypeInsnNode(
                            Opcodes.NEW,
                            'java/util/ArrayList'
                        )
                    );
                    insnList1.add(new InsnNode(Opcodes.DUP));
                    insnList1.add(new MethodInsnNode(
                            Opcodes.INVOKESPECIAL,
                            'java/util/ArrayList',
                            '<init>',
                            '()V',
                            false
                        )
                    );
                    insnList1.add(new VarInsnNode(Opcodes.ASTORE, 7));
                }
                var insertAfter = findInsertAfterNode(instructions);
                methodNode.instructions.insert(insertAfter, insnList1);

                var aload3 = findVarAload3(instructions);
                instructions.set(
                    aload3,
                    new VarInsnNode(Opcodes.ALOAD, 7)
                );

                var invokeAddEffect = findInvokeAddEffect(instructions);
                instructions.set(
                    invokeAddEffect,
                    new MethodInsnNode(
                        Opcodes.INVOKEVIRTUAL,
                        'java/util/ArrayList',
                        'add',
                        '(Ljava/lang/Object;)Z',
                        false
                    )
                );

                var loopEndLabel = new LabelNode();

                var loopEndJump = findLoopEndJump(instructions);
                instructions.set(
                    loopEndJump,
                    new JumpInsnNode(Opcodes.IFEQ, loopEndLabel)
                );

                var insnList2 = new InsnList();
                {
                    insnList2.add(loopEndLabel);
                    insnList2.add(new FrameNode(Opcodes.F_CHOP, 1, null, 0, null));
                    insnList2.add(new VarInsnNode(Opcodes.ALOAD, 3));
                    insnList2.add(new VarInsnNode(Opcodes.ALOAD, 1));
                    insnList2.add(new VarInsnNode(Opcodes.ALOAD, 7));
                    insnList2.add(new MethodInsnNode(
                            Opcodes.INVOKESTATIC,
                            'org/auioc/mcmod/arnicalib/mod/coremod/AHCoreModHandler',
                            'onLivingEatAddEffect',
                            '(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Ljava/util/List;)V',
                            false
                        )
                    );
                }
                methodNode.instructions.insertBefore(endLabel, insnList2);

                methodNode.visitMaxs(4, 8);

                print(ASMAPI.methodNodeToString(methodNode));
                return methodNode;
            }
        }
    };
}


//! LocalVariableTable
/*
    Slot    Name            Signature
    1       pFood           Lnet/minecraft/world/item/ItemStack;
    2       pLevel          Lnet/minecraft/world/level/Level;
    3       pLivingEntity   Lnet/minecraft/world/entity/LivingEntity;
+   7       effects         Ljava/util/ArrayList<Lnet/minecraft/world/effect/MobEffectInstance;>;
*/

//! Code
/*
    private void addEatEffect(ItemStack pFood, Level pLevel, LivingEntity pLivingEntity) {
        Item item = pFood.getItem();
-       if (item.isEdible()) {
+       if (item.isEdible() && !pLevel.isClientSide) {
+           ArrayList effects = new ArrayList();
            for(Pair<MobEffectInstance, Float> pair : pFood.getFoodProperties(this).getEffects()) {
-               if (!pLevel.isClientSide && pair.getFirst() != null && pLevel.random.nextFloat() < pair.getSecond()) {
+               if (pair.getFirst() != null && pLevel.random.nextFloat() < (Float)pair.getSecond()) {
-                   pLivingEntity.addEffect(new MobEffectInstance(pair.getFirst()));
+                   effects.add(new MobEffectInstance(pair.getFirst()));
                }
            }
+           AHCoreModHandler.onLivingEatAddEffect(pLivingEntity, pFood, effects);
        }
    }
*   ========== ByteCode ==========   *
    L0
        LINENUMBER 3608 L0
        ALOAD 1
        INVOKEVIRTUAL net/minecraft/world/item/ItemStack.getItem ()Lnet/minecraft/world/item/Item;
        ASTORE 4
    L1
        LINENUMBER 3609 L1
        ALOAD 4
        INVOKEVIRTUAL net/minecraft/world/item/Item.isEdible ()Z
        IFEQ L2
+       ALOAD 2
+        GETFIELD net/minecraft/world/level/Level.isClientSide : Z
+        IFNE L2
    L3
-       LINENUMBER 3610 L3
+       NEW java/util/ArrayList
+       DUP
+       INVOKESPECIAL java/util/ArrayList.<init> ()V
+       ASTORE 7
+   L4
+       LINENUMBER 3610 L4
        ALOAD 1
        ALOAD 0
        INVOKEVIRTUAL net/minecraft/world/item/ItemStack.getFoodProperties (Lnet/minecraft/world/entity/LivingEntity;)Lnet/minecraft/world/food/FoodProperties;
        INVOKEVIRTUAL net/minecraft/world/food/FoodProperties.getEffects ()Ljava/util/List;
        INVOKEINTERFACE java/util/List.iterator ()Ljava/util/Iterator; (itf)
        ASTORE 5
-   L4
+   L5
    FRAME APPEND [net/minecraft/world/item/Item java/util/Iterator]
        ALOAD 5
        INVOKEINTERFACE java/util/Iterator.hasNext ()Z (itf)
-       IFEQ L2
+       IFEQ L6
        ALOAD 5
        INVOKEINTERFACE java/util/Iterator.next ()Ljava/lang/Object; (itf)
        CHECKCAST com/mojang/datafixers/util/Pair
        ASTORE 6
-   L5
-       LINENUMBER 3611 L5
-       ALOAD 2
-       GETFIELD net/minecraft/world/level/Level.isClientSide : Z
-       IFNE L6
+   L7
+       LINENUMBER 3611 L7
        ALOAD 6
        INVOKEVIRTUAL com/mojang/datafixers/util/Pair.getFirst ()Ljava/lang/Object;
-       IFNULL L6
+       IFNULL L8
        ALOAD 2
        GETFIELD net/minecraft/world/level/Level.random : Lnet/minecraft/util/RandomSource;
        INVOKEINTERFACE net/minecraft/util/RandomSource.nextFloat ()F (itf)
        ALOAD 6
        INVOKEVIRTUAL com/mojang/datafixers/util/Pair.getSecond ()Ljava/lang/Object;
        CHECKCAST java/lang/Float
        INVOKEVIRTUAL java/lang/Float.floatValue ()F
        FCMPG
-       IFGE L6
-   L7
-       LINENUMBER 3612 L7
-       ALOAD 3
+       IFGE L8
+   L9
+       LINENUMBER 3612 L9
+       ALOAD 7
         NEW net/minecraft/world/effect/MobEffectInstance
        DUP
        ALOAD 6
        INVOKEVIRTUAL com/mojang/datafixers/util/Pair.getFirst ()Ljava/lang/Object;
        CHECKCAST net/minecraft/world/effect/MobEffectInstance
        INVOKESPECIAL net/minecraft/world/effect/MobEffectInstance.<init> (Lnet/minecraft/world/effect/MobEffectInstance;)V
-       INVOKEVIRTUAL net/minecraft/world/entity/LivingEntity.addEffect (Lnet/minecraft/world/effect/MobEffectInstance;)Z
+       INVOKEVIRTUAL java/util/ArrayList.add (Ljava/lang/Object;)Z
        POP
-   L6
-       LINENUMBER 3614 L6
+   L8
+       LINENUMBER 3614 L8
    FRAME SAME
-       GOTO L4
+       GOTO L5
+   L6
+   FRAME CHOP 1
+       ALOAD 3
+       ALOAD 1
+       ALOAD 7
+       INVOKESTATIC org/auioc/mcmod/arnicalib/mod/coremod/AHCoreModHandler.onLivingEatAddEffect (Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Ljava/util/List;)V
    L2
        LINENUMBER 3616 L2
    FRAME CHOP 1
        RETURN
-   L8
+   L10
    //_ ...
+   LOCALVARIABLE effects Ljava/util/ArrayList; L3 L2 7
+   // signature Ljava/util/ArrayList<Lnet/minecraft/world/effect/MobEffectInstance;>;
+   // declaration: effects extends java.util.ArrayList<net.minecraft.world.effect.MobEffectInstance>
    MAXSTACK = 4
-   MAXLOCALS = 7
+   MAXLOCALS = 8
*/
