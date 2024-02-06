package org.auioc.mcmod.arnicalib;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.apache.logging.log4j.Logger;
import org.auioc.mcmod.arnicalib.base.log.LogUtil;
import org.auioc.mcmod.arnicalib.base.version.HVersion;
import org.auioc.mcmod.arnicalib.game.mod.HModUtil;
import org.auioc.mcmod.arnicalib.game.mod.IHMod;
import org.auioc.mcmod.arnicalib.mod.Initialization;

@Mod(ArnicaLib.MOD_ID)
public final class ArnicaLib implements IHMod {

    public static final String MOD_ID = "arnicalib";
    public static final String MOD_NAME = "ArnicaLib";
    public static final Logger LOGGER = LogUtil.getLogger(MOD_NAME);
    public static final HVersion VERSION = HModUtil.getVersion(ArnicaLib.class, LOGGER);

    private static IEventBus modEventBus;

    public ArnicaLib(IEventBus modEventBus) {
        ArnicaLib.modEventBus = modEventBus;
        Initialization.init();
    }

    public static IEventBus getModEventBus() {
        return modEventBus;
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static String i18n(String key) {
        return MOD_ID + "." + key;
    }

}
