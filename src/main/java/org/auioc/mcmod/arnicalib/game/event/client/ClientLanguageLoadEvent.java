/*
 * Copyright (C) 2022-2024 AUIOC.ORG
 *
 * This file is part of ArnicaLib, a mod made for Minecraft.
 *
 * ArnicaLib is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <https://www.gnu.org/licenses/>.
 */

package org.auioc.mcmod.arnicalib.game.event.client;

import net.minecraft.server.packs.resources.ResourceManager;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;

import java.util.Collections;
import java.util.List;
import java.util.Map;

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
