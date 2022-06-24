package org.auioc.mcmod.arnicalib.utils.game;

import cpw.mods.modlauncher.Launcher;
import cpw.mods.modlauncher.api.IEnvironment;

public interface EnvironmentUtils {

    static boolean isDev() {
        return Launcher.INSTANCE
            .environment()
            .getProperty(IEnvironment.Keys.LAUNCHTARGET.get())
            .orElse("missing")
            .contains("dev");
    }

}
