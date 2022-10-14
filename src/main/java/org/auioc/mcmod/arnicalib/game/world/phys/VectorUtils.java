package org.auioc.mcmod.arnicalib.game.world.phys;

import org.apache.commons.lang3.tuple.Pair;
import net.minecraft.world.phys.Vec3;

public class VectorUtils {

    public static Vec3 add(Vec3 v, double b) {
        return v.add(b, b, b);
    }

    public static Vec3 subtract(Vec3 v, double b) {
        return v.subtract(b, b, b);
    }

    public static Vec3 multiply(Vec3 v, double b) {
        return v.multiply(b, b, b);
    }

    public static Vec3[] fromDoubleArray(double[] d) {
        Vec3[] v = new Vec3[d.length / 3];
        for (int i = 0, j = 0; i < v.length; ++i, j = i * 3) {
            v[i] = new Vec3(d[j], d[j + 1], d[j + 2]);
        }
        return v;
    }

    public static double[] toDoubleArray(Vec3[] v) {
        double[] d = new double[v.length * 3];
        for (int i = 0, j = 0; i < v.length; ++i, j = i * 3) {
            d[j] = v[i].x;
            d[j + 1] = v[i].y;
            d[j + 2] = v[i].z;
        }
        return d;
    }

    /**
     * @param normalVector normal vector to a plane
     * @return two base vectors in this plane
     */
    public static Pair<Vec3, Vec3> getBaseVector(Vec3 normalVector) {
        Vec3 N = normalVector.normalize();

        // y > z > x  ( 0,  z, -y)
        // z > y > x  ( 0, -z,  y)
        // x > z > y  ( z,  0, -x)
        // z > x > y  (-z,  0,  x)
        // x > y > z  ( y, -x,  0)
        // y > x > z  (-y,  x,  0)
        Vec3 vA;
        double aX = Math.abs(N.x);
        double aY = Math.abs(N.y);
        double aZ = Math.abs(N.z);
        if (aX >= aY && aY >= aZ) {
            vA = new Vec3(N.y, -N.x, 0);
        } else if (aX >= aZ && aZ >= aY) {
            vA = new Vec3(N.z, 0, -N.x);
        } else if (aY >= aX && aX >= aZ) {
            vA = new Vec3(-N.y, N.x, 0);
        } else if (aY >= aZ && aZ >= aX) {
            vA = new Vec3(0, N.z, -N.y);
        } else if (aZ >= aX && aX >= aY) {
            vA = new Vec3(-N.z, 0, N.x);
        } else if (aZ >= aY && aY >= aX) {
            vA = new Vec3(0, -N.z, N.y);
        } else {
            throw new RuntimeException();
        }
        Vec3 vB = N.cross(vA).normalize();

        return Pair.of(vA, vB);
    }

}
