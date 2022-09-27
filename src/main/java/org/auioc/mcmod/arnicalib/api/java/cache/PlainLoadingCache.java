package org.auioc.mcmod.arnicalib.api.java.cache;

import java.util.HashMap;
import java.util.function.Function;
import javax.annotation.Nonnull;

public class PlainLoadingCache<K, V> implements LoadingCache<K, V> {

    private final HashMap<K, V> map = new HashMap<K, V>();
    private final Function<K, V> loader;

    public PlainLoadingCache(Function<K, V> loader) {
        this.loader = loader;
    }

    @Nonnull
    @Override
    public V get(K key) {
        return (map.containsKey(key)) ? map.get(key) : load(key);
    }

    @Nonnull
    @Override
    public V load(K key) {
        V value = loader.apply(key);
        map.put(key, value);
        return value;
    }


    @Override
    public void clear() {
        map.clear();
    }

    public void refresh(K key) {
        load(key);
    }

}
