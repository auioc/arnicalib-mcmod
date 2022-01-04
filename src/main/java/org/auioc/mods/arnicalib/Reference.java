package org.auioc.mods.arnicalib;

import net.minecraft.resources.ResourceLocation;

public final class Reference {

    public static ResourceLocation ResourceId(String path) {
        return new ResourceLocation(ArnicaLib.MOD_ID, path);
    }

    public static String I18nKey(String path) {
        return ArnicaLib.MOD_ID + "." + path;
    }

}
