package org.auioc.mods.arnicalib.utils.java;

import static org.auioc.mods.arnicalib.ArnicaLib.LOGGER;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import org.apache.logging.log4j.Marker;
import org.auioc.mods.arnicalib.api.java.data.Tuple;
import org.auioc.mods.arnicalib.utils.LogUtil;

public interface JarUtils {

    static Marker MARKER = LogUtil.getMarker("JarUtils");

    static Tuple<String, String> DEFAULT_VERSION = new Tuple<String, String>("0", "0");

    static Attributes getManifest(Class<?> clazz) throws MalformedURLException, IOException {
        try {
            String path = clazz.getResource(clazz.getSimpleName() + ".class").toString();
            return new Manifest(new URL(path.substring(0, path.lastIndexOf("!") + 1) + "/META-INF/MANIFEST.MF").openStream()).getMainAttributes();
        } catch (Exception e) {
            LOGGER.warn(MARKER, "MANIFEST.MF of mod " + clazz.getSimpleName() + " could not be read. If this is a development environment you can ignore this message.");
            throw e;
        }
    }

    static Tuple<String, String> getModVersion(final Attributes attrs, String modName) {
        try {
            return new Tuple<String, String>(attrs.getValue("Implementation-Version"), attrs.getValue(modName + "-Version"));
        } catch (Exception e) {
            return DEFAULT_VERSION;
        }
    }

    static Tuple<String, String> getModVersion(Class<?> clazz, String modName) {
        try {
            return getModVersion(getManifest(clazz), modName);
        } catch (Exception e) {
            return DEFAULT_VERSION;
        }
    }

    static Tuple<String, String> getModVersion(Class<?> clazz) {
        return getModVersion(clazz, clazz.getSimpleName());
    }

}
