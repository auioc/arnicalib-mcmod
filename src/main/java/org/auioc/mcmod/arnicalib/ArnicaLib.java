package org.auioc.mcmod.arnicalib;

import org.apache.logging.log4j.Logger;
import org.auioc.mcmod.arnicalib.base.log.LogUtil;
import org.auioc.mcmod.arnicalib.game.mod.HModUtil;
import org.auioc.mcmod.arnicalib.game.mod.IHMod;
import org.auioc.mcmod.arnicalib.mod.Initialization;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;

@Mod(ArnicaLib.MOD_ID)
public final class ArnicaLib implements IHMod {

    public static final String MOD_ID = "arnicalib";
    public static final String MOD_NAME = "ArnicaLib";
    public static final String MAIN_VERSION;
    public static final String FULL_VERSION;

    public static final Logger LOGGER = LogUtil.getLogger(MOD_NAME);

    public ArnicaLib() {
        Initialization.init();
    }

    static {
        final var version = HModUtil.getVersion(ArnicaLib.class, LOGGER);
        MAIN_VERSION = version.main;
        FULL_VERSION = version.full;
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static String i18n(String key) {
        return MOD_ID + "." + key;
    }

}
