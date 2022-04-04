package org.auioc.mods.arnicalib.utils.java;

import static org.auioc.mods.arnicalib.ArnicaLib.LOGGER;
import java.util.jar.Attributes;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.Marker;
import org.auioc.mods.arnicalib.utils.LogUtil;

public class VersionUtils {

    private VersionUtils() {}

    private static final Marker MARKER = LogUtil.getMarker(VersionUtils.class);

    private static final Pair<String, String> DEFAULT_VERSION_TUPLE = Pair.of("0", "0");
    private static final Pattern REVISION_NUMBER_PATTERN = Pattern.compile("(?<=rev\\.)[0-9a-fA-F]{7}");
    private static final Pattern BUILD_NUMBER_PATTERN = Pattern.compile("(?<=build\\.)\\d+");

    public static String getMainVersion(Attributes attrs) {
        return attrs.getValue("Implementation-Version");
    }

    public static String getFullVersion(Attributes attrs, String modName) {
        String version = attrs.getValue(modName + "-Version");
        if (getRevisionNumber(version).isEmpty()) {
            LOGGER.warn(MARKER, "The revision number of mod " + modName + " is null. If this is a manually built version you can ignore this message.");
        }
        return version;
    }

    public static Pair<String, String> getModVersion(Attributes attrs, String modName) {
        return Pair.of(getMainVersion(attrs), getFullVersion(attrs, modName));
    }

    public static Pair<String, String> getModVersion(Class<?> clazz, String modName) {
        try {
            return getModVersion(JarUtils.getManifest(clazz), modName);
        } catch (Exception e) {
            return DEFAULT_VERSION_TUPLE;
        }
    }

    public static Pair<String, String> getModVersion(Class<?> clazz) {
        return getModVersion(clazz, clazz.getSimpleName());
    }

    public static String getRevisionNumber(String fullVersion) {
        Matcher matcher = REVISION_NUMBER_PATTERN.matcher(fullVersion);
        if (matcher.find()) {
            return matcher.group();
        }
        return "";
    }

    public static int getBuildNumber(String fullVersion) {
        Matcher matcher = BUILD_NUMBER_PATTERN.matcher(fullVersion);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group());
        }
        return 0;
    }

}

