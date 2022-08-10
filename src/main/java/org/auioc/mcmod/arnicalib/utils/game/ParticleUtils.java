package org.auioc.mcmod.arnicalib.utils.game;

import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.phys.Vec3;

public class ParticleUtils {

    private static final Minecraft MC = Minecraft.getInstance();

    public static void drawLine(double x1, double y1, double z1, double x2, double y2, double z2, double stepLength) {
        drawLine(new Vec3(x1, y1, z1), new Vec3(x2, y2, z2), stepLength);
    }

    public static void drawLine(Vec3 startPoint, Vec3 endPoint, double stepLength) {
        Vec3 line = startPoint.vectorTo(endPoint);

        double lineLength = line.length();
        long pointCount = Math.round(lineLength / stepLength);

        Vec3 unit = line.normalize();

        for (long i = 0; i < pointCount; i++) {
            Vec3 n = startPoint.add(unit.scale(i * stepLength));
            MC.level.addParticle(ParticleTypes.ELECTRIC_SPARK, n.x, n.y, n.z, 0.0D, 0.0D, 0.0D);
        }
    }

}
