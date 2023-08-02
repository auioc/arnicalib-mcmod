package org.auioc.mcmod.arnicalib.game.codec;

import java.util.function.Function;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;

public class EnumCodec<E extends Enum<E>> implements Codec<E> {

    private final Function<E, String> encoder;
    private final Function<String, E> decoder;

    public EnumCodec(Function<E, String> encoder, Function<String, E> decoder) {
        this.encoder = encoder;
        this.decoder = decoder;
    }

    public EnumCodec(Class<E> enumClass) {
        this(
            (input) -> input.name().toLowerCase(),
            (name) -> Enum.valueOf(enumClass, name.toUpperCase())
        );
    }

    @Override
    public <T> DataResult<T> encode(E input, DynamicOps<T> ops, T prefix) {
        return ops.mergeToPrimitive(prefix, ops.createString(encoder.apply(input)));
    }

    @Override
    public <T> DataResult<Pair<E, T>> decode(DynamicOps<T> ops, T input) {
        return ops.getStringValue(input).map(decoder).map((result) -> Pair.of(result, ops.empty()));
    }

}
