package org.auioc.mcmod.arnicalib.game.network;

import java.util.Optional;
import java.util.function.Function;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class HPacketHandler implements IHPacketHandler {

    private final SimpleChannel CHANNEL;
    private int index = 0;

    public HPacketHandler(ResourceLocation name, String protocolVersion) {
        this.CHANNEL = NetworkRegistry.ChannelBuilder
            .named(name)
            .networkProtocolVersion(() -> protocolVersion)
            .clientAcceptedVersions(protocolVersion::equals)
            .serverAcceptedVersions(protocolVersion::equals)
            .simpleChannel();
    }

    public HPacketHandler(String modid, String name, String protocolVersion) {
        this(new ResourceLocation(modid, name), protocolVersion);
    }

    public HPacketHandler(String modid, String protocolVersion) {
        this(new ResourceLocation(modid, "main"), protocolVersion);
    }


    @Override
    public <MSG extends IHPacket> void registerMessage(Class<MSG> packet, Function<FriendlyByteBuf, MSG> decoder, NetworkDirection direction) {
        CHANNEL.registerMessage(index++, packet, IHPacket::encode, decoder, IHPacket::handle, Optional.of(direction));
    }

    public <MSG extends IHPacket> void registerClientToServer(Class<MSG> packet, Function<FriendlyByteBuf, MSG> decoder) {
        registerMessage(packet, decoder, NetworkDirection.PLAY_TO_SERVER);
    }

    public <MSG extends IHPacket> void registerServerToClient(Class<MSG> packet, Function<FriendlyByteBuf, MSG> decoder) {
        registerMessage(packet, decoder, NetworkDirection.PLAY_TO_CLIENT);
    }


    @Override
    public <MSG extends IHPacket> void sendToServer(MSG message) {
        CHANNEL.sendToServer(message);
    }

    @Override
    public <MSG extends IHPacket> void sendToClient(ServerPlayer player, MSG message) {
        if (!(player instanceof FakePlayer)) {
            CHANNEL.sendTo(message, player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
        }
    }

}
