package org.auioc.mcmod.arnicalib.utils.game;

import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;

public class ParticleUtils {

    private static final Minecraft MC = Minecraft.getInstance();

    public static void drawLine(double x1, double y1, double z1, double x2, double y2, double z2, double stepLength) {
        double lineLength = Mth.length((x2 - x1), (y2 - y1), (z2 - z1));
        long pointCount = Math.round(lineLength / stepLength);

        double projectedStepLengthX = (x2 - x1) / pointCount;
        double projectedStepLengthY = (y2 - y1) / pointCount;
        double projectedStepLengthZ = (z2 - z1) / pointCount;

        for (long i = 0; i < pointCount; i++) {
            double x = x1 + projectedStepLengthX * i;
            double y = y1 + projectedStepLengthY * i;
            double z = z1 + projectedStepLengthZ * i;
            MC.level.addParticle(ParticleTypes.ELECTRIC_SPARK, x, y, z, 0.0D, 0.0D, 0.0D);
        }
    }

}
