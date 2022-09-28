package org.auioc.mcmod.arnicalib.base.word;

import java.util.regex.Pattern;

@SuppressWarnings("deprecation")
public class WordUtils extends org.apache.commons.lang3.text.WordUtils {

    public static String toSnakeCase(String str) {
        return str.replaceAll("[A-Z]", "_$0").toLowerCase();
    }

    public static String toCamelCase(String str) {
        return Pattern.compile("_([a-z])").matcher(str).replaceAll(m -> m.group(1).toUpperCase());
    }

}
