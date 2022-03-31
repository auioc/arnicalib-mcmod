package org.auioc.mods.arnicalib.utils.java;

import static org.auioc.mods.arnicalib.ArnicaLib.LOGGER;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import org.apache.logging.log4j.Marker;
import org.auioc.mods.arnicalib.utils.LogUtil;

public class FileUtils extends org.apache.commons.io.FileUtils {

    private static final Marker MARKER = LogUtil.getMarker(FileUtils.class);

    public static File getOrCreateDirectory(String directoryName) throws IOException {
        var file = new File(directoryName);

        if (file.exists()) return file;

        if (file.mkdirs()) {
            LOGGER.warn(MARKER, "Directory \"" + file + "\" does not exist, create");
            return file;
        }
        throw new IOException("Could not create directory \"" + file + "\"");
    }

    public static File getFile(String fileName) throws IOException {
        var file = new File(fileName);

        if (file.getParentFile().exists()) return file;

        if (file.getParentFile().mkdirs()) {
            LOGGER.warn(MARKER, "Parent directory of file \"" + file + "\" does not exist, create");
            return file;
        }
        throw new IOException("Could not create parent directory of file \"" + file + "\"");
    }


    /**
     * Writes a String to a file creating the file if it does not exist using the UTF-8 charset.
     *
     * @param file the file to write
     * @param data the content to write to the file
     * @throws IOException in case of an I/O error
     * @since 5.1.4
     */
    public static void writeStringToFile(final File file, final String data) throws IOException {
        writeStringToFile(file, data, Charset.forName("utf8"), false);
    }

    /* ============================================================================================================== */
    // #region Deprecated

    @Deprecated
    private FileUtils() {}

    @Deprecated(since = "5.1.4")
    public static void writeText(String fileName, String text) throws IOException {
        var file = getFile(fileName);

        if (file.exists()) LOGGER.warn(MARKER, "File \"" + file + "\" already exists, overwrite");

        final var writer = new BufferedWriter(new FileWriter(file, false));
        writer.write(text);
        writer.close();
    }

    // #endregion Deprecated

}
