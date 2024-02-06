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

package org.auioc.mcmod.arnicalib.game.resource;

import net.minecraft.ResourceLocationException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.auioc.mcmod.arnicalib.base.awt.ImageUtils;
import org.auioc.mcmod.arnicalib.base.function.BiIntFunction;

import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@OnlyIn(Dist.CLIENT)
public class ResourceUtils {

    public static ResourceLocation modelIdOrElse(String modelId, ResourceLocation other) {
        try {
            if (modelId.contains("#")) {
                var split = modelId.split("#");
                return new ModelResourceLocation(new ResourceLocation(split[0]), split[1]);
            }
            return new ResourceLocation(modelId);
        } catch (ResourceLocationException e) {
            return other;
        }
    }

    public static ResourceLocation modelId(String modelId) {
        return modelIdOrElse(modelId, ModelBakery.MISSING_MODEL_LOCATION);
    }

    // ====================================================================== //

    public static boolean isValidModelId(String modelId) {
        return modelIdOrElse(modelId, null) != null;
    }

    // ====================================================================== //

    public static BakedModel getBakedModel(String modelId) {
        return Minecraft.getInstance().getModelManager().getModel(modelId(modelId));
    }

    public static BakedModel getBakedModel(ResourceLocation modelId) {
        return Minecraft.getInstance().getModelManager().getModel(modelId);
    }

    // ====================================================================== //

    public static BufferedImage getImage(ResourceLocation id) throws IOException {
        return ImageIO.read(Minecraft.getInstance().getResourceManager().getResourceOrThrow(id).open());
    }

    public static int getARGBColorFromTexture(ResourceLocation id, int x, int y) {
        try {
            return ImageUtils.getColor(getImage(id), x, y).getRGB();
        } catch (IOException e) {
            return 0;
        }
    }

    @Nullable
    public static int[] getARGBColorsFromTexture(ResourceLocation id, BiIntFunction<Rectangle> rectangle) {
        try {
            return ImageUtils.getARGBColors(getImage(id), rectangle);
        } catch (IOException e) {
            return null;
        }
    }

}
