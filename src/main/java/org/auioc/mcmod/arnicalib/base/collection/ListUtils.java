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

    public static <T> int indexOf(List<T> list, Predicate<T> predicate) {
        for (int i = 0, l = list.size(); i < l; ++i) {
            if (predicate.test(list.get(i))) {
                return i;
            }
        }
        return -1;
    }

    public static <T> int lastIndexOf(List<T> list, Predicate<T> predicate) {
        for (int i = list.size() - 1; i >= 0; --i) {
            if (predicate.test(list.get(i))) {
                return i;
            }
        }
        return -1;
    }

    public <T> int[] allIndexesOf(List<T> list, Predicate<T> predicate) {
        return IntStream
            .range(0, list.size())
            .filter((i) -> predicate.test(list.get(i)))
            .toArray();
    }

}
