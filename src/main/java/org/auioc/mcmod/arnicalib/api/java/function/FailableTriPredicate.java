package org.auioc.mcmod.arnicalib.api.java.function;

import java.util.Objects;

@FunctionalInterface
public interface FailableTriPredicate<T, U, V, E extends Throwable> {

    boolean test(T t, U u, V v) throws E;

    default FailableTriPredicate<T, U, V, E> and(FailableTriPredicate<? super T, ? super U, ? super V, E> other) {
        Objects.requireNonNull(other);
        return (T t, U u, V v) -> test(t, u, v) && other.test(t, u, v);
    }

    default FailableTriPredicate<T, U, V, E> negate() {
        return (T t, U u, V v) -> !test(t, u, v);
    }

    default FailableTriPredicate<T, U, V, E> or(FailableTriPredicate<? super T, ? super U, ? super V, E> other) {
        Objects.requireNonNull(other);
        return (T t, U u, V v) -> test(t, u, v) || other.test(t, u, v);
    }

}
