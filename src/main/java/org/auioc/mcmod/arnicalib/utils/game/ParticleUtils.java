package org.auioc.mcmod.arnicalib.utils.game;

import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public class ParticleUtils {

    private static final Minecraft MC = Minecraft.getInstance();

    public static void drawLine(Vec3 startPoint, Vec3 endPoint, double stepLength) {
        drawLine(startPoint.x, startPoint.y, startPoint.z, endPoint.x, endPoint.y, endPoint.z, stepLength);
    }

    public static void drawLine(double x1, double y1, double z1, double x2, double y2, double z2, double stepLength) {
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
            MC.level.addParticle(ParticleTypes.ELECTRIC_SPARK, x, y, z, 0.0D, 0.0D, 0.0D);
        }
    }

}
