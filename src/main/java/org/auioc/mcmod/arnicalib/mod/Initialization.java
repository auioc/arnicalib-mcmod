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

package org.auioc.mcmod.arnicalib.mod;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import org.auioc.mcmod.arnicalib.ArnicaLib;
import org.auioc.mcmod.arnicalib.game.mod.ExtensionPointUtils;
import org.auioc.mcmod.arnicalib.mod.client.ClientInitialization;
import org.auioc.mcmod.arnicalib.mod.common.tag.HTags;
import org.auioc.mcmod.arnicalib.mod.server.event.AHServerEventHandler;
import org.auioc.mcmod.arnicalib.mod.server.loot.AHGlobalLootModifiers;
import org.auioc.mcmod.arnicalib.mod.server.loot.AHLootItemConditions;
import org.auioc.mcmod.arnicalib.mod.server.loot.AHLootItemFunctions;

public final class Initialization {

    public static void init() {
        registerConfig();
        modSetup();
        forgeSetup();
        if (FMLEnvironment.dist == Dist.CLIENT) {
            ClientInitialization.init();
        } else {
            ExtensionPointUtils.serverOnly();
        }
    }

    private static final IEventBus modEventBus = ArnicaLib.getModEventBus();
    private static final IEventBus forgeEventBus = NeoForge.EVENT_BUS;

    private static void registerConfig() { }

    private static void modSetup() {
        AHGlobalLootModifiers.GLOBAL_LOOT_MODIFIERS.register(modEventBus);
        AHLootItemConditions.LOOT_CONDITION_TYPES.register(modEventBus);
        AHLootItemFunctions.LOOT_FUNCTION_TYPES.register(modEventBus);
        HTags.init();
    }

    private static void forgeSetup() {
        forgeEventBus.register(AHServerEventHandler.class);
    }

}
