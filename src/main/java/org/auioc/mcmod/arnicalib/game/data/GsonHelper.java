package org.auioc.mcmod.arnicalib.game.data;

import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class GsonHelper extends net.minecraft.util.GsonHelper {

    public static <T> T[] convertToArray(JsonArray json, IntFunction<T[]> generator, Function<JsonElement, T> converter) {
        final var array = generator.apply(json.size());
        int i = 0;
        for (var jsonElement : json) {
            array[i] = converter.apply(jsonElement);
            i++;
        }
        return array;
    }

    public static <T> T[] getAsArray(JsonObject json, String key, IntFunction<T[]> generator, Function<JsonElement, T> converter) {
        return convertToArray(getAsJsonArray(json, key), generator, converter);
    }

    public static <T> T[] getAsArray(JsonObject json, String key, IntFunction<T[]> generator, Function<JsonElement, T> converter, Supplier<T[]> fallback) {
        return (json.has(key)) ? getAsArray(json, key, generator, converter) : fallback.get();
    }

    public static <T> T[] getAsArray(JsonObject json, String key, IntFunction<T[]> generator, Function<JsonElement, T> converter, T[] fallback) {
        return getAsArray(json, key, generator, converter, () -> fallback);
    }

    public static <T> T[] getAsArray(JsonObject json, String key, IntFunction<T[]> generator, Function<JsonElement, T> converter, boolean fallback) {
        return (fallback) ? getAsArray(json, key, generator, converter, () -> generator.apply(0)) : getAsArray(json, key, generator, converter);
    }

}
