package org.auioc.mcmod.arnicalib.game.network;

import java.util.function.Function;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkDirection;

public interface IHPacketHandler {

    <MSG extends IHPacket> void registerMessage(Class<MSG> packet, Function<FriendlyByteBuf, MSG> decoder, NetworkDirection direction);

    @OnlyIn(Dist.CLIENT)
    <MSG extends IHPacket> void sendToServer(MSG message);

    <MSG extends IHPacket> void sendToClient(ServerPlayer player, MSG message);

    <MSG extends IHPacket> void sendToAllClient(MSG message);

}
