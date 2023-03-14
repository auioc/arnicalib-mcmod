package org.auioc.mcmod.arnicalib.game.event.client;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import net.minecraft.client.resources.language.LanguageInfo;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.event.IModBusEvent;

@OnlyIn(Dist.CLIENT)
public class ClientLanguageLoadEvent extends Event implements IModBusEvent {

    private final ResourceManager resourceManager;
    private final List<LanguageInfo> languageInfo;
    private final Map<String, String> storage;
    private final boolean defaultRightToLeft;

    public ClientLanguageLoadEvent(ResourceManager resourceManager, List<LanguageInfo> languageInfo, Map<String, String> storage, boolean defaultRightToLeft) {
        this.resourceManager = resourceManager;
        this.languageInfo = Collections.unmodifiableList(languageInfo);
        this.storage = storage;
        this.defaultRightToLeft = defaultRightToLeft;
    }

    public ResourceManager getResourceManager() {
        return this.resourceManager;
    }

    public List<LanguageInfo> getLanguageInfo() {
        return this.languageInfo;
    }

    public Map<String, String> getStorage() {
        return this.storage;
    }

    public boolean isDefaultRightToLeft() {
        return this.defaultRightToLeft;
    }

}
