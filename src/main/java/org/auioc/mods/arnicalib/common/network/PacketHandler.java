package org.auioc.mods.arnicalib.common.network;

import java.util.Optional;
import java.util.function.Function;
import org.auioc.mods.arnicalib.ArnicaLib;
import org.auioc.mods.arnicalib.api.game.network.IHPacket;
import org.auioc.mods.arnicalib.api.game.network.IHPacketHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

@SuppressWarnings("unused")
public final class PacketHandler implements IHPacketHandler {

    @SubscribeEvent
    public static void onCommonSetupEvent(FMLCommonSetupEvent event) {
        register();
    }

    private static final String PROTOCOL_VERSION = ArnicaLib.MAIN_VERSION;
    private static final SimpleChannel HANDLER = NetworkRegistry.newSimpleChannel(
        new ResourceLocation(ArnicaLib.MOD_ID, "network"),
        () -> PROTOCOL_VERSION,
        PROTOCOL_VERSION::equals,
        PROTOCOL_VERSION::equals
    );
    private static int index;

    private static <MSG extends IHPacket> void registerClientToServer(Class<MSG> type, Function<FriendlyByteBuf, MSG> decoder) {
        registerMessage(type, decoder, NetworkDirection.PLAY_TO_SERVER);
    }

    private static <MSG extends IHPacket> void registerServerToClient(Class<MSG> type, Function<FriendlyByteBuf, MSG> decoder) {
        registerMessage(type, decoder, NetworkDirection.PLAY_TO_CLIENT);
    }

    private static <MSG extends IHPacket> void registerMessage(Class<MSG> type, Function<FriendlyByteBuf, MSG> decoder, NetworkDirection networkDirection) {
        HANDLER.registerMessage(index++, type, IHPacket::encode, decoder, IHPacket::handle, Optional.of(networkDirection));
    }

    public static void register() {}

    public static <MSG extends IHPacket> void sendToServer(MSG msg) {
        HANDLER.sendToServer(msg);
    }

    public static <MSG extends IHPacket> void sendTo(ServerPlayer player, MSG msg) {
        if (!(player instanceof FakePlayer)) {
            HANDLER.sendTo(msg, player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
        }
    }

}
