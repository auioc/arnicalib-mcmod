package org.auioc.mcmod.arnicalib.base.function;

@FunctionalInterface
public interface BiIntFunction<R> {

    R apply(int left, int right);

}
