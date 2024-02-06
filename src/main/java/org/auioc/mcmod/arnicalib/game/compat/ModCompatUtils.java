/*
 * Copyright (C) 2022-2024 AUIOC.ORG
 *
 * This file is part of ArnicaLib, a mod made for Minecraft.
 *
 * ArnicaLib is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <https://www.gnu.org/licenses/>.
 */

package org.auioc.mcmod.arnicalib.game.compat;

import net.neoforged.fml.ModList;

public class ModCompatUtils {

    public static boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    public static boolean isClass(String clazz, boolean safe) {
        try {
            if (safe) {
                Class.forName(clazz, false, ModCompatUtils.class.getClassLoader());
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
