package org.auioc.mods.arnicalib.utils.java;

import static org.auioc.mods.arnicalib.ArnicaLib.LOGGER;
import java.util.jar.Attributes;
import org.apache.logging.log4j.Marker;
import org.auioc.mods.arnicalib.api.java.data.Tuple;
import org.auioc.mods.arnicalib.utils.LogUtil;

public class VersionUtils {

    private static final Marker MARKER = LogUtil.getMarker(VersionUtils.class);

    private static final Tuple<String, String> DEFAULT_VERSION_TUPLE = new Tuple<String, String>("0", "0");

    public static String getMainVersion(Attributes attrs) {
        return attrs.getValue("Implementation-Version");
    }

    public static String getFullVersion(Attributes attrs, String modName) {
        String version = attrs.getValue(modName + "-Version");
        String[] p = version.split("-");
        if (p.length > 0 && p[p.length - 1].equals("null")) {
            LOGGER.warn(MARKER, "The revision number of mod " + modName + " is null. If this is a manually built version you can ignore this message.");
        }
        return version;
    }

    public static Tuple<String, String> getModVersion(Attributes attrs, String modName) {
        return new Tuple<String, String>(getMainVersion(attrs), getFullVersion(attrs, modName));
    }

    public static Tuple<String, String> getModVersion(Class<?> clazz, String modName) {
        try {
            return getModVersion(JarUtils.getManifest(clazz), modName);
        } catch (Exception e) {
            return DEFAULT_VERSION_TUPLE;
        }
    }

    public static Tuple<String, String> getModVersion(Class<?> clazz) {
        return getModVersion(clazz, clazz.getSimpleName());
    }

}

