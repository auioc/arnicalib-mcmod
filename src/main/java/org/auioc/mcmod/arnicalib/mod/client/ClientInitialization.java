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

package org.auioc.mcmod.arnicalib.mod.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.ClientChatReceivedEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.auioc.mcmod.arnicalib.ArnicaLib;
import org.auioc.mcmod.arnicalib.base.reflection.ReflectionUtils;
import org.auioc.mcmod.arnicalib.game.mod.EnvironmentUtils;
import org.auioc.mcmod.arnicalib.game.mod.ExtensionPointUtils;
import org.auioc.mcmod.arnicalib.mod.client.event.AHClientEventHandler;
import org.auioc.mcmod.arnicalib.mod.client.event.AHClientModEventHandler;

@OnlyIn(Dist.CLIENT)
public final class ClientInitialization {

    public static void init() {
        ExtensionPointUtils.clientOnly();
        registerConfig();
        modSetup();
        forgeSetup();
    }

    private static final IEventBus modEventBus = ArnicaLib.getModEventBus();
    private static final IEventBus forgeEventBus = NeoForge.EVENT_BUS;

    private static void registerConfig() { }

    private static void modSetup() {
        modEventBus.register(AHClientModEventHandler.class);
    }

    private static void forgeSetup() {
        forgeEventBus.register(AHClientEventHandler.class);
        if (EnvironmentUtils.IS_DEV) {
            forgeEventBus.addListener(ClientInitialization::clientTestHandler);
        }
    }

    // ============================================================================================================== //

    private static void clientTestHandler(ClientChatReceivedEvent event) {
        if (event.getMessage().getString().equals("<Dev> .test")) {
            ReflectionUtils.invokeStatic("org.auioc.mcmod.arnicalib.mod.test.DevTestHandlerClient", "test");
        }
    }

}
