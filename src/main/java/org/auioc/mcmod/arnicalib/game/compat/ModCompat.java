package org.auioc.mcmod.arnicalib.game.compat;

import net.minecraftforge.fml.ModList;

public class ModCompat {

    public static boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    public static boolean isClass(String clazz, boolean safe) {
        try {
            if (safe) {
                Class.forName(clazz, false, ModCompat.class.getClassLoader());
            } else {
                Class.forName(clazz);
            }
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean isClass(String clazz) {
        return isClass(clazz, true);
    }

}
