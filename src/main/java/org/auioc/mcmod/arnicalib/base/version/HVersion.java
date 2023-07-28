package org.auioc.mcmod.arnicalib.base.version;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HVersion {

    public static final HVersion DEFAULT = new HVersion("0", "0", "", 0, false, false);

    public final String main;
    public final String full;
    public final String revisionHash;
    public final int buildNumber;
    public final boolean isDirty;
    public final boolean isDev;

    public HVersion(String main, String full, String revisionHash, int buildNumber, boolean isDirty, boolean isDev) {
        this.main = main;
        this.full = full;
        this.revisionHash = revisionHash;
        this.buildNumber = buildNumber;
        this.isDirty = isDirty;
        this.isDev = isDev;
    }

    public HVersion(String main, String full) {
        this(
            main, full,
            getRevisionHash(full),
            getBuildNumber(full),
            full.contains("-dirty"),
            full.contains("-dev-")
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof HVersion other) {
            return this.main.equals(other.main) && this.full.equals(other.full);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31 * this.main.hashCode() + this.full.hashCode();
    }

    // ====================================================================== //

    private static final Pattern PATTERN_REVISION_HASH = Pattern.compile("(?<=rev\\.)[0-9a-fA-F]{7}");
    private static final Pattern PATTERN_BUILD_NUMBER = Pattern.compile("(?<=build\\.)\\d+");

    private static String getRevisionHash(String fullVersion) {
        Matcher matcher = PATTERN_REVISION_HASH.matcher(fullVersion);
        return (matcher.find()) ? matcher.group().toLowerCase() : "";
    }

    private static int getBuildNumber(String fullVersion) {
        Matcher matcher = PATTERN_BUILD_NUMBER.matcher(fullVersion);
        return (matcher.find()) ? Integer.parseInt(matcher.group()) : 0;
    }

}
