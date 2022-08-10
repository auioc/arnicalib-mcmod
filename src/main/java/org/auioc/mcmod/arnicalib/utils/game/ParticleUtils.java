package org.auioc.mcmod.arnicalib.utils.game;

import net.minecraft.client.Minecraft;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class ParticleUtils {

    private static final double DEFAULT_STEP_LENGTH = 0.25D;
    private static final SimpleParticleType DEFAULT_PARTICLE = ParticleTypes.ELECTRIC_SPARK;

    /*================================================================================================================*/
    // #region drawLine

    @SuppressWarnings("resource")
    public static <T extends ParticleOptions> void drawLine(double x1, double y1, double z1, double x2, double y2, double z2, double stepLength, T particle, boolean force) {
        double diffX = (x2 - x1);
        double diffY = (y2 - y1);
        double diffZ = (z2 - z1);

        double lineLength = Mth.length(diffX, diffY, diffZ);

        long pointCount = Math.round(lineLength / stepLength);

        double projectedStepLengthX = diffX / pointCount;
        double projectedStepLengthY = diffY / pointCount;
        double projectedStepLengthZ = diffZ / pointCount;

        for (long i = 0; i < pointCount; i++) {
            double x = x1 + projectedStepLengthX * i;
            double y = y1 + projectedStepLengthY * i;
            double z = z1 + projectedStepLengthZ * i;
            //                                     force, decreased
            Minecraft.getInstance().levelRenderer.addParticle(particle, force, !force, x, y, z, 0.0D, 0.0D, 0.0D);
        }
    }

    public static <T extends ParticleOptions> void drawLine(double x1, double y1, double z1, double x2, double y2, double z2, T particle) {
        drawLine(x1, y1, z1, x2, y2, z2, DEFAULT_STEP_LENGTH, particle, true);
    }

    public static void drawLine(Vec3 startPoint, Vec3 endPoint) {
        drawLine(startPoint.x, startPoint.y, startPoint.z, endPoint.x, endPoint.y, endPoint.z, DEFAULT_PARTICLE);
    }

    public static void drawLine(Vec3i startPoint, Vec3i endPoint, double stepLength) {
        drawLine(startPoint.getX(), startPoint.getY(), startPoint.getZ(), endPoint.getX(), endPoint.getY(), endPoint.getZ(), DEFAULT_PARTICLE);
    }

    // #endregion drawLine

    /*================================================================================================================*/
    // #region drawCuboid

    public static <T extends ParticleOptions> void drawCuboid(AABB aabb, double stepLength, T particle, boolean force) {
        double[][] edges = AABBUtils.getEdges(aabb);
        for (double[] edge : edges) {
            drawLine(edge[0], edge[1], edge[2], edge[3], edge[4], edge[5], stepLength, particle, force);
        }
    }

    public static <T extends ParticleOptions> void drawCuboid(AABB aabb, T particle) {
        drawCuboid(aabb, DEFAULT_STEP_LENGTH, particle, true);
    }

    public static <T extends ParticleOptions> void drawCuboid(AABB aabb) {
        drawCuboid(aabb, DEFAULT_PARTICLE);
    }

    // #endregion drawCuboid

}
