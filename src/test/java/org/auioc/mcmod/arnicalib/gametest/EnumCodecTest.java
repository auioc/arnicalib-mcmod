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

package org.auioc.mcmod.arnicalib.gametest;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.JsonOps;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestAssertException;
import net.minecraft.gametest.framework.GameTestHelper;
import net.neoforged.neoforge.gametest.GameTestHolder;
import net.neoforged.neoforge.gametest.PrefixGameTestTemplate;
import org.apache.logging.log4j.Marker;
import org.auioc.mcmod.arnicalib.ArnicaLib;
import org.auioc.mcmod.arnicalib.base.log.LogUtil;
import org.auioc.mcmod.arnicalib.game.codec.EnumCodec;

import java.util.function.Function;

import static org.auioc.mcmod.arnicalib.gametest.ArnicaLibGameTest.LOGGER;


@GameTestHolder(ArnicaLib.MOD_ID)
@PrefixGameTestTemplate(value = false)
public class EnumCodecTest {

    private static final Marker MARKER = LogUtil.getMarker("EnumCodec");

    private enum TestEnum {
        A("N:A"), B_S("N:B_S"), c_s("N:c_s"), D_s("N:D_s"), E("N:E");

        private final String n;

        private TestEnum(String n) { this.n = n; }

        public String getCustomStringLookup() { return n; }
    }

    private static <T> void encodeTest(String n, EnumCodec<TestEnum> c, TestEnum t, Function<JsonElement, T> f, T v) {
        boolean r = c.encodeStart(JsonOps.INSTANCE, t).result().map(f).map(v::equals).orElse(false);
        if (r) {
            LOGGER.info(MARKER, "Encode test '{}' passed", n);
        } else {
            LOGGER.error(MARKER, "Encode test '{}' failed: Not equal", n);
            throw new GameTestAssertException(String.format("Encode test '%s' failed", n));
        }
    }

    private static <T> void decodeTest(String n, EnumCodec<TestEnum> c, JsonElement t, T v) {
        boolean r = c.decode(JsonOps.INSTANCE, t).resultOrPartial((e) -> {
            LOGGER.error(MARKER, "Decode test '{}' failed: {}", n, e);
            throw new GameTestAssertException(String.format("Decode test '%s' failed: %s", n, e));
        }).map(Pair::getFirst).map(v::equals).orElse(false);
        if (r) {
            LOGGER.info(MARKER, "Decode test '{}' passed", n);
        } else {
            LOGGER.error(MARKER, "Decode test '{}' failed: - Not equal", n);
            throw new GameTestAssertException(String.format("Encode test '%s' failed: Not equal", n));
        }
    }

    private static final EnumCodec<TestEnum> BY_ORDINAL = EnumCodec.byOrdinal(TestEnum.class);
    private static final EnumCodec<TestEnum> BY_NAME_L = EnumCodec.byNameLowerCase(TestEnum.class);
    private static final EnumCodec<TestEnum> BY_NAME_U = EnumCodec.byNameUpperCase(TestEnum.class);
    private static final EnumCodec<TestEnum> BY_NAME = EnumCodec.byName(TestEnum.class);
    private static final EnumCodec<TestEnum> BY_STRING = EnumCodec.byString(TestEnum.class, TestEnum::getCustomStringLookup);

    @GameTest(template = "empty", timeoutTicks = 10 * 20, batch = "EnumCodec")
    public static void testEncode(GameTestHelper helper) {
        encodeTest("byOrdinal", BY_ORDINAL, TestEnum.E, JsonElement::getAsInt, 4);
        encodeTest("byString", BY_STRING, TestEnum.A, JsonElement::getAsString, "N:A");
        encodeTest("byNameLowerCase", BY_NAME_L, TestEnum.B_S, JsonElement::getAsString, "b_s");
        encodeTest("byNameUpperCase", BY_NAME_U, TestEnum.c_s, JsonElement::getAsString, "C_S");
        encodeTest("byName", BY_NAME, TestEnum.D_s, JsonElement::getAsString, "D_s");
        helper.succeed();
    }

    @GameTest(template = "empty", timeoutTicks = 10 * 20, batch = "EnumCodec")
    public static void testDecode(GameTestHelper helper) {
        decodeTest("byOrdinal", BY_ORDINAL, new JsonPrimitive(4), TestEnum.E);
        decodeTest("byString", BY_STRING, new JsonPrimitive("N:A"), TestEnum.A);
        decodeTest("byNameLowerCase", BY_NAME_L, new JsonPrimitive("b_s"), TestEnum.B_S);
        decodeTest("byNameUpperCase", BY_NAME_U, new JsonPrimitive("C_S"), TestEnum.c_s);
        decodeTest("byName", BY_NAME, new JsonPrimitive("D_s"), TestEnum.A);
        helper.succeed();
    }

}
