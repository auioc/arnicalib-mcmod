package org.auioc.mcmod.arnicalib.api.java.function;

@FunctionalInterface
public interface TriConsumer<K, V, S> {

    void accept(K k, V v, S s);

}
