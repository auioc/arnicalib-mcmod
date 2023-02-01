package org.auioc.mcmod.arnicalib.base.reflection;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ReflectionUtils {

    public static <T> Optional<T> getFieldValue(Object object, Field field, Class<? extends T> type) {
        Object value;
        try {
            value = field.get(object);
        } catch (IllegalAccessException e) {
            return Optional.empty();
        }
        if (type.isInstance(value)) {
            return Optional.of(type.cast(value));
        }
        return Optional.empty();
    }

    public static <T> Map<String, T> getFieldValues(Object object, Class<? extends T> type) {
        var map = new HashMap<String, T>();
        for (var field : object.getClass().getClass().getFields()) {
            getFieldValue(object, field, type).ifPresent((value) -> map.put(field.getName(), value));
        }
        return map;
    }

}
