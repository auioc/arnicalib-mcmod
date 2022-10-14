package org.auioc.mcmod.arnicalib.game.particle;

import org.auioc.mcmod.arnicalib.game.world.phys.AABBUtils;
import org.auioc.mcmod.arnicalib.game.world.phys.ShapeUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

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

    public static void drawPolygon(ParticlePainterOptions options, Vec3[] vertexes) {
        for (int i = 0, l = vertexes.length; i < l; ++i) {
            drawLine(options, vertexes[i], vertexes[(i + 1 == l) ? 0 : i + 1]);
        }
    }

    public static void drawPolygon(ParticlePainterOptions options, Vec3 centre, Vec3 normalVector, double circumradius, double centralAngle) {
        drawPolygon(options, ShapeUtils.createRegularPolygon(centre, normalVector, circumradius, centralAngle));
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

}
