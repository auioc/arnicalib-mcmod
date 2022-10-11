package org.auioc.mcmod.arnicalib.base.collection;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import org.apache.commons.lang3.Validate;

public class ListUtils {

    public static <T> T getLast(List<T> list) {
        Validate.notEmpty(list);
        return list.get(list.size() - 1);
    }


    public static <T> int indexOf(List<T> list, Predicate<T> predicate, int ordinal) {
        for (int i = 0, l = list.size(); i < l; ++i) {
            if (predicate.test(list.get(i))) {
                if (ordinal <= 0) return i;
                ordinal--;
            }
        }
        return -1;
    }

    public static <T> int indexOf(List<T> list, Predicate<T> predicate) {
        return indexOf(list, predicate, 0);
    }

    public static <T> int lastIndexOf(List<T> list, Predicate<T> predicate, int ordinal) {
        for (int i = list.size() - 1; i >= 0; --i) {
            if (predicate.test(list.get(i))) {
                if (ordinal <= 0) return i;
                ordinal--;
            }
        }
        return -1;
    }

    public static <T> int lastIndexOf(List<T> list, Predicate<T> predicate) {
        return lastIndexOf(list, predicate, 0);
    }

    public <T> int[] allIndexesOf(List<T> list, Predicate<T> predicate) {
        return IntStream
            .range(0, list.size())
            .filter((i) -> predicate.test(list.get(i)))
            .toArray();
    }


    public static <T> boolean add(List<T> list, int index, T element) {
        try {
            list.add(index, element);
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public static <T> boolean addBefore(List<T> list, Predicate<T> refPredicate, T element) {
        return add(list, indexOf(list, refPredicate), element);
    }

    public static <T> boolean addAfter(List<T> list, Predicate<T> refPredicate, T element) {
        return add(list, indexOf(list, refPredicate) + 1, element);
    }

}
