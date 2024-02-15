var ASMAPI = Java.type('net.neoforged.coremod.api.ASMAPI');
var Opcodes = Java.type('org.objectweb.asm.Opcodes');
var InsnList = Java.type('org.objectweb.asm.tree.InsnList');
var InsnNode = Java.type('org.objectweb.asm.tree.InsnNode');
var LabelNode = Java.type('org.objectweb.asm.tree.LabelNode');
var FrameNode = Java.type('org.objectweb.asm.tree.FrameNode');
var MethodNode = Java.type('org.objectweb.asm.tree.MethodNode');
var VarInsnNode = Java.type('org.objectweb.asm.tree.VarInsnNode');
var JumpInsnNode = Java.type('org.objectweb.asm.tree.JumpInsnNode');
var TypeInsnNode = Java.type('org.objectweb.asm.tree.TypeInsnNode');
var FieldInsnNode = Java.type('org.objectweb.asm.tree.FieldInsnNode');
var MethodInsnNode = Java.type('org.objectweb.asm.tree.MethodInsnNode');
var LocalVariableNode = Java.type('org.objectweb.asm.tree.LocalVariableNode');

/**
 * @param {MethodNode} method
 */
function printMethodNode(method) {
    print(ASMAPI.methodNodeToString(method));
}

/**
 * @typedef {(any)=>boolean} NodePredicate
 */

/**
 * @param {string} name
 * @param {string} owner
 * @return NodePredicate
 */
function isFieldGet(owner, name) {
    return function (node) {
        return node instanceof FieldInsnNode && node.getOpcode() === Opcodes.GETFIELD
            && node.owner === owner && node.name === name;
    };
}

/**
 * @param {string} name
 * @param {string} owner
 * @param {string} desc
 * @return NodePredicate
 */
function isInvoke(owner, name, desc) {
    return function (node) {
        return node instanceof MethodInsnNode
            && node.owner === owner && node.name === name && node.desc === desc;
    };
}

/**
 * @param {string} desc
 * @return NodePredicate
 */
function isNewObject(desc) {
    return function (node) {
        return node instanceof TypeInsnNode && node.getOpcode() === Opcodes.NEW
            && node.desc === desc;
    };
}

/**
 * @param {number} index
 * @return NodePredicate
 */
function isALoad(index) {
    return function (node) {
        return node instanceof VarInsnNode && node.getOpcode() === Opcodes.ALOAD
            && node.var === index;
    };
}


/**
 * @param {number} index
 * @return NodePredicate
 */
function isILoad(index) {
    return function (node) {
        return node instanceof VarInsnNode && node.getOpcode() === Opcodes.ILOAD
            && node.var === index;
    };
}

/**
 * @return NodePredicate
 */
function isReturn() {
    return function (node) {
        return node instanceof InsnNode && node.getOpcode() === Opcodes.RETURN;
    };
}

/**
 * @param {object[]} nodes
 * @return InsnList
 */
function toInsnList(nodes) {
    var insnList = new InsnList();
    for (var i = 0; i < nodes.length; i++) {
        insnList.add(nodes[i]);
    }
    return insnList;
}

/**
 * @param {InsnList} insns
 * @param {NodePredicate} predicate
 * @param {number} offset
 * @return any
 */
function findNodeBy(insns, predicate, offset) {
    for (var i = 0; i < insns.size(); i++) {
        var node = insns.get(i);
        if (predicate(node)) {
            return insns.get(i + offset);
        }
    }
    throw new Error('Failed to find node');
}

/**
 * @param {InsnList} insns
 * @param {NodePredicate} predicate
 * @param {number} offset
 * @return any
 */
function findNodeByR(insns, predicate, offset) {
    for (var i = insns.size() - 1; i >= 0; i--) {
        var node = insns.get(i);
        if (predicate(node)) {
            return insns.get(i + offset);
        }
    }
    throw new Error('Failed to find node');
}

/**
 * @param {InsnList} insns
 * @param {NodePredicate} predicate
 * @param {number[]} offsets
 * @return any[]
 */
function findNodesByR(insns, predicate, offsets) {
    for (var i = insns.size() - 1; i >= 0; i--) {
        var node = insns.get(i);
        if (predicate(node)) {
            var result = [];
            for (var j = 0; j < offsets.length; j++) {
                result[j] = insns.get(i + offsets[j]);
            }
            return result;
        }
    }
    throw new Error('Failed to find nodes');
}

/**
 * @param {MethodNode} method
 * @param {string} name
 * @param {string} desc
 * @param {string|null} sig
 * @param {LabelNode} start
 * @param {LabelNode} end
 * @param {number} index
 */
function addLocalVariable(method, name, desc, sig, start, end, index) {
    method.localVariables.add(new LocalVariableNode(name, desc, sig, start, end, index));
}

/**
 * @param {MethodNode} method
 * @param {number} max
 */
function setMaxLocals(method, max) {
    method.visitMaxs(method.maxStack, max);
}

/**
 * @param {number} index
 * @return VarInsnNode
 */
function aLoad(index) {
    return new VarInsnNode(Opcodes.ALOAD, index);
}

/**
 * @param {number} index
 * @return VarInsnNode
 */
function aStore(index) {
    return new VarInsnNode(Opcodes.ASTORE, index);
}

/**
 * @param {number} index
 * @return VarInsnNode
 */
function iLoad(index) {
    return new VarInsnNode(Opcodes.ILOAD, index);
}

/**
 * @param {number} index
 * @return VarInsnNode
 */
function iStore(index) {
    return new VarInsnNode(Opcodes.ISTORE, index);
}

/**
 * @param {LabelNode} label
 * @return JumpInsnNode
 */
function ifNotEqual(label) {
    return new JumpInsnNode(Opcodes.IFNE, label);
}

/**
 * @param {LabelNode} label
 * @return JumpInsnNode
 */
function ifEqual(label) {
    return new JumpInsnNode(Opcodes.IFEQ, label);
}

/**
 * @param {string} owner
 * @param {string} name
 * @param {string} desc
 * @return MethodInsnNode
 */
function invokeSpecial(owner, name, desc) {
    return new MethodInsnNode(Opcodes.INVOKESPECIAL, owner, name, desc, false);
}

/**
 * @param {string} owner
 * @param {string} name
 * @param {string} desc
 * @return MethodInsnNode
 */
function invokeStatic(owner, name, desc) {
    return new MethodInsnNode(Opcodes.INVOKESTATIC, owner, name, desc, false);
}

/**
 * @param {string} owner
 * @param {string} name
 * @param {string} desc
 * @return MethodInsnNode
 */
function invokeVirtual(owner, name, desc) {
    return new MethodInsnNode(Opcodes.INVOKEVIRTUAL, owner, name, desc, false);
}

/**
 * @param {string} owner
 * @param {string} name
 * @param {string} desc
 * @return FieldInsnNode
 */
function getField(owner, name, desc) {
    return new FieldInsnNode(Opcodes.GETFIELD, owner, name, desc);
}

/**
 * @param {string} desc
 * @return TypeInsnNode
 */
function newObject(desc) {
    return new TypeInsnNode(Opcodes.NEW, desc);
}

/**
 * @return InsnNode
 */
function dup() {
    return new InsnNode(Opcodes.DUP);
}

/**
 * @return FrameNode
 */
function frameChop() {
    return new FrameNode(Opcodes.F_CHOP, 1, null, 0, null);
}
