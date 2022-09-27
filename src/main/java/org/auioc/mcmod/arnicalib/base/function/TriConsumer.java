package org.auioc.mcmod.arnicalib.base.function;

@FunctionalInterface
public interface TriConsumer<K, V, S> {

    void accept(K k, V v, S s);

}
