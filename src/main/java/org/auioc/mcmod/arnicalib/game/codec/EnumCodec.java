package org.auioc.mcmod.arnicalib.game.codec;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;

public class EnumCodec<E extends Enum<E>> implements Codec<E> {

    private final Class<E> enumClass;

    public EnumCodec(Class<E> enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    public <T> DataResult<T> encode(E input, DynamicOps<T> ops, T prefix) {
        return ops.mergeToPrimitive(prefix, ops.createString(input.name()));
    }

    @Override
    public <T> DataResult<Pair<E, T>> decode(DynamicOps<T> ops, T input) {
        return ops.getStringValue(input)
            .map((name) -> Pair.of(Enum.valueOf(this.enumClass, name), ops.empty()));
    }

}
