package org.auioc.mcmod.arnicalib.utils.java;

import static org.auioc.mcmod.arnicalib.ArnicaLib.LOGGER;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import org.apache.logging.log4j.Marker;
import org.auioc.mcmod.arnicalib.utils.LogUtil;

public interface JarUtils {

    static Marker MARKER = LogUtil.getMarker(JarUtils.class);

    static Attributes getManifest(Class<?> clazz) throws MalformedURLException, IOException {
        try {
            String path = clazz.getResource(clazz.getSimpleName() + ".class").toString();
            return new Manifest(new URL(path.substring(0, path.lastIndexOf("!") + 1) + "/META-INF/MANIFEST.MF").openStream()).getMainAttributes();
        } catch (Exception e) {
            LOGGER.warn(MARKER, "MANIFEST.MF of mod " + clazz.getSimpleName() + " could not be read. If this is a development environment you can ignore this message.");
            throw e;
        }
    }

}
