package org.auioc.mcmod.arnicalib.game.world.phys;

import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public class ShapeUtils {

    public static Vec3[] getPointsOnLine(Vec3 start, Vec3 end, double stepLength) {
        double dX = (end.x - start.x);
        double dY = (end.y - start.y);
        double dZ = (end.z - start.z);

        double lineLength = Mth.length(dX, dY, dZ);

        int pointCount = (int) (lineLength / stepLength);
        var points = new Vec3[pointCount];

        double stepLengthX = dX / pointCount;
        double stepLengthY = dY / pointCount;
        double stepLengthZ = dZ / pointCount;
        for (int i = 0; i < pointCount; i++) {
            double x = start.x + stepLengthX * i;
            double y = start.y + stepLengthY * i;
            double z = start.z + stepLengthZ * i;
            points[i] = new Vec3(x, y, z);
        }

        return points;
    }

    public static Vec3[] createRegularPolygon(Vec3 centre, Vec3 normalVector, double circumradius, double centralAngle) {
        var base = VectorUtils.getBaseVector(normalVector);

        int pointCount = (int) (360.0D / centralAngle);
        var vertexes = new Vec3[pointCount];
        for (int i = 0; i < pointCount; ++i) {
            double t = Math.toRadians(i * centralAngle);
            double sinT = Math.sin(t);
            double cosT = Math.cos(t);
            vertexes[i] = centre
                .add(VectorUtils.multiply(VectorUtils.multiply(base.getLeft(), circumradius), cosT))
                .add(VectorUtils.multiply(VectorUtils.multiply(base.getRight(), circumradius), sinT));
        }

        return vertexes;
    }

}
