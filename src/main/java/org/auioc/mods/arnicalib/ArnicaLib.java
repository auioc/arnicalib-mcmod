package org.auioc.mods.arnicalib;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.auioc.mods.arnicalib.api.java.data.Tuple;
import org.auioc.mods.arnicalib.utils.LogUtil;
import org.auioc.mods.arnicalib.utils.java.JarUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;

@Mod(ArnicaLib.MOD_ID)
public final class ArnicaLib {

    public static final String MOD_ID = "arnicalib";
    public static final String MOD_NAME = "ArnicaLib";
    public static String MAIN_VERSION;
    public static String FULL_VERSION;

    public static final Logger LOGGER = LogUtil.getLogger(MOD_NAME);
    private static final Marker CORE = LogUtil.getMarker("CORE");

    public ArnicaLib() {
        Tuple<String, String> version = JarUtils.getModVersion(getClass());
        MAIN_VERSION = version.getA();
        FULL_VERSION = version.getB();
        LOGGER.info(CORE, "Version: " + MAIN_VERSION + " (" + FULL_VERSION + ")");

        Initialization.init();
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static String i18n(String key) {
        return MOD_ID + "." + key;
    }

}
