package org.auioc.mcmod.arnicalib.base.math;

public class NumberUtils extends org.apache.commons.lang3.math.NumberUtils {

    public static String toBinaryString(int i, int s) {
        return String.format("0b%" + s + "s", Integer.toBinaryString(i)).replace(" ", "0");
    }

    public static String toOctalString(int i, int s) {
        return String.format("0o%" + s + "s", Integer.toOctalString(i)).replace(" ", "0");
    }

    public static String toHexString(int i, int s) {
        return String.format("0x%" + s + "s", Integer.toHexString(i)).replace(" ", "0");
    }

    public static String toBinaryString(long i, int s) {
        return String.format("0b%" + s + "s", Long.toBinaryString(i)).replace(" ", "0");
    }

    public static String toOctalString(long i, int s) {
        return String.format("0o%" + s + "s", Long.toOctalString(i)).replace(" ", "0");
    }

    public static String toHexString(long i, int s) {
        return String.format("0x%" + s + "s", Long.toHexString(i)).replace(" ", "0");
    }

}
