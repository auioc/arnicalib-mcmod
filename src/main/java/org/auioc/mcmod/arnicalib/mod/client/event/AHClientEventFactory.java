package org.auioc.mcmod.arnicalib.mod.client.event;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.packs.resources.ResourceManager;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.fml.ModLoader;
import net.neoforged.neoforge.common.NeoForge;
import org.auioc.mcmod.arnicalib.game.event.client.ClientLanguageLoadEvent;
import org.auioc.mcmod.arnicalib.game.event.client.ClientPermissionChangedEvent;

import java.util.List;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public final class AHClientEventFactory {

    /**
     * @see org.auioc.mcmod.arnicalib.mod.mixin.client.MixinLocalPlayer#setPermissionLevel
     */
    public static void onPermissionChanged(LocalPlayer player, int oldLevel, int newLevel) {
        NeoForge.EVENT_BUS.post(new ClientPermissionChangedEvent(player, oldLevel, newLevel));
    }

    /**
     * @see org.auioc.mcmod.arnicalib.mod.mixin.client.MixinClientLanguage#loadFrom
     */
    public static void onClientLanguageLoad(ResourceManager resourceManager, List<String> languageCodes, Map<String, String> storage, boolean defaultRightToLeft) {
        ModLoader.get().postEvent(new ClientLanguageLoadEvent(resourceManager, languageCodes, storage, defaultRightToLeft));
    }

}
