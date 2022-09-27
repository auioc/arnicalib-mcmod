package org.auioc.mcmod.arnicalib.game.particle;

import org.auioc.mcmod.arnicalib.base.phys.Shape;
import org.auioc.mcmod.arnicalib.game.nbt.NbtUtils;
import org.auioc.mcmod.arnicalib.game.world.phys.AABBUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
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

    public static void drawLine(ParticlePainterOptions options, double x1, double y1, double z1, double x2, double y2, double z2) {
        double diffX = (x2 - x1);
        double diffY = (y2 - y1);
        double diffZ = (z2 - z1);

        double lineLength = Mth.length(diffX, diffY, diffZ);

        long pointCount = Math.round(lineLength / options.stepLength());

        double projectedStepLengthX = diffX / pointCount;
        double projectedStepLengthY = diffY / pointCount;
        double projectedStepLengthZ = diffZ / pointCount;

        for (long i = 0; i < pointCount; i++) {
            double x = x1 + projectedStepLengthX * i;
            double y = y1 + projectedStepLengthY * i;
            double z = z1 + projectedStepLengthZ * i;
            drawPoint(options, x, y, z);
        }
    }

    public static void drawPolygon(ParticlePainterOptions options, Vec3[] vertexes) {
        for (int i = 0, l = vertexes.length; i < l; i++) {
            Vec3 a = vertexes[i];
            Vec3 b = vertexes[(i + 1 == l) ? 0 : i + 1];
            drawLine(options, a.x, a.y, a.z, b.x, b.y, b.z);
        }
    }

    public static void drawCuboid(ParticlePainterOptions options, AABB aabb) {
        double[][] edges = AABBUtils.getEdges(aabb);
        for (double[] edge : edges) {
            drawLine(options, edge[0], edge[1], edge[2], edge[3], edge[4], edge[5]);
        }
    }


    public static void draw(Shape shape, ParticlePainterOptions options, CompoundTag data) {
        switch (shape) {
            case LINE -> {
                double[] p1 = NbtUtils.getDoubleArray(data, "StartPoint");
                double[] p2 = NbtUtils.getDoubleArray(data, "EndPoint");
                drawLine(options, p1[0], p1[1], p1[2], p2[0], p2[1], p2[2]);
            }
            case CUBOID -> {
                drawCuboid(options, NbtUtils.getAABB(data, "AABB"));
            }
            case POLYGON -> {
                double[] p = NbtUtils.getDoubleArray(data, "Vertexes");
                Vec3[] vertexes = new Vec3[p.length / 3];
                for (int i = 0, j = 0; i < vertexes.length; i++, j = i * 3) {
                    vertexes[i] = new Vec3(p[j], p[j + 1], p[j + 2]);
                }
                drawPolygon(options, vertexes);
            }
            default -> throw new IllegalArgumentException("Unexpected shape: " + shape);
        }
    }

}
