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

package org.auioc.mcmod.arnicalib.game.resource.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.apache.logging.log4j.Marker;
import org.auioc.mcmod.arnicalib.base.log.LogUtil;

import java.util.List;
import java.util.Map;

import static org.auioc.mcmod.arnicalib.ArnicaLib.LOGGER;

/**
 * This is a FAKE resource reload listener, do NOT register it!
 *
 * @see org.auioc.mcmod.arnicalib.mod.client.event.AHClientModEventHandler#onModelRegister
 */
@OnlyIn(Dist.CLIENT)
public class CustomModelLoader extends SimpleJsonResourceReloadListener {

    private static final Marker MARKER = LogUtil.getMarker(CustomModelLoader.class);
    private static final String MODELS_PATH = "models/";
    private static final Gson GSON = new GsonBuilder().create();

    private final ResourceManager resourceManager;
    private final String path;

    public CustomModelLoader(ResourceManager resourceManager, String path) {
        super(GSON, MODELS_PATH + path);
        this.resourceManager = resourceManager;
        this.path = path.endsWith("/") ? path : path + "/";
    }

    public List<ResourceLocation> list() {
        return super.prepare(this.resourceManager, null)
            .keySet()
            .stream()
            .map((id) -> new ResourceLocation(id.getNamespace(), this.path + id.getPath()))
            .peek((id) -> LOGGER.info(MARKER, "Found custom model '{}'", id.toString()))
            .toList();
    }

    public static List<ResourceLocation> list(ResourceManager resourceManager, String path) {
        return new CustomModelLoader(resourceManager, path).list();
    }

    // ====================================================================== //

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> map, ResourceManager resourceManager, ProfilerFiller profiler) { }

}
