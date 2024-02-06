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

package org.auioc.mcmod.arnicalib.base.file;

import static org.auioc.mcmod.arnicalib.ArnicaLib.LOGGER;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import org.apache.logging.log4j.Marker;
import org.auioc.mcmod.arnicalib.base.log.LogUtil;

public class JarUtils {

    public static final Marker MARKER = LogUtil.getMarker(JarUtils.class);

    public static Attributes getManifest(Class<?> clazz) throws MalformedURLException, IOException {
        try {
            String path = clazz.getResource(clazz.getSimpleName() + ".class").toString();
            return new Manifest(new URL(path.substring(0, path.lastIndexOf("!") + 1) + "/META-INF/MANIFEST.MF").openStream()).getMainAttributes();
        } catch (Exception e) {
            LOGGER.warn(MARKER, "MANIFEST.MF of " + clazz.getSimpleName() + ".class could not be read. If this is a development environment you can ignore this message.");
            throw e;
        }
    }

}
