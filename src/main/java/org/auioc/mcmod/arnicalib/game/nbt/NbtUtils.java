package org.auioc.mcmod.arnicalib.game.nbt;

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

    public static Vec3 getVec3(CompoundTag nbt, String key) {
        return readVec3(getDoubleListTag(nbt, key));
    }

}
