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

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent;
import org.auioc.mcmod.arnicalib.game.resource.model.CustomModelLoader;
import org.auioc.mcmod.arnicalib.mod.client.resource.AHClientReloadListener;

@OnlyIn(Dist.CLIENT)
public final class AHClientModEventHandler {

    @SubscribeEvent
    public static void onRegisterAdditionalModel(ModelEvent.RegisterAdditional event) {
        CustomModelLoader.list(Minecraft.getInstance().getResourceManager(), "custom").forEach(event::register);
    }

    @SubscribeEvent
    public static void registerReloadListeners(final RegisterClientReloadListenersEvent event) {
        event.registerReloadListener(new AHClientReloadListener());
    }

}
