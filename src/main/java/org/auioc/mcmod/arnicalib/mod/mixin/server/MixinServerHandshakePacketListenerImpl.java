package org.auioc.mcmod.arnicalib.mod.mixin.server;

import net.minecraft.network.Connection;
import net.minecraft.network.protocol.handshake.ClientIntentionPacket;
import net.minecraft.server.network.ServerHandshakePacketListenerImpl;
import org.auioc.mcmod.arnicalib.mod.server.event.AHServerEventFactory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ServerHandshakePacketListenerImpl.class)
public class MixinServerHandshakePacketListenerImpl {

    @Final
    @Shadow
    private Connection connection;

    @Inject(
        method = "Lnet/minecraft/server/network/ServerHandshakePacketListenerImpl;handleIntention(Lnet/minecraft/network/protocol/handshake/ClientIntentionPacket;)V",
        at = @At(value = "HEAD"),
        require = 1,
        allow = 1,
        cancellable = true
    )
    private void handleServerLogin(ClientIntentionPacket pPacket, CallbackInfo ci) {
        if (AHServerEventFactory.onServerLogin(pPacket, connection)) {
            ci.cancel();
        }
    }

}
