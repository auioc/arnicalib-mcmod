function initializeCoreMod() {
    var LabelNode = Java.type('org.objectweb.asm.tree.LabelNode');

    Java.type('net.neoforged.coremod.api.ASMAPI').loadFile('coremods/util/utils.js');

    return {
        'LivingEntity#addEatEffect': {
            target: {
                type: 'METHOD',
                class: 'net.minecraft.world.entity.LivingEntity',
                methodName: 'addEatEffect',
                methodDesc: '(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;)V'
            },
            transformer: function (methodNode) {
                var insns = methodNode.instructions;

                {
                    var toRemove = findNodesByR(insns,
                        isFieldGet('net/minecraft/world/level/Level', 'isClientSide'),
                        [-1, 0, 1]);
                    toRemove.forEach(function (node) {
                        insns.remove(node);
                    });
                }

                var startLabel = new LabelNode();
                var endLabel = findNodeByR(insns, isReturn(), -3);

                addLocalVariable(methodNode,
                    'effects', 'Ljava/util/ArrayList;',
                    'Ljava/util/ArrayList<Lnet/minecraft/world/effect/MobEffectInstance;>;',
                    startLabel, endLabel, 7
                );

                {
                    var toInsns1 = [
                        aLoad(2),
                        getField('net/minecraft/world/level/Level', 'isClientSide', 'Z'),
                        ifNotEqual(endLabel),
                        startLabel,
                        newObject('java/util/ArrayList'),
                        dup(),
                        invokeSpecial('java/util/ArrayList', '<init>', '()V'),
                        aStore(7)
                    ];
                    var insertAfter = findNodeBy(insns,
                        isInvoke('net/minecraft/world/item/Item', 'isEdible', '()Z'),
                        1);
                    insns.insert(insertAfter, toInsnList(toInsns1));
                }

                {
                    var aload3 = findNodeByR(insns, isALoad(3), 0);
                    insns.set(aload3, aLoad(7));
                }

                {
                    var invokeAddEffect = findNodeByR(insns,
                        isInvoke(
                            'net/minecraft/world/entity/LivingEntity', 'addEffect',
                            '(Lnet/minecraft/world/effect/MobEffectInstance;)Z'
                        ),
                        0);
                    insns.set(
                        invokeAddEffect,
                        invokeVirtual('java/util/ArrayList', 'add', '(Ljava/lang/Object;)Z')
                    );
                }

                var loopEndLabel = new LabelNode();

                {
                    var loopEndJump = findNodeBy(insns,
                        isInvoke('java/util/Iterator', 'hasNext', '()Z'),
                        1);
                    insns.set(loopEndJump, ifEqual(loopEndLabel));
                }

                {
                    var toInsns2 = [
                        loopEndLabel,
                        frameChop(),
                        aLoad(3),
                        aLoad(1),
                        aLoad(7),
                        invokeStatic(
                            'org/auioc/mcmod/arnicalib/mod/coremod/AHCoreModHandler', 'onLivingEatAddEffect',
                            '(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Ljava/util/List;)V'
                        )
                    ];
                    insns.insertBefore(endLabel, toInsnList(toInsns2));
                }

                setMaxLocals(methodNode, 7);

                // printMethodNode(methodNode);
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
