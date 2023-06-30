package org.auioc.mcmod.arnicalib.mod.mixin.client;

import org.auioc.mcmod.arnicalib.mod.client.event.AHClientEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.player.LocalPlayer;

@Mixin(value = LocalPlayer.class)
public class MixinLocalPlayer {

    @Shadow
    private int permissionLevel;

    @Inject(
        method = "Lnet/minecraft/client/player/LocalPlayer;setPermissionLevel(I)V",
        at = @At(value = "HEAD"),
        require = 1,
        allow = 1
    )
    private void setPermissionLevel(int pPermissionLevel, CallbackInfo ci) {
        AHClientEventFactory.onPermissionChanged(((LocalPlayer) (Object) this), this.permissionLevel, pPermissionLevel);
    }

}
