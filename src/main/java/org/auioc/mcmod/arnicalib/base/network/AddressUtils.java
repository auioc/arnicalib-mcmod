package org.auioc.mcmod.arnicalib.base.network;

import static org.auioc.mcmod.arnicalib.ArnicaLib.LOGGER;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.annotation.Nullable;
import org.apache.logging.log4j.Marker;
import org.auioc.mcmod.arnicalib.base.log.LogUtil;

public interface AddressUtils {

    static Marker MARKER = LogUtil.getMarker(AddressUtils.class);

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
