package org.auioc.mcmod.arnicalib.game.mod;

import java.util.Locale;
import cpw.mods.modlauncher.Launcher;
import cpw.mods.modlauncher.api.IEnvironment;

public class EnvironmentUtils {

    public static final boolean IS_DEV = isDev();

    public static boolean isDev() {
        return Launcher.INSTANCE
            .environment()
            .getProperty(IEnvironment.Keys.LAUNCHTARGET.get())
            .orElse("missing")
            .toLowerCase(Locale.ROOT)
            .contains("dev");
    }

}
