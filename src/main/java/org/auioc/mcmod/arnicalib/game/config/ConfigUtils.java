package org.auioc.mcmod.arnicalib.game.config;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class ConfigUtils {

    public static final Joiner LINE_JOINER = Joiner.on("\n");
    public static final Joiner DOT_JOINER = Joiner.on(".");
    public static final Splitter DOT_SPLITTER = Splitter.on(".");

    public static List<String> split(String path) {
        return Lists.newArrayList(DOT_SPLITTER.split(path));
    }


    public static void pushWithoutPop(Builder specBuilder, String path, Consumer<Builder> subBuilder) {
        specBuilder.push(path);
        subBuilder.accept(specBuilder);
    }

    public static void push(Builder specBuilder, String path, Consumer<Builder> subBuilder) {
        specBuilder.push(path);
        subBuilder.accept(specBuilder);
        specBuilder.pop();
    }


    public static <T> ConfigValue<List<? extends T>> defineList(Builder specBuilder, String path, Supplier<List<? extends T>> defaultSupplier, Predicate<Object> elementValidator) {
        return specBuilder.defineListAllowEmpty(split(path), defaultSupplier, elementValidator);
    }

    public static <T> ConfigValue<List<? extends T>> defineList(Builder specBuilder, String path, Predicate<Object> elementValidator) {
        return defineList(specBuilder, path, List::of, elementValidator);
    }

    public static ConfigValue<List<? extends String>> defineStringList(Builder specBuilder, String path) {
        return defineList(specBuilder, path, (o) -> o instanceof String);
    }

    public static ConfigValue<List<? extends String>> defineStringList(Builder specBuilder, String path, List<String> allowedValues) {
        return defineList(specBuilder, path, (o) -> o instanceof String && allowedValues.contains(o));
    }

    public static ConfigValue<List<? extends String>> defineStringList(Builder specBuilder, String path, Supplier<List<? extends String>> defaultSupplier, Predicate<String> stringValidator) {
        return defineList(specBuilder, path, defaultSupplier, (o) -> (o instanceof String) && stringValidator.test((String) o));
    }

    public static ConfigValue<List<? extends String>> defineStringList(Builder specBuilder, String path, Supplier<List<? extends String>> defaultSupplier) {
        return defineStringList(specBuilder, path, defaultSupplier, (o) -> true);
    }


    public static boolean validateListSize(Object o, int size) {
        if (o instanceof List<?> l) {
            return l.size() == size;
        }
        return false;
    }

    public static <T> boolean validateListElements(Object o, Class<T> type) {
        if (o instanceof List<?> l) {
            for (var e : l) {
                if (!type.isInstance(e)) return false;
            }
            return true;
        }
        return false;
    }

    public static <T> boolean validateListElements(Object o, Class<T> type, List<T> allowed) {
        if (o instanceof List<?> l) {
            for (var e : l) {
                if (type.isInstance(e) && allowed.contains(type.cast(o))) {
                    continue;
                } else {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static <T> boolean validateStringList(Object o, List<String> allowed) {
        return validateListElements(o, String.class, allowed);
    }

}
