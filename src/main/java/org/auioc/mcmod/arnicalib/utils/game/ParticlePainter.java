package org.auioc.mcmod.arnicalib.utils.game;

import java.util.function.Consumer;
import org.auioc.mcmod.arnicalib.api.game.phys.Shape;
import org.auioc.mcmod.arnicalib.common.network.AHPacketHandler;
import org.auioc.mcmod.arnicalib.common.network.packet.client.ClientboundDrawParticleShapePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ParticlePainter {

    public static final Options DEFAULT_OPTIONS = new Options(0.25D, ParticleTypes.ELECTRIC_SPARK, true);

    public static record Options(double stepLength, ParticleOptions particle, boolean force) {
    }

    @OnlyIn(Dist.CLIENT)
    public static class Client {

        @SuppressWarnings("resource")
        public static void drawLine(double x1, double y1, double z1, double x2, double y2, double z2, Options options) {
            double diffX = (x2 - x1);
            double diffY = (y2 - y1);
            double diffZ = (z2 - z1);

            double lineLength = Mth.length(diffX, diffY, diffZ);

            long pointCount = Math.round(lineLength / options.stepLength);

            double projectedStepLengthX = diffX / pointCount;
            double projectedStepLengthY = diffY / pointCount;
            double projectedStepLengthZ = diffZ / pointCount;

            for (long i = 0; i < pointCount; i++) {
                double x = x1 + projectedStepLengthX * i;
                double y = y1 + projectedStepLengthY * i;
                double z = z1 + projectedStepLengthZ * i;
                Minecraft.getInstance().levelRenderer.addParticle(options.particle, options.force, !options.force, x, y, z, 0.0D, 0.0D, 0.0D);
            }
        }

        public static void drawCuboid(AABB aabb, Options options) {
            double[][] edges = AABBUtils.getEdges(aabb);
            for (double[] edge : edges) {
                drawLine(edge[0], edge[1], edge[2], edge[3], edge[4], edge[5], options);
            }
        }

        public static void draw(Shape shape, CompoundTag data, Options options) {
            switch (shape) {
                case LINE -> {
                    double[] p1 = NbtUtils.getDoubleArray(data, "StartPoint");
                    double[] p2 = NbtUtils.getDoubleArray(data, "EndPoint");
                    drawLine(p1[0], p1[1], p1[2], p2[0], p2[1], p2[2], options);
                }
                case CUBOID -> {
                    drawCuboid(NbtUtils.getAABB(data, "AABB"), options);
                }
                default -> throw new IllegalArgumentException("Unexpected shape: " + shape);
            }
        }

    }

    public static class Server {

        private static void draw(ServerPlayer player, Shape shape, Options options, Consumer<CompoundTag> serializer) {
            var nbt = new CompoundTag();
            serializer.accept(nbt);
            AHPacketHandler.sendToClient(player, new ClientboundDrawParticleShapePacket(shape, nbt, options));
        }

        public static void drawLine(ServerPlayer player, double x1, double y1, double z1, double x2, double y2, double z2, Options options) {
            draw(player, Shape.LINE, options, (nbt) -> {
                nbt.put("StartPoint", NbtUtils.writeDoubleArray(x1, y1, z1));
                nbt.put("EndPoint", NbtUtils.writeDoubleArray(x2, y2, z2));
            });
        }

        public static void drawCuboid(ServerPlayer player, AABB aabb, Options options) {
            draw(player, Shape.CUBOID, options, (nbt) -> {
                nbt.put("AABB", NbtUtils.writeAABB(aabb));
            });
        }

    }

}
