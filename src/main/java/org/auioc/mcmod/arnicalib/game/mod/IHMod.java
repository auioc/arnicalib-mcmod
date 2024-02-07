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

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.auioc.mcmod.arnicalib.base.log.LogUtil;

public interface IHMod {

    Marker MARKER = LogUtil.getMarker("CORE");

    static void validateVersion(BuildInfo buildInfo, Logger logger) {
        logger.info(MARKER, "Version: " + buildInfo.version() + " (" + buildInfo + ")");
        if (!buildInfo.isRelease()) { logger.warn(MARKER, "Mod is a development version"); }
        if (buildInfo.isDirty()) { logger.warn(MARKER, "Mod is a dirty build"); }
    }

}
