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

package org.auioc.mcmod.arnicalib.game.mod;

import static org.auioc.mcmod.arnicalib.game.mod.IHMod.COREMARKER;

import java.util.jar.Attributes;

import org.apache.logging.log4j.Logger;
import org.auioc.mcmod.arnicalib.base.file.JarUtils;
import org.auioc.mcmod.arnicalib.base.version.HVersion;

public class HModUtil {

    public static HVersion getVersion(Class<?> clazz, Logger logger) {
        var ver = getVersion(clazz);
        logger.info(COREMARKER, "Version: " + ver.main + " (" + ver.full + ")");
        if (ver.equals(HVersion.DEFAULT)) {
            logger.warn(COREMARKER, "Zero mod version detected. If this is a development environment you can ignore this message.");
        } else {
            if (ver.revisionHash == "") logger.warn(COREMARKER, "Invalid revision hash '" + ver.revisionHash + "'");
            if (ver.buildNumber <= 0) logger.warn(COREMARKER, "Invalid build number '" + ver.buildNumber + "'");
            if (ver.isDirty) logger.warn(COREMARKER, "This mod is a development version");
            if (ver.isDirty) logger.warn(COREMARKER, "This mod is a dirty build");
        }
        return ver;
    }

    public static HVersion getVersion(Class<?> clazz) {
        try {
            return getVersion(JarUtils.getManifest(clazz));
        } catch (Exception e) {
            return HVersion.DEFAULT;
        }
    }

    public static HVersion getVersion(Attributes attrs) {
        return new HVersion(attrs.getValue("Implementation-Version"), attrs.getValue("HiMod-Full-Version"));
    }

}
