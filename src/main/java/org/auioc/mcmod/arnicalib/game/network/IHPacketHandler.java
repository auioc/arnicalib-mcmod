package org.auioc.mcmod.arnicalib.game.network;

import java.util.function.Function;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;

public interface IHPacketHandler {

    <MSG extends IHPacket> void registerMessage(Class<MSG> packet, Function<FriendlyByteBuf, MSG> decoder, NetworkDirection direction);

    <MSG extends IHPacket> void sendToServer(MSG message);

    <MSG extends IHPacket> void sendToClient(ServerPlayer player, MSG message);

}
