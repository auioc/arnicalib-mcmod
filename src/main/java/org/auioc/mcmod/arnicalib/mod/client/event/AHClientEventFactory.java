package org.auioc.mcmod.arnicalib.mod.client.event;

import org.auioc.mcmod.arnicalib.game.event.client.ClientPermissionsChangedEvent;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;

@OnlyIn(Dist.CLIENT)
public class AHClientEventFactory {

    private static final IEventBus BUS = MinecraftForge.EVENT_BUS;

    /**
     * @see org.auioc.mcmod.arnicalib.mod.mixin.client.MixinLocalPlayer#setPermissionLevel
     */
    public static void onPermissionChanged(LocalPlayer player, int oldLevel, int newLevel) {
        BUS.post(new ClientPermissionsChangedEvent(player, oldLevel, newLevel));
    }

}
