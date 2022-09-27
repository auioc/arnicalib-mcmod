package org.auioc.mcmod.arnicalib.base.cache;

public interface Cache<K, V> {

    V get(K key);

    void put(K key, V value);

    void clear();

}
