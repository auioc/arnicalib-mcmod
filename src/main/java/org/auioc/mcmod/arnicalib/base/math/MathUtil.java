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

    // ====================================================================== //

    @SuppressWarnings("removal")
    @Deprecated(since = "5.7.1", forRemoval = true)
    public static int sigma(int n, int m, org.auioc.mcmod.arnicalib.base.function.IntToIntFunction f) {
        return sigma(n, m, (IntUnaryOperator) (int i) -> f.applyAsInt(i));
    }

    @SuppressWarnings("removal")
    @Deprecated(since = "5.7.1", forRemoval = true)
    public static double sigma(int n, int m, org.auioc.mcmod.arnicalib.base.function.DoubleToDoubleFunction f) {
        return sigma(n, m, (DoubleUnaryOperator) (double i) -> f.applyAsDouble(i));
    }

}
