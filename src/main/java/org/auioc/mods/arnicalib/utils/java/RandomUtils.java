package org.auioc.mods.arnicalib.utils.java;

import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.lang3.Validate;

public class RandomUtils extends org.apache.commons.lang3.RandomUtils {

    public static <T> T pickOneFromCollection(Collection<T> collection) {
        int size = collection.size();

        Validate.isTrue(size > 0, "The collection must be not empty.");

        int target = nextInt(0, size);
        Iterator<T> iterator = collection.iterator();
        for (int i = 0; i < target; i++) {
            iterator.next();
        }
        T result = iterator.next();

        return result;
    }

}
