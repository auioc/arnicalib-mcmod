package org.auioc.mcmod.arnicalib.game.world.position;

import org.auioc.mcmod.arnicalib.base.function.DoubleToDoubleFunction;

public enum SpeedUnit {

    METRES_PER_TICK("metres per tick", "m/t", (v) -> v), //
    METRES_PER_SECOND("metres per second", "m/s", (v) -> v * 20.0D), //
    KILOMETRES_PER_HOUR("kilometres per hour", "km/h", (v) -> v * 72.0D);

    private final String name;
    private final String symbol;
    private final DoubleToDoubleFunction converter;

    private SpeedUnit(String name, String symbol, DoubleToDoubleFunction converter) {
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
