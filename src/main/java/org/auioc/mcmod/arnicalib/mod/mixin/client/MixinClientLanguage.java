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

package org.auioc.mcmod.arnicalib.mod.mixin.client;

import java.util.List;
import java.util.Map;

import org.auioc.mcmod.arnicalib.mod.client.event.AHClientEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import net.minecraft.client.resources.language.ClientLanguage;
import net.minecraft.server.packs.resources.ResourceManager;

@Mixin(value = ClientLanguage.class)
public class MixinClientLanguage {

    @Inject(
        method = "Lnet/minecraft/client/resources/language/ClientLanguage;loadFrom(Lnet/minecraft/server/packs/resources/ResourceManager;Ljava/util/List;Z)Lnet/minecraft/client/resources/language/ClientLanguage;",
        at = @At(
            value = "NEW",
            target = "(Ljava/util/Map;Z)Lnet/minecraft/client/resources/language/ClientLanguage;"
        ),
        locals = LocalCapture.CAPTURE_FAILHARD,
        require = 1,
        allow = 1
    )
    private static void loadFrom(
        ResourceManager resourceManager, List<String> languageCodes, boolean defaultRightToLeft,
        CallbackInfoReturnable<ClientLanguage> cir,
        Map<String, String> map
    ) {
        AHClientEventFactory.onClientLanguageLoad(resourceManager, languageCodes, map, defaultRightToLeft);
    }

}
