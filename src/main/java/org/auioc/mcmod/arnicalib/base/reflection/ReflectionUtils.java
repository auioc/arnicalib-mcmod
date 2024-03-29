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

package org.auioc.mcmod.arnicalib.base.reflection;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ReflectionUtils {

    public static <V> Optional<V> getFieldValue(Object object, Field field, Class<? extends V> type) {
        Object value;
        try {
            field.setAccessible(true);
            value = field.get(object);
        } catch (Exception e) {
            return Optional.empty();
        }
        if (type.isInstance(value)) {
            return Optional.of(type.cast(value));
        }
        return Optional.empty();
    }

    // ====================================================================== //

    public static <V> Map<String, V> getFieldValues(Class<?> clazz, Object object, Class<? extends V> type) {
        var map = new HashMap<String, V>();
        for (var field : clazz.getFields()) {
            getFieldValue(object, field, type).ifPresent((value) -> map.put(field.getName(), value));
        }
        return map;
    }

    public static <V> Map<String, V> getFieldValues(Object object, Class<? extends V> type) {
        return getFieldValues(object.getClass(), object, type);
    }

    public static <V> Map<String, V> getFieldValues(Class<?> clazz, Class<? extends V> type) {
        return getFieldValues(clazz, null, type);
    }

    // ====================================================================== //

    public static List<Class<?>> getClasses(Object object) {
        Class<?> clazz = (object instanceof Class<?>) ? (Class<?>) object : object.getClass();
        final var classes = new ArrayList<Class<?>>();
        do {
            classes.add(clazz);
            clazz = clazz.getSuperclass();
        } while (clazz != Object.class);
        return classes;
    }

    // ====================================================================== //

    public static List<Field> getFields(Class<?> clazz) {
        final var fields = new ArrayList<Field>();
        try {
            for (var field : clazz.getDeclaredFields()) {
                fields.add(field);
            }
        } catch (SecurityException e) {
        }
        return fields;
    }

    public static Map<Class<?>, List<Field>> getAllFields(Object object) {
        var classes = getClasses(object);
        final var result = new LinkedHashMap<Class<?>, List<Field>>(classes.size(), 1);
        for (var c : classes) {
            result.put(c, getFields(c));
        }
        return result;
    }

    public static List<Field> getAllFieldsFlat(Object object) {
        final var fields = new ArrayList<Field>();
        for (var c : getClasses(object)) {
            fields.addAll(getFields(c));
        }
        return fields;
    }

    // ====================================================================== //

    public static boolean invokeStatic(String className, String methodName) {
        try {
            Class.forName(className).getMethod(methodName).invoke(null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
