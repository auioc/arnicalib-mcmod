package org.auioc.mcmod.arnicalib.mod.client.event;

import java.util.List;
import java.util.Map;
import org.auioc.mcmod.arnicalib.game.event.client.ClientLanguageLoadEvent;
import org.auioc.mcmod.arnicalib.game.event.client.ClientPermissionsChangedEvent;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.language.LanguageInfo;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoader;

@OnlyIn(Dist.CLIENT)
public final class AHClientEventFactory {

    /**
     * @see org.auioc.mcmod.arnicalib.mod.mixin.client.MixinLocalPlayer#setPermissionLevel
     */
    public static void onPermissionChanged(LocalPlayer player, int oldLevel, int newLevel) {
        MinecraftForge.EVENT_BUS.post(new ClientPermissionsChangedEvent(player, oldLevel, newLevel));
    }

    /**
     * @see org.auioc.mcmod.arnicalib.mod.mixin.client.MixinClientLanguage#loadFrom
     */
    public static void onClientLanguageLoad(ResourceManager resourceManager, List<LanguageInfo> languageInfo, Map<String, String> storage, boolean defaultRightToLeft) {
        ModLoader.get().postEvent(new ClientLanguageLoadEvent(resourceManager, languageInfo, storage, defaultRightToLeft));
    }

}
