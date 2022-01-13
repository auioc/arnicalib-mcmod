package org.auioc.mods.arnicalib;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.auioc.mods.arnicalib.utils.LogUtil;
import org.auioc.mods.arnicalib.utils.java.VersionUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;

@Mod(ArnicaLib.MOD_ID)
public final class ArnicaLib {

    public static final String MOD_ID = "arnicalib";
    public static final String MOD_NAME = "ArnicaLib";
    public static final String MAIN_VERSION;
    public static final String FULL_VERSION;

    public static final Logger LOGGER = LogUtil.getLogger(MOD_NAME);
    private static final Marker CORE = LogUtil.getMarker("CORE");

    public ArnicaLib() {
        Initialization.init();
    }

    static {
        Pair<String, String> version = VersionUtils.getModVersion(ArnicaLib.class);
        MAIN_VERSION = version.getLeft();
        FULL_VERSION = version.getRight();
        LOGGER.info(CORE, "Version: " + MAIN_VERSION + " (" + FULL_VERSION + ")");
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static String i18n(String key) {
        return MOD_ID + "." + key;
    }

}
