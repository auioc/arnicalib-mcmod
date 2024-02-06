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

package org.auioc.mcmod.arnicalib.base.math;

import java.util.function.DoubleUnaryOperator;
import java.util.function.IntUnaryOperator;

public class MathUtil {

    public static int sigma(int n, int m, IntUnaryOperator f) {
        int r = 0;
        for (int i = m, _n = n + 1; i < _n; ++i) r += f.applyAsInt(i);
        return r;
    }

    public static double sigma(int n, int m, DoubleUnaryOperator f) {
        double r = 0.0D;
        for (int i = m, _n = n + 1; i < _n; ++i) r += f.applyAsDouble((double) i);
        return r;
    }

    public static double log(double a, double n) {
        return Math.log(n) / Math.log(a);
    }

    public static double log2(double n) {
        return log(2.0D, n);
    }

}
