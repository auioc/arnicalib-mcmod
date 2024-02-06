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
