package org.auioc.mcmod.arnicalib.base.collection;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import org.apache.commons.lang3.Validate;

public class ListUtils {

    public static <T> T getLast(List<T> list) {
        Validate.notEmpty(list);
        return list.get(list.size() - 1);
    }

    public static <T> Optional<T> getOptional(List<T> list, int index) {
        return (index >= 0 && index < list.size()) ? Optional.ofNullable(list.get(index)) : Optional.empty();
    }

    public static <T> T getOrDefault(List<T> list, int index, T defaultValue) {
        return (index >= 0 && index < list.size()) ? list.get(index) : defaultValue;
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


    public static <T> List<T> findAll(List<T> list, Predicate<T> predicate) {
        return list.stream().filter(predicate).toList();
    }

    public static <T> Optional<T> findFirst(List<T> list, Predicate<T> predicate) {
        return list.stream().filter(predicate).findFirst();
    }

    public static <T> Optional<T> findFirst(List<T> list, Predicate<T> predicate, int ordinal) {
        return getOptional(list, indexOf(list, predicate, ordinal));
    }

    public static <T> Optional<T> findLast(List<T> list, Predicate<T> predicate, int ordinal) {
        return getOptional(list, lastIndexOf(list, predicate, ordinal));
    }


    public static <T> boolean add(List<T> list, int index, T element) {
        if (index > list.size() || index < 0) return false;
        list.add(index, element);
        return true;
    }

    public static <T> boolean addBefore(List<T> list, Predicate<T> refPredicate, T element) {
        return add(list, indexOf(list, refPredicate), element);
    }

    public static <T> boolean addAfter(List<T> list, Predicate<T> refPredicate, T element) {
        return add(list, indexOf(list, refPredicate) + 1, element);
    }

}
