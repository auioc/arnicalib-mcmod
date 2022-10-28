package org.auioc.mcmod.arnicalib.base.cache;

import java.util.HashMap;
import java.util.Optional;
import javax.annotation.Nullable;
import org.checkerframework.checker.units.qual.K;
import org.lwjgl.system.CallbackI.V;

public class PlainCache implements Cache<K, V> {

    private final HashMap<K, V> map = new HashMap<K, V>();

    @Nullable
    @Override
    public V get(K key) {
        return map.get(key);
    }

    public Optional<V> getOptional(K key) {
        return Optional.ofNullable(get(key));
    }

    @Override
    public void put(K key, V value) {
        map.put(key, value);
    }

    @Override
    public void clear() {
        map.clear();
    }

}
