/*
 * Copyright (C) 2025 AUIOC.ORG
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

package org.auioc.mcmod.arnicalib.game.critereon;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import org.auioc.mcmod.arnicalib.game.codec.EnumCodec;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @since 7.0.0
 */
public record BiomePredicate(Optional<ClimatePredicate> climate) implements Predicate<Biome> {

    public static final Codec<BiomePredicate> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        ClimatePredicate.CODEC.optionalFieldOf("climate").forGetter(o -> o.climate)
    ).apply(instance, BiomePredicate::new));


    public boolean matches(Biome biome) {
        if (climate.isPresent() && !climate.get().matches(biome.getModifiedClimateSettings())) {
            return false;
        }
        return true;
    }

    public boolean matches(Holder<Biome> biome) {
        return matches(biome.value());
    }

    @Override
    public boolean test(Biome biome) { return matches(biome); }

    // ============================================================================================================== //

    public static BiomePredicate withClimate(Consumer<ClimatePredicate.Builder> consumer) {
        var climate = ClimatePredicate.builder();
        consumer.accept(climate);
        return new BiomePredicate(Optional.of(climate.build()));
    }

    // ============================================================================================================== //

    public record ClimatePredicate(
        Optional<Boolean> hasPrecipitation,
        MinMaxBounds.Doubles temperature,
        Optional<Biome.TemperatureModifier> temperatureModifier,
        MinMaxBounds.Doubles downfall
    ) {

        public static final Codec<ClimatePredicate> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.BOOL.optionalFieldOf("has_precipitation").forGetter(o -> o.hasPrecipitation),
            MinMaxBounds.Doubles.CODEC.optionalFieldOf("temperature", MinMaxBounds.Doubles.ANY).forGetter(o -> o.temperature),
            EnumCodec.byString(Biome.TemperatureModifier.class, e -> e.getName()).optionalFieldOf("temperature_modifier").forGetter(o -> o.temperatureModifier),
            MinMaxBounds.Doubles.CODEC.optionalFieldOf("downfall", MinMaxBounds.Doubles.ANY).forGetter(o -> o.downfall)
        ).apply(instance, ClimatePredicate::new));

        public boolean matches(Biome.ClimateSettings climate) {
            if (hasPrecipitation.isPresent() && hasPrecipitation.get() != climate.hasPrecipitation()) {
                return false;
            }
            if (!temperature.matches(climate.temperature())) {
                return false;
            }
            if (temperatureModifier.isPresent() && temperatureModifier.get() != climate.temperatureModifier()) {
                return false;
            }
            if (!downfall.matches(climate.downfall())) {
                return false;
            }
            return true;
        }

        public static Builder builder() {
            return new Builder();
        }

        // ========================================================================================================== //

        public static class Builder {

            private Optional<Boolean> hasPrecipitation = Optional.empty();
            private MinMaxBounds.Doubles temperature = MinMaxBounds.Doubles.ANY;
            private Optional<Biome.TemperatureModifier> temperatureModifier = Optional.empty();
            private MinMaxBounds.Doubles downfall = MinMaxBounds.Doubles.ANY;

            private Builder() { }

            public Builder hasPrecipitation() {
                this.hasPrecipitation = Optional.of(true);
                return this;
            }

            public Builder noPrecipitation() {
                this.hasPrecipitation = Optional.of(false);
                return this;
            }

            public Builder withTemperatureModifier(Biome.TemperatureModifier modifier) {
                this.temperatureModifier = Optional.of(modifier);
                return this;
            }

            public Builder temperature(MinMaxBounds.Doubles temperature) {
                this.temperature = temperature;
                return this;
            }

            public Builder downfall(MinMaxBounds.Doubles downfall) {
                this.temperature = downfall;
                return this;
            }

            public ClimatePredicate build() {
                return new ClimatePredicate(hasPrecipitation, temperature, temperatureModifier, downfall);
            }

        }

    }

}
