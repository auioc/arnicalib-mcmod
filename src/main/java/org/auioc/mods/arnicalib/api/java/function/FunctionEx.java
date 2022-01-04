package org.auioc.mods.arnicalib.api.java.function;

@FunctionalInterface
public interface FunctionEx<T, R> {

    R apply(T t) throws Exception;

}
