package org.auioc.mcmod.arnicalib.base.function;

import java.util.Objects;

@FunctionalInterface
public interface QuadPredicate<T, U, V, W> {

    boolean test(T t, U u, V v, W w);

    default QuadPredicate<T, U, V, W> negate() {
        return (t, u, v, w) -> !test(t, u, v, w);
    }

    default QuadPredicate<T, U, V, W> and(QuadPredicate<? super T, ? super U, ? super V, ? super W> other) {
        Objects.requireNonNull(other);
        return (t, u, v, w) -> test(t, u, v, w) && other.test(t, u, v, w);
    }

    default QuadPredicate<T, U, V, W> or(QuadPredicate<? super T, ? super U, ? super V, ? super W> other) {
        Objects.requireNonNull(other);
        return (t, u, v, w) -> test(t, u, v, w) || other.test(t, u, v, w);
    }

}
