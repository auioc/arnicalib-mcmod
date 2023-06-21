package org.auioc.mcmod.arnicalib.game.event.client;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.event.IModBusEvent;

@OnlyIn(Dist.CLIENT)
public class ClientLanguageLoadEvent extends Event implements IModBusEvent {

    private final ResourceManager resourceManager;
    private final List<String> languageCodes;
    private final Map<String, String> storage;
    private final boolean defaultRightToLeft;

    public ClientLanguageLoadEvent(ResourceManager resourceManager, List<String> languageCodes, Map<String, String> storage, boolean defaultRightToLeft) {
        this.resourceManager = resourceManager;
        this.languageCodes = Collections.unmodifiableList(languageCodes);
        this.storage = storage;
        this.defaultRightToLeft = defaultRightToLeft;
    }

    public ResourceManager getResourceManager() {
        return this.resourceManager;
    }

    public List<String> getLanguageCodes() {
        return this.languageCodes;
    }

    public Map<String, String> getStorage() {
        return this.storage;
    }

    public boolean isDefaultRightToLeft() {
        return this.defaultRightToLeft;
    }

}
