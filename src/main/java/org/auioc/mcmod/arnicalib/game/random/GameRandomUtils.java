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

package org.auioc.mcmod.arnicalib.game.random;

import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.XoroshiroRandomSource;
import org.auioc.mcmod.arnicalib.base.random.RandomUtils;
import org.auioc.mcmod.arnicalib.base.validate.Validate;

public class GameRandomUtils extends RandomUtils {

    public static RandomSource create() {
        return new XoroshiroRandomSource(RandomUtils.uniqueSeed());
    }

    public static double nextDouble(RandomSource random, double bound) {
        double r = random.nextDouble();
        r = r * bound;
        if (r >= bound) r = Math.nextDown(bound);
        return r;
    }

    public static int nextSignum(RandomSource random) {
        return random.nextBoolean() ? 1 : -1;
    }

    public static int offset(int bound, RandomSource random) {
        return random.nextInt(bound) * nextSignum(random);
    }

    public static double offset(double bound, RandomSource random) {
        return nextDouble(random, bound) * nextSignum(random);
    }

    public static boolean percentageChance(int chance, RandomSource random) {
        Validate.isInCloseInterval(0, 100, chance);
        return random.nextInt(100) < chance;
    }

    public static boolean fractionChance(int numerator, int denominator, RandomSource random) {
        Validate.isFractionChance(numerator, denominator);
        return random.nextInt(denominator) < numerator;
    }


}
