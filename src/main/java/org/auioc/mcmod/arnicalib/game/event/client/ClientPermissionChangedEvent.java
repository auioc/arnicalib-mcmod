package org.auioc.mcmod.arnicalib.game.event.client;

import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ClientPermissionChangedEvent extends ClientPlayerEvent {

    private final int oldLevel;
    private final int newLevel;

    public ClientPermissionChangedEvent(LocalPlayer player, int oldLevel, int newLevel) {
        super(player);
        this.oldLevel = oldLevel;
        this.newLevel = newLevel;
    }

    public int getOldLevel() {
        return oldLevel;
    }

    public int getNewLevel() {
        return newLevel;
    }
}
