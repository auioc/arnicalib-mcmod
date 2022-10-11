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


    public static boolean validateListSize(Object obj, int size) {
        if (!(obj instanceof List)) return false;
        return (((List<?>) obj).size() == size) ? true : false;
    }

    public static boolean validateListElements(Object obj, Class<?> clazz) {
        if (!(obj instanceof List)) return false;

        for (Object e : (List<?>) obj) {
            if (e.getClass() != clazz) return false;
        }

        return true;
    }

}
