package org.auioc.mcmod.arnicalib.common.network;

import static org.auioc.mcmod.arnicalib.common.network.AHPacketHandler.HANDLER;
import org.auioc.mcmod.arnicalib.common.network.packet.client.ClientboundDrawParticleShapePacket;

public final class AHPackets {

    protected static void register() {
        HANDLER.registerServerToClient(ClientboundDrawParticleShapePacket.class, ClientboundDrawParticleShapePacket::decode);
    }

}
