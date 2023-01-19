package org.auioc.mcmod.arnicalib.mod.client.event;

import org.auioc.mcmod.arnicalib.game.resource.model.CustomModelLoader;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ForgeModelBakery;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

@OnlyIn(Dist.CLIENT)
public final class AHClientModEventHandler {

    private static final String SRG_FIELD_MODELBAKERY_RESOURCEMANAGER = "f_119243_";

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        ModelBakery modelBakery = ForgeModelBakery.instance();
        ResourceManager resourceManager = ObfuscationReflectionHelper.getPrivateValue(ModelBakery.class, modelBakery, SRG_FIELD_MODELBAKERY_RESOURCEMANAGER);
        {
            CustomModelLoader.load(resourceManager, "custom");
        }
    }

}
