package org.auioc.mcmod.arnicalib.game.config;

import java.util.List;
import java.util.function.Consumer;
import net.minecraftforge.common.ForgeConfigSpec.Builder;

public class ConfigUtils {

    public static void pushWithoutPop(Builder specBuilder, String path, Consumer<Builder> subBuilder) {
        specBuilder.push(path);
        subBuilder.accept(specBuilder);
    }

    public static void push(Builder specBuilder, String path, Consumer<Builder> subBuilder) {
        specBuilder.push(path);
        subBuilder.accept(specBuilder);
        specBuilder.pop();
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
