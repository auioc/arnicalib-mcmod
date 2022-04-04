package org.auioc.mods.arnicalib.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class LogUtil {

    private LogUtil() {}

    private static StackTraceElement getCaller() {
        StackTraceElement[] callStack = Thread.currentThread().getStackTrace();

        StackTraceElement caller = null;

        String logClassName = LogUtil.class.getName();

        int i = 0;
        for (int len = callStack.length; i < len; i++) {
            if (logClassName.equals(callStack[i].getClassName())) {
                break;
            }
        }

        caller = callStack[i + 3];
        return caller;
    }

    public static Logger getLoggerByCaller() {
        return LogManager.getLogger(getCaller().getClassName());
    }

    public static Logger getLogger(String name) {
        return LogManager.getLogger(name);
    }

    public static Marker getMarker(String marker) {
        return MarkerManager.getMarker(marker);
    }

    public static Marker getMarker(Class<?> clazz) {
        return MarkerManager.getMarker(clazz.getSimpleName());
    }


    public static void trace(Object msg) {
        getLoggerByCaller().trace(msg);
    }

    public static void debug(Object msg) {
        getLoggerByCaller().debug(msg);
    }

    public static void info(Object msg) {
        getLoggerByCaller().info(msg);
    }

    public static void warn(Object msg) {
        getLoggerByCaller().warn(msg);
    }

    public static void error(Object msg) {
        getLoggerByCaller().error(msg);
    }

    public static void error(Object msg, Throwable t) {
        getLoggerByCaller().error(msg, t);
    }

}
