package org.auioc.mcmod.arnicalib.game.world.phys;

import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public class ShapeUtils {

    public static int countPoints(double l, double s) {
        return (int) (l / s);
    }

    public static Vec3[] getPointsOnLine(Vec3 start, Vec3 end, double stepLength) {
        double dX = (end.x - start.x);
        double dY = (end.y - start.y);
        double dZ = (end.z - start.z);

        double lineLength = Mth.length(dX, dY, dZ);

        int count = countPoints(lineLength, stepLength);
        var points = new Vec3[count];

        double stepLengthX = dX / count;
        double stepLengthY = dY / count;
        double stepLengthZ = dZ / count;
        for (int i = 0; i < count; i++) {
            double x = start.x + stepLengthX * i;
            double y = start.y + stepLengthY * i;
            double z = start.z + stepLengthZ * i;
            points[i] = new Vec3(x, y, z);
        }

        return points;
    }

    public static Vec3[] createRegularPolygon(Vec3 centre, Vec3 normalVector, double circumradius, double centralAngle) {
        var base = VectorUtils.getBaseVector(normalVector);

        int count = countPoints(360.0D, centralAngle);
        var vertexes = new Vec3[count];
        for (int i = 0; i < count; ++i) {
            double t = Math.toRadians(i * centralAngle);
            double sinT = Math.sin(t);
            double cosT = Math.cos(t);
            vertexes[i] = centre
                .add(VectorUtils.multiply(VectorUtils.multiply(base.getLeft(), circumradius), cosT))
                .add(VectorUtils.multiply(VectorUtils.multiply(base.getRight(), circumradius), sinT));
        }

        return vertexes;
    }

    public static Vec3[] createSphere(Vec3 centre, double radius, double deltaAngle) {
        var normal = new Vec3(1.0D, 0.0D, 0.0D);

        int count = countPoints(360.0D, deltaAngle);
        var points = new Vec3[count * count];
        for (int i = 0; i < count; ++i) {
            var circle = createRegularPolygon(centre, normal.yRot((float) Math.toRadians(i * deltaAngle)), radius, deltaAngle);
            for (int j = 0, l = circle.length; j < l; ++j) {
                points[(i * count) + j] = circle[j];
            }
        }

        return points;
    }

}
