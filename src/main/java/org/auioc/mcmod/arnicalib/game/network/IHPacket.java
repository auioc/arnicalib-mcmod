package org.auioc.mcmod.arnicalib.game.network;

import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent.Context;

public interface IHPacket {

    void handle(Context ctx);

    void encode(FriendlyByteBuf buffer);

    static <MSG extends IHPacket> void handle(final MSG message, Supplier<Context> context) {
        Context ctx = context.get();
        ctx.enqueueWork(() -> message.handle(ctx));
        ctx.setPacketHandled(true);
    }

}
