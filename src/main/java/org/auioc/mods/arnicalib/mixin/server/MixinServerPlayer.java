package org.auioc.mods.arnicalib.mixin.server;

import java.util.UUID;
import org.auioc.mods.arnicalib.server.event.ServerEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

@Mixin(value = ServerPlayer.class)
public abstract class MixinServerPlayer {

    // @org.spongepowered.asm.mixin.Debug(export = true, print = true)
    @Inject(
        method = "Lnet/minecraft/server/level/ServerPlayer;sendMessage(Lnet/minecraft/network/chat/Component;Lnet/minecraft/network/chat/ChatType;Ljava/util/UUID;)V",
        at = @At(value = "HEAD"),
        require = 1,
        allow = 1,
        cancellable = true
    )
    private void onSendMessage(Component p_9147_, ChatType p_9148_, UUID p_9149_, CallbackInfo ci) {
        if (ServerEventFactory.fireServerPlayerSendMessageEvent(p_9147_, p_9148_, p_9149_)) {
            ci.cancel();
        }
    }

}
