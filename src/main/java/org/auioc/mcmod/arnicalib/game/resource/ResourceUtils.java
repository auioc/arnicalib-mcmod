package org.auioc.mcmod.arnicalib.game.resource;

import net.minecraft.ResourceLocationException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ResourceUtils {

    public static ResourceLocation modelIdOrElse(String modelId, ResourceLocation other) {
        try {
            return modelId.contains("#") ? new ModelResourceLocation(modelId) : new ResourceLocation(modelId);
        } catch (ResourceLocationException e) {
            return other;
        }
    }

    public static ResourceLocation modelId(String modelId) {
        return modelIdOrElse(modelId, ModelBakery.MISSING_MODEL_LOCATION);
    }

    // ====================================================================== //

    public static boolean isValidModelId(String modelId) {
        try {
            if (modelId.contains("#")) {
                new ModelResourceLocation(modelId);
            } else {
                new ResourceLocation(modelId);
            }
            return true;
        } catch (ResourceLocationException e) {
            return false;
        }
    }

    // ====================================================================== //

    public static BakedModel getBakedModel(String modelId) {
        return Minecraft.getInstance().getModelManager().getModel(modelId(modelId));
    }

    public static BakedModel getBakedModel(ResourceLocation modelId) {
        return Minecraft.getInstance().getModelManager().getModel(modelId);
    }

}
