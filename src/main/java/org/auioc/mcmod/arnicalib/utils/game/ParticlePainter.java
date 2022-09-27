package org.auioc.mcmod.arnicalib.utils.game;

import java.util.function.Consumer;
import org.auioc.mcmod.arnicalib.base.phys.Shape;
import org.auioc.mcmod.arnicalib.common.network.AHPacketHandler;
import org.auioc.mcmod.arnicalib.common.network.packet.client.ClientboundDrawParticleShapePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ParticlePainter {

    public static final Options DEFAULT_OPTIONS = new Options(0.25D, ParticleTypes.ELECTRIC_SPARK, true);

    public static record Options(double stepLength, ParticleOptions particle, boolean force) {
    }

    @OnlyIn(Dist.CLIENT)
    public static class Client {

        @SuppressWarnings("resource")
        public static void drawPoint(Options options, double x, double y, double z) {
            Minecraft.getInstance().levelRenderer.addParticle(options.particle, options.force, !options.force, x, y, z, 0.0D, 0.0D, 0.0D);
        }

        public static void drawLine(Options options, double x1, double y1, double z1, double x2, double y2, double z2) {
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
                drawPoint(options, x, y, z);
            }
        }

        public static void drawPolygon(Options options, Vec3[] vertexes) {
            for (int i = 0, l = vertexes.length; i < l; i++) {
                Vec3 a = vertexes[i];
                Vec3 b = vertexes[(i + 1 == l) ? 0 : i + 1];
                drawLine(options, a.x, a.y, a.z, b.x, b.y, b.z);
            }
        }

        public static void drawCuboid(Options options, AABB aabb) {
            double[][] edges = AABBUtils.getEdges(aabb);
            for (double[] edge : edges) {
                drawLine(options, edge[0], edge[1], edge[2], edge[3], edge[4], edge[5]);
            }
        }


        public static void draw(Shape shape, Options options, CompoundTag data) {
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

    public static class Server {

        private static void draw(ServerPlayer player, Shape shape, Options options, Consumer<CompoundTag> serializer) {
            var nbt = new CompoundTag();
            serializer.accept(nbt);
            AHPacketHandler.sendToClient(player, new ClientboundDrawParticleShapePacket(shape, options, nbt));
        }


        public static void drawLine(ServerPlayer player, Options options, double x1, double y1, double z1, double x2, double y2, double z2) {
            draw(player, Shape.LINE, options, (nbt) -> {
                nbt.put("StartPoint", NbtUtils.writeDoubleArray(x1, y1, z1));
                nbt.put("EndPoint", NbtUtils.writeDoubleArray(x2, y2, z2));
            });
        }

        public static void drawPolygon(ServerPlayer player, Options options, Vec3[] vertexes) {
            draw(player, Shape.POLYGON, options, (nbt) -> {
                double[] p = new double[vertexes.length * 3];
                for (int i = 0, j = 0; i < vertexes.length; i++, j = i * 3) {
                    p[j] = vertexes[i].x;
                    p[j + 1] = vertexes[i].y;
                    p[j + 2] = vertexes[i].z;
                }
                nbt.put("Vertexes", NbtUtils.writeDoubleArray(p));
            });
        }

        public static void drawCuboid(ServerPlayer player, Options options, AABB aabb) {
            draw(player, Shape.CUBOID, options, (nbt) -> {
                nbt.put("AABB", NbtUtils.writeAABB(aabb));
            });
        }


        // #region overload

        public static void drawLine(ServerPlayer player, double x1, double y1, double z1, double x2, double y2, double z2) {
            drawLine(player, DEFAULT_OPTIONS, x1, y1, z1, x2, y2, z2);
        }

        public static void drawLine(ServerPlayer player, Vec3 start, Vec3 end) {
            drawLine(player, DEFAULT_OPTIONS, start.x, start.y, start.z, end.x, end.y, end.z);
        }

        public static void drawLine(ServerPlayer player, Vec3i start, Vec3i end) {
            drawLine(player, Vec3.atCenterOf(start), Vec3.atCenterOf(end));
        }

        public static void drawPolygon(ServerPlayer player, Vec3[] vertexes) {
            drawPolygon(player, DEFAULT_OPTIONS, vertexes);
        }

        public static void drawPolygon(ServerPlayer player, Vec3i[] vertexes) {
            Vec3[] _vertexes = new Vec3[vertexes.length];
            for (int i = 0; i < vertexes.length; i++) _vertexes[i] = Vec3.atCenterOf(vertexes[i]);
            drawPolygon(player, _vertexes);
        }

        public static void drawCuboid(ServerPlayer player, AABB aabb) {
            drawCuboid(player, DEFAULT_OPTIONS, aabb);
        }

        // #endregion overload

    }

}
