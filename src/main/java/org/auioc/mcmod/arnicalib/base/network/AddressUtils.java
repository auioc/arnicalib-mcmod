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

package org.auioc.mcmod.arnicalib.base.network;

import org.apache.logging.log4j.Marker;
import org.auioc.mcmod.arnicalib.base.log.LogUtil;

import javax.annotation.Nullable;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.auioc.mcmod.arnicalib.ArnicaLib.LOGGER;

public interface AddressUtils {

    Marker MARKER = LogUtil.getMarker(AddressUtils.class);

    @Nullable
    static InetAddress toInetAddress(String addr) {
        try {
            return InetAddress.getByName(addr);
        } catch (UnknownHostException e) {
            LOGGER.error(MARKER, "", e);
            return null;
        }
    }

    static boolean isLocalAddress(InetAddress addr) {
        return addr.isLoopbackAddress() || addr.isAnyLocalAddress() || addr.isLinkLocalAddress();
    }

    static boolean isLocalAddress(String addr) {
        if (addr.contains("local:E:")) {
            return true;
        }
        return isLocalAddress(toInetAddress(addr));

    }

    static boolean isLanAddress(InetAddress addr) {
        return addr.isSiteLocalAddress();
    }

    static boolean isLanAddress(String addr) {
        if (addr.contains("local:E:")) {
            return true;
        }
        return isLanAddress(toInetAddress(addr));
    }

}
