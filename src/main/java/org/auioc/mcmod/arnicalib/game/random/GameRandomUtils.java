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
