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

package org.auioc.mcmod.arnicalib.game.codec;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import net.minecraft.util.ExtraCodecs;
import org.auioc.mcmod.arnicalib.base.function.StringFunction;
import org.auioc.mcmod.arnicalib.base.function.ToStringFunction;

import java.util.Arrays;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class EnumCodec<E extends Enum<E>> implements Codec<E> {

    private final Codec<E> codec;

    public EnumCodec(Codec<E> codec) {
        this.codec = codec;
    }

    @Override
    public <T> DataResult<T> encode(E input, DynamicOps<T> ops, T prefix) {
        return this.codec.encode(input, ops, prefix);
    }

    @Override
    public <T> DataResult<Pair<E, T>> decode(DynamicOps<T> ops, T input) {
        return this.codec.decode(ops, input);
    }

    // ============================================================================================================== //

    public static <E extends Enum<E>> EnumCodec<E> of(ToStringFunction<E> encoder, StringFunction<E> decoder) {
        return new EnumCodec<>(ExtraCodecs.stringResolverCodec(encoder, (s) -> (s != null) ? decoder.apply(s) : null));
    }

    public static <E extends Enum<E>> EnumCodec<E> of(IntFunction<E> decoder) {
        return new EnumCodec<>(ExtraCodecs.idResolverCodec(Enum::ordinal, decoder, -1));
    }

    // ====================================================================== //

    public static <E extends Enum<E>> EnumCodec<E> ordinalWithCache(Class<E> enumClass) {
        var values = enumClass.getEnumConstants();
        return of((i) -> (i >= 0 && i < values.length) ? values[i] : null);
    }

    // ====================================================================== //

    public static <E extends Enum<E>> EnumCodec<E> stringWithMapCache(Supplier<E[]> values, ToStringFunction<E> encoder) {
        var map = Arrays.stream(values.get()).collect(Collectors.toMap(encoder, (e) -> e));
        return of(encoder, map::get);
    }

    public static <E extends Enum<E>> EnumCodec<E> stringWithMapCache(Class<E> enumClass, ToStringFunction<E> encoder) {
        return stringWithMapCache(enumClass::getEnumConstants, encoder);
    }

    public static <E extends Enum<E>> EnumCodec<E> stringWithArrayCache(Supplier<E[]> values, ToStringFunction<E> encoder) {
        var _values = values.get();
        return of(encoder, (s) -> {
            for (E e : _values) if (encoder.apply(e).equals(s)) return e;
            return null;
        });
    }

    public static <E extends Enum<E>> EnumCodec<E> stringWithArrayCache(Class<E> enumClass, ToStringFunction<E> encoder) {
        return stringWithArrayCache(enumClass::getEnumConstants, encoder);
    }

    // ====================================================================== //

    public static <E extends Enum<E>> EnumCodec<E> nameLowercaseWithCache(Class<E> enumClass, boolean large) {
        ToStringFunction<E> encoder = (e) -> e.name().toLowerCase();
        return large ? stringWithMapCache(enumClass, encoder) : stringWithArrayCache(enumClass, encoder);
    }

    public static <E extends Enum<E>> EnumCodec<E> nameLowercaseWithCache(Class<E> enumClass) {
        return nameLowercaseWithCache(enumClass, enumClass.getEnumConstants().length > 8);
    }

}
