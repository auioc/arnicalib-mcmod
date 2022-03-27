package org.auioc.mods.arnicalib.utils.java;

import static org.auioc.mods.arnicalib.ArnicaLib.LOGGER;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.logging.log4j.Marker;
import org.auioc.mods.arnicalib.utils.LogUtil;

public interface FileUtils {

    static Marker MARKER = LogUtil.getMarker(FileUtils.class);

    static File getOrCreateDirectory(String directoryName) throws IOException {
        var file = new File(directoryName);

        if (file.exists()) {
            return file;
        }

        if (file.mkdirs()) {
            LOGGER.warn(MARKER, "Directory \"" + file + "\" does not exist, create");
            return file;
        }
        throw new IOException("Could not create directory \"" + file + "\"");
    }

    static File getFile(String fileName) throws IOException {
        var file = new File(fileName);

        if (file.getParentFile().exists()) {
            return file;
        }

        if (file.getParentFile().mkdirs()) {
            LOGGER.warn(MARKER, "Parent directory of file \"" + file + "\" does not exist, create");
            return file;
        }
        throw new IOException("Could not create parent directory of file \"" + file + "\"");
    }

    static void writeText(String fileName, String text) throws IOException {
        var file = getFile(fileName);

        if (file.exists()) {
            LOGGER.warn(MARKER, "File \"" + file + "\" already exists, overwrite");
        }

        final var writer = new BufferedWriter(new FileWriter(file, false));
        writer.write(text);
        writer.close();
    }

}
