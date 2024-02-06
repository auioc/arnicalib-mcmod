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

package org.auioc.mcmod.arnicalib.game.world.position;

import java.util.function.DoubleUnaryOperator;

public enum SpeedUnit {

    METRES_PER_TICK("metres per tick", "m/t", (v) -> v), //
    METRES_PER_SECOND("metres per second", "m/s", (v) -> v * 20.0D), //
    KILOMETRES_PER_HOUR("kilometres per hour", "km/h", (v) -> v * 72.0D);

    private final String name;
    private final String symbol;
    private final DoubleUnaryOperator converter;

    private SpeedUnit(String name, String symbol, DoubleUnaryOperator converter) {
        this.name = name;
        this.symbol = symbol;
        this.converter = converter;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public double convertFrom(double metresPreTick) {
        return converter.applyAsDouble(metresPreTick);
    }

}
