package org.auioc.mcmod.arnicalib.game.resource;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import org.auioc.mcmod.arnicalib.base.awt.ImageUtils;
import org.auioc.mcmod.arnicalib.base.function.BiIntFunction;
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
