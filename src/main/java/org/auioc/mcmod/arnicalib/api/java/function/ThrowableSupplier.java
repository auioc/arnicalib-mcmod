package org.auioc.mcmod.arnicalib.api.java.function;

import java.util.function.Supplier;

@FunctionalInterface
public interface ThrowableSupplier<E extends Throwable> {

    Supplier<E> create(String message);

    @FunctionalInterface
    public static interface Any extends ThrowableSupplier<Throwable> {
    }

    @FunctionalInterface
    public static interface Error extends ThrowableSupplier<java.lang.Error> {
    }

    @FunctionalInterface
    public static interface Exception extends ThrowableSupplier<java.lang.Exception> {
    }

    @FunctionalInterface
    public static interface RuntimeException extends ThrowableSupplier<java.lang.RuntimeException> {
    }

}
