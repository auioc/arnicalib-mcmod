package org.auioc.mcmod.arnicalib.game.random;

import org.auioc.mcmod.arnicalib.base.random.RandomUtils;
import net.minecraft.util.RandomSource;

public class GameRandomUtils extends RandomUtils {

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

}
