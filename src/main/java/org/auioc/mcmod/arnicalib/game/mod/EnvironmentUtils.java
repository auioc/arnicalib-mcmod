package org.auioc.mcmod.arnicalib.game.mod;

import java.util.Locale;
import cpw.mods.modlauncher.Launcher;
import cpw.mods.modlauncher.api.IEnvironment;

public class EnvironmentUtils {

    public static final boolean IS_DEV = isDev();
    public static final boolean IS_DATA = isData();

    public static String getLaunchTarget() {
        return Launcher.INSTANCE
            .environment()
            .getProperty(IEnvironment.Keys.LAUNCHTARGET.get())
            .orElse("MISSING");
    }

    public static boolean isDev() {
        return getLaunchTarget().toLowerCase(Locale.ROOT).contains("dev");
    }

    public static boolean isData() {
        return getLaunchTarget().toLowerCase(Locale.ROOT).startsWith("forgedata");
    }

}
