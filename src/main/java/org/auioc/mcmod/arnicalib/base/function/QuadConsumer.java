package org.auioc.mcmod.arnicalib.base.function;

@FunctionalInterface
public interface QuadConsumer<A, B, C, D> {

    void accept(A a, B b, C c, D d);

}
