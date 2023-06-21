package org.auioc.mcmod.arnicalib.game.nbt;

import java.util.function.Supplier;
import org.joml.Vector3f;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.DoubleTag;
import net.minecraft.nbt.FloatTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class NbtUtils {

    public static ListTag writeDoubleArray(double... values) {
        var listTag = new ListTag();
        for (int i = 0; i < values.length; i++) {
            listTag.addTag(i, DoubleTag.valueOf(values[i]));
        }
        return listTag;
    }

    public static double[] readDoubleArray(ListTag nbt) {
        int size = nbt.size();
        var result = new double[size];
        for (int i = 0; i < size; i++) result[i] = nbt.getDouble(i);
        return result;
    }

    public static ListTag getDoubleListTag(CompoundTag nbt, String key) {
        return nbt.getList(key, Tag.TAG_DOUBLE);
    }

    public static double[] getDoubleArray(CompoundTag nbt, String key) {
        return readDoubleArray(getDoubleListTag(nbt, key));
    }

    // ====================================================================== //

    public static ListTag writeFloatArray(float... values) {
        var listTag = new ListTag();
        for (int i = 0; i < values.length; i++) {
            listTag.addTag(i, FloatTag.valueOf(values[i]));
        }
        return listTag;
    }

    public static float[] readFloatArray(ListTag nbt) {
        int size = nbt.size();
        var result = new float[size];
        for (int i = 0; i < size; i++) result[i] = nbt.getFloat(i);
        return result;
    }

    public static ListTag getFloatListTag(CompoundTag nbt, String key) {
        return nbt.getList(key, Tag.TAG_FLOAT);
    }

    public static float[] getFloatArray(CompoundTag nbt, String key) {
        return readFloatArray(getFloatListTag(nbt, key));
    }

    // ============================================================================================================== //

    public static ListTag writeAABB(AABB aabb) {
        return writeDoubleArray(aabb.minX, aabb.minY, aabb.minZ, aabb.maxX, aabb.maxY, aabb.maxZ);
    }

    public static AABB readAABB(ListTag nbt) {
        double[] p = readDoubleArray(nbt);
        return new AABB(p[0], p[1], p[2], p[3], p[4], p[5]);
    }

    public static AABB getAABB(CompoundTag nbt, String key) {
        return readAABB(getDoubleListTag(nbt, key));
    }

    // ====================================================================== //

    public static ListTag writeVec3(Vec3 vec) {
        return writeDoubleArray(vec.x, vec.y, vec.z);
    }

    public static Vec3 readVec3(ListTag nbt) {
        double[] p = readDoubleArray(nbt);
        return new Vec3(p[0], p[1], p[2]);
    }

    public static Vec3 readVec3OrElse(ListTag nbt, Vec3 other) {
        double[] p = readDoubleArray(nbt);
        return (p.length == 3) ? new Vec3(p[0], p[1], p[2]) : other;
    }

    public static Vec3 readVec3OrElseGet(ListTag nbt, Supplier<Vec3> supplier) {
        double[] p = readDoubleArray(nbt);
        return (p.length == 3) ? new Vec3(p[0], p[1], p[2]) : supplier.get();
    }

    public static Vec3 getVec3(CompoundTag nbt, String key) {
        return readVec3(getDoubleListTag(nbt, key));
    }

    public static Vec3 getVec3OrElse(CompoundTag nbt, String key, Vec3 other) {
        return readVec3OrElse(getDoubleListTag(nbt, key), other);
    }

    public static Vec3 getVec3OrElseGet(CompoundTag nbt, String key, Supplier<Vec3> supplier) {
        return readVec3OrElseGet(getDoubleListTag(nbt, key), supplier);
    }

    // ====================================================================== //

    public static ListTag writeVector3f(Vector3f vec) {
        return writeFloatArray(vec.x(), vec.y(), vec.z());
    }

    public static Vector3f readVector3f(ListTag nbt) {
        float[] p = readFloatArray(nbt);
        return new Vector3f(p[0], p[1], p[2]);
    }

    public static Vector3f readVector3fOrElse(ListTag nbt, Vector3f other) {
        float[] p = readFloatArray(nbt);
        return (p.length == 3) ? new Vector3f(p[0], p[1], p[2]) : other;
    }

    public static Vector3f readVector3fOrElseGet(ListTag nbt, Supplier<Vector3f> supplier) {
        float[] p = readFloatArray(nbt);
        return (p.length == 3) ? new Vector3f(p[0], p[1], p[2]) : supplier.get();
    }

    public static Vector3f getVector3f(CompoundTag nbt, String key) {
        return readVector3f(getFloatListTag(nbt, key));
    }

    public static Vector3f getVector3fOrElse(CompoundTag nbt, String key, Vector3f other) {
        return readVector3fOrElse(getFloatListTag(nbt, key), other);
    }

    public static Vector3f getVector3fOrElseGet(CompoundTag nbt, String key, Supplier<Vector3f> supplier) {
        return readVector3fOrElseGet(getFloatListTag(nbt, key), supplier);
    }

}
