package org.auioc.mcmod.arnicalib.api.java.cache;

public interface Cache<K, V> {

    V get(K key);

    void put(K key, V value);

    void clear();

}
