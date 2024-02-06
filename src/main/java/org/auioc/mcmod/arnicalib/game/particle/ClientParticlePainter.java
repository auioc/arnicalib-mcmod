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

package org.auioc.mcmod.arnicalib.game.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.auioc.mcmod.arnicalib.game.world.phys.AABBUtils;
import org.auioc.mcmod.arnicalib.game.world.phys.ShapeUtils;

@OnlyIn(Dist.CLIENT)
public class ClientParticlePainter {

    @SuppressWarnings("resource")
    public static void drawPoint(ParticlePainterOptions options, double x, double y, double z) {
        Minecraft.getInstance().levelRenderer.addParticle(options.particle(), options.force(), !options.force(), x, y, z, 0.0D, 0.0D, 0.0D);
    }

    public static void drawLine(ParticlePainterOptions options, Vec3 start, Vec3 end) {
        for (var p : ShapeUtils.getPointsOnLine(start, end, options.stepLength())) {
            drawPoint(options, p.x, p.y, p.z);
        }
    }

    public static void drawPolygon(ParticlePainterOptions options, Vec3[] vertices) {
        for (int i = 0, l = vertices.length; i < l; ++i) {
            drawLine(options, vertices[i], vertices[(i + 1 == l) ? 0 : i + 1]);
        }
    }

    public static void drawPolygon(ParticlePainterOptions options, Vec3 centre, Vec3 normalVector, double circumradius, double centralAngle) {
        drawPolygon(options, ShapeUtils.createRegularPolygon(centre, normalVector, circumradius, centralAngle));
    }

    public static void drawStarPolygon(ParticlePainterOptions options, Vec3 centre, Vec3 normalVector, double circumradius, int points) {
        for (var l : ShapeUtils.createStarPolygon(centre, normalVector, circumradius, points)) {
            drawLine(options, l[0], l[1]);
        }
    }

    public static void drawCircle(ParticlePainterOptions options, Vec3 centre, Vec3 normalVector, double radius, double deltaAngle) {
        for (var p : ShapeUtils.createRegularPolygon(centre, normalVector, radius, deltaAngle)) {
            drawPoint(options, p.x, p.y, p.z);
        }
    }

    public static void drawCuboid(ParticlePainterOptions options, AABB aabb) {
        double[][] edges = AABBUtils.getEdges(aabb);
        for (double[] edge : edges) {
            drawLine(options, new Vec3(edge[0], edge[1], edge[2]), new Vec3(edge[3], edge[4], edge[5]));
        }
    }

    public static void drawSphere(ParticlePainterOptions options, Vec3 centre, double radius, double deltaAngle) {
        for (var p : ShapeUtils.createSphere(centre, radius, deltaAngle)) {
            drawPoint(options, p.x, p.y, p.z);
        }
    }

}
