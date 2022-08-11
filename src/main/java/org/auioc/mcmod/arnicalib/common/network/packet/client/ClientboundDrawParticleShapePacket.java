package org.auioc.mcmod.arnicalib.common.network.packet.client;

import org.auioc.mcmod.arnicalib.api.game.network.IHPacket;
import org.auioc.mcmod.arnicalib.api.game.phys.Shape;
import org.auioc.mcmod.arnicalib.utils.game.ParticlePainter;
import org.auioc.mcmod.arnicalib.utils.game.ParticlePainter.Options;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent.Context;
import net.minecraftforge.registries.ForgeRegistries;

public class ClientboundDrawParticleShapePacket implements IHPacket {

    private final Shape shape;
    private final CompoundTag data;
    private final Options options;

    public ClientboundDrawParticleShapePacket(Shape shape, CompoundTag data, Options options) {
        this.shape = shape;
        this.data = data;
        this.options = options;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void handle(Context ctx) {
        ParticlePainter.Client.draw(this.shape, this.data, this.options);
    }

    @Override
    public void encode(FriendlyByteBuf buffer) {
        buffer.writeEnum(this.shape);
        buffer.writeNbt(this.data);
        buffer.writeDouble(this.options.stepLength());
        buffer.writeResourceLocation(this.options.particle().getType().getRegistryName());
        this.options.particle().writeToNetwork(buffer);
        buffer.writeBoolean(this.options.force());
    }

    public static ClientboundDrawParticleShapePacket decode(FriendlyByteBuf buffer) {
        var shape = buffer.readEnum(Shape.class);
        var data = buffer.readNbt();
        var stepLength = buffer.readDouble();
        var _particleType = ForgeRegistries.PARTICLE_TYPES.getValue(buffer.readResourceLocation());
        var particle = readParticle(buffer, _particleType);
        var force = buffer.readBoolean();
        return new ClientboundDrawParticleShapePacket(shape, data, new Options(stepLength, particle, force));
    }

    @SuppressWarnings("deprecation")
    private static <T extends ParticleOptions> T readParticle(FriendlyByteBuf buffer, ParticleType<T> particleType) {
        return particleType.getDeserializer().fromNetwork(particleType, buffer);
    }

}
