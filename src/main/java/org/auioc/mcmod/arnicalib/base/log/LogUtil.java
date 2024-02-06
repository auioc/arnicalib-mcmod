/*
 * Copyright (C) 2022-2024 AUIOC.ORG
 *
 * This file is part of ArnicaLib, a mod made for Minecraft.
 *
 * ArnicaLib is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <https://www.gnu.org/licenses/>.
 */

package org.auioc.mcmod.arnicalib.base.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class LogUtil {

    private LogUtil() { }

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
