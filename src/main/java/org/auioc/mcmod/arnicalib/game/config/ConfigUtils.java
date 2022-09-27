package org.auioc.mcmod.arnicalib.game.config;

import java.util.List;
import java.util.function.Consumer;
import net.minecraftforge.common.ForgeConfigSpec.Builder;

public interface ConfigUtils {

    static void pushWithoutPop(Builder specBuilder, String path, Consumer<Builder> subBuilder) {
        specBuilder.push(path);
        subBuilder.accept(specBuilder);
    }

    static void push(Builder specBuilder, String path, Consumer<Builder> subBuilder) {
        specBuilder.push(path);
        subBuilder.accept(specBuilder);
        specBuilder.pop();
    }


    static boolean validateListSize(Object obj, int size) {
        if (!(obj instanceof List)) return false;
        return (((List<?>) obj).size() == size) ? true : false;
    }

    static boolean validateListElements(Object obj, Class<?> clazz) {
        if (!(obj instanceof List)) return false;

        for (Object e : (List<?>) obj) {
            if (e.getClass() != clazz) return false;
        }

        return true;
    }

}
