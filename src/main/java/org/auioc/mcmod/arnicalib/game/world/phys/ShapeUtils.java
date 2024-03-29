/*
 * Copyright (C) 2022-2024 AUIOC.ORG
 *
 * This file is part of ArnicaLib, a mod made for Minecraft.
 *
 * ArnicaLib is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <https://www.gnu.org/licenses/>.
 */

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
        var vertices = new Vec3[count];
        for (int i = 0; i < count; ++i) {
            double t = Math.toRadians(i * centralAngle);
            double sinT = Math.sin(t);
            double cosT = Math.cos(t);
            vertices[i] = centre
                .add(VectorUtils.multiply(VectorUtils.multiply(base.getLeft(), circumradius), cosT))
                .add(VectorUtils.multiply(VectorUtils.multiply(base.getRight(), circumradius), sinT));
        }

        return vertices;
    }

    public static Vec3[][] createStarPolygon(Vec3 centre, Vec3 normalVector, double circumradius, int points) {
        var vertices = createRegularPolygon(centre, normalVector, circumradius, 360.D / points);

        var lines = new Vec3[points][2];
        for (int i = 0, l = vertices.length; i < l; ++i) {
            lines[i] = new Vec3[] {
                vertices[i], vertices[(i + 2 >= l) ? i + 2 - l : i + 2]
            };
        }

        return lines;
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
