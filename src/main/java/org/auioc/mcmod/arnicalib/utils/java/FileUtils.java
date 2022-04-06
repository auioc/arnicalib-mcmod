package org.auioc.mcmod.arnicalib.utils.java;

import static org.auioc.mcmod.arnicalib.ArnicaLib.LOGGER;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import org.apache.logging.log4j.Marker;
import org.auioc.mcmod.arnicalib.utils.LogUtil;

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
     * Reads the contents of a file into a String using the UTF-8 charset.
     * The file is always closed.
     *
     * @param file the file to read, must not be {@code null}
     * @return the file contents, never {@code null}
     * @throws NullPointerException  if file is {@code null}.
     * @throws FileNotFoundException if the file does not exist, is a directory rather than a regular file, or for some other reason cannot be opened for reading.
     * @throws IOException           if an I/O error occurs.
     * @see org.apache.commons.io.FileUtils#readFileToString(File, Charset)
     * @since 5.1.4
     */
    public static String readFileToString(final File file) throws IOException {
        return readFileToString(file, Charset.forName("utf8"));
    }

    /**
     * Writes a String to a file creating the file if it does not exist using the UTF-8 charset.
     *
     * @param file the file to write
     * @param data the content to write to the file
     * @throws IOException in case of an I/O error
     * @see org.apache.commons.io.FileUtils#writeStringToFile(File, String, Charset, boolean)
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
