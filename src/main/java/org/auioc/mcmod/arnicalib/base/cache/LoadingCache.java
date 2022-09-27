package org.auioc.mcmod.arnicalib.base.cache;

import javax.annotation.Nonnull;

public interface LoadingCache<K, V> extends Cache<K, V> {

    @Nonnull
    V load(K key);

    @Override
    default void put(K key, V value) {
        throw new UnsupportedOperationException();
    }

}
