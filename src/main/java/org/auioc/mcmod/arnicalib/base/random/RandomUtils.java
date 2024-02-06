package org.auioc.mcmod.arnicalib.base.random;

import org.auioc.mcmod.arnicalib.base.validate.Validate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class RandomUtils {

    private static final AtomicLong seedUniquifier = new AtomicLong(9311145141919810L);

    private static long seedUniquifier() {
        for (; ; ) {
            long current = seedUniquifier.get();
            long next = current * 1181783497276652981L;
            if (seedUniquifier.compareAndSet(current, next)) {
                return next;
            }
        }
    }

    public static long uniqueSeed() {
        return seedUniquifier() ^ System.nanoTime();
    }

    // ============================================================================================================== //

    public static final Random RANDOM = new Random();

    // ============================================================================================================== //
    // #region PickFromCollection

    public static <T> T pickOneFromCollection(Collection<T> collection) {
        Validate.notEmpty(collection, "The collection must be not empty");

        int size = collection.size();
        int target = RANDOM.nextInt(size);
        Iterator<T> iterator = collection.iterator();
        for (int i = 0; i < target; i++) {
            iterator.next();
        }
        T result = iterator.next();

        return result;
    }

    public static <T> List<T> pickFromList(List<T> list, int N, boolean allowRepetitions) {
        Validate.notEmpty(list, "The list must be not empty");

        int size = list.size();

        Validate.isPositive(N, "The number of needed elements must be positive");
        Validate.isTrue(size > N, "The number of needed elements must be smaller or equal to the list size");

        if (size == N) {
            return list;
        }

        List<T> newList = new ArrayList<T>();

        if (N == 1) {
            newList.add(pickOneFromList(list, size));
        } else {
            if (allowRepetitions) {
                for (int i = 0; i < N; i++) {
                    newList.add(pickOneFromList(list, size));
                }
            } else {
                List<Integer> targets = new ArrayList<Integer>();
                for (int i = 0; i < N; i++) {
                    int target;
                    while (true) {
                        target = RANDOM.nextInt(size);
                        if (!targets.contains(target)) {
                            targets.add(target);
                            break;
                        }
                    }
                    newList.add(list.get(target));
                }
            }
        }

        return newList;
    }

    public static <T> List<T> pickFromList(List<T> list, int N) {
        return pickFromList(list, N, false);
    }

    public static <T> T pickOneFromList(List<T> list) {
        Validate.notEmpty(list, "The list must be not empty");
        return list.get(RANDOM.nextInt(list.size()));
    }

    private static <T> T pickOneFromList(List<T> list, int size) {
        return list.get(RANDOM.nextInt(size));
    }

    // #endregion PickFromCollection

    // ============================================================================================================== //
    // #region Chance

    public static boolean percentageChance(int chance) {
        Validate.isInCloseInterval(0, 100, chance);
        return RANDOM.nextInt(100) < chance;
    }

    public static boolean percentageChance(int chance, Random random) {
        Validate.isInCloseInterval(0, 100, chance);
        return random.nextInt(100) < chance;
    }

    public static boolean fractionChance(int denominator) {
        Validate.isPositive(denominator);
        return RANDOM.nextInt(denominator) == 0;
    }

    public static boolean fractionChance(int denominator, Random random) {
        Validate.isPositive(denominator);
        return random.nextInt(denominator) == 0;
    }

    public static boolean fractionChance(int numerator, int denominator) {
        Validate.isFractionChance(numerator, denominator);
        return RANDOM.nextInt(denominator) < numerator;
    }

    public static boolean fractionChance(int numerator, int denominator, Random random) {
        Validate.isFractionChance(numerator, denominator);
        return random.nextInt(denominator) < numerator;
    }

    // #endregion Chance

    // ============================================================================================================== //
    // #region Signum

    public static int nextSignum() {
        return RANDOM.nextBoolean() ? 1 : -1;
    }

    public static int nextSignum(Random random) {
        return random.nextBoolean() ? 1 : -1;
    }

    // #endregion Signum

    // ============================================================================================================== //
    // #region Offset

    public static int offset(int bound, Random random) {
        return random.nextInt(bound) * nextSignum(random);
    }

    public static long offset(long bound, Random random) {
        return random.nextLong(bound) * nextSignum(random);
    }

    public static float offset(float bound, Random random) {
        return random.nextFloat(bound) * nextSignum(random);
    }

    public static double offset(double bound, Random random) {
        return random.nextDouble(bound) * nextSignum(random);
    }

    public static int offset(int bound) {
        return RANDOM.nextInt(bound) * nextSignum();
    }

    public static long offset(long bound) {
        return RANDOM.nextLong(bound) * nextSignum();
    }

    public static float offset(float bound) {
        return RANDOM.nextFloat(bound) * nextSignum();
    }

    public static double offset(double bound) {
        return RANDOM.nextDouble(bound) * nextSignum();
    }

    // #endregion Offset

}
