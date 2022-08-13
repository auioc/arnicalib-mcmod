package org.auioc.mcmod.arnicalib.utils.game;

import java.util.Locale;
import cpw.mods.modlauncher.Launcher;
import cpw.mods.modlauncher.api.IEnvironment;

public interface EnvironmentUtils {

    boolean IS_DEV = isDev();

    static boolean isDev() {
        return Launcher.INSTANCE
            .environment()
            .getProperty(IEnvironment.Keys.LAUNCHTARGET.get())
            .orElse("missing")
            .toLowerCase(Locale.ROOT)
            .contains("dev");
    }

}
