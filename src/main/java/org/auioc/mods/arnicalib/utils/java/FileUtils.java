package org.auioc.mods.arnicalib.utils.java;

import static org.auioc.mods.arnicalib.ArnicaLib.LOGGER;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.logging.log4j.Marker;
import org.auioc.mods.arnicalib.utils.LogUtil;

public class FileUtils extends org.apache.commons.io.FileUtils {

    private static final Marker MARKER = LogUtil.getMarker(FileUtils.class);

    public static File getOrCreateDirectory(String directoryName) throws IOException {
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

    public static File getFile(String fileName) throws IOException {
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

    /* ============================================================================================================== */
    // #region Deprecated

    @Deprecated
    private FileUtils() {}

    @Deprecated(since = "5.1.4")
    public static void writeText(String fileName, String text) throws IOException {
        var file = getFile(fileName);

        if (file.exists()) {
            LOGGER.warn(MARKER, "File \"" + file + "\" already exists, overwrite");
        }

        final var writer = new BufferedWriter(new FileWriter(file, false));
        writer.write(text);
        writer.close();
    }

    // #endregion Deprecated

}
