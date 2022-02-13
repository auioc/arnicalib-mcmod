package org.auioc.mods.arnicalib.utils.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class RandomUtils extends org.apache.commons.lang3.RandomUtils {

    /*================================================================================================================*/
    // #region PickFromCollection

    public static <T> T pickOneFromCollection(Collection<T> collection) {
        Validate.notEmpty(collection, "The collection must be not empty");

        int size = collection.size();
        int target = nextInt(0, size);
        Iterator<T> iterator = collection.iterator();
        for (int i = 0; i < target; i++) {
            iterator.next();
        }
        T result = iterator.next();

        return result;
    }

    public static <T> List<T> pickFromList(List<T> list, int N) {
        Validate.notEmpty(list, "The list must be not empty");

        int size = list.size();

        Validate.isPositive(N, "The number of needed elements must be positive");
        Validate.isTrue(size > N, "The number of needed elements must be smaller or equal to the list size");

        if (size == N) {
            return list;
        }

        List<T> newList = new ArrayList<T>();

        if (N == 1) {
            newList.add(list.get(nextInt(0, size)));
        } else {
            List<Integer> targets = new ArrayList<Integer>();
            for (int i = 0; i < N; i++) {
                int target;
                while (true) {
                    target = nextInt(0, size);
                    if (!targets.contains(target)) {
                        targets.add(target);
                        break;
                    }
                }
                newList.add(list.get(target));
            }
        }

        return newList;
    }

    public static <T> T pickOneFromList(List<T> list) {
        Validate.notEmpty(list, "The list must be not empty");
        return list.get(nextInt(0, list.size()));
    }

    // #endregion PickFromCollection

    /*================================================================================================================*/
    // #region Chance

    public static boolean percentageChance(int chance) {
        Validate.isInCloseInterval(0, 100, chance);
        return nextInt(0, 100) < chance;
    }

    public static boolean percentageChance(int chance, Random random) {
        Validate.isInCloseInterval(0, 100, chance);
        return random.nextInt(100) < chance;
    }

    public static boolean fractionChance(int denominator) {
        Validate.isPositive(denominator);
        return nextInt(0, denominator) == 0;
    }

    public static boolean fractionChance(int denominator, Random random) {
        Validate.isPositive(denominator);
        return random.nextInt(denominator) == 0;
    }

    public static boolean fractionChance(int numerator, int denominator) {
        Validate.isFractionChance(numerator, denominator);
        return nextInt(0, denominator) < numerator;
    }

    public static boolean fractionChance(int numerator, int denominator, Random random) {
        Validate.isFractionChance(numerator, denominator);
        return random.nextInt(denominator) < numerator;
    }

    // #endregion Chance

}
