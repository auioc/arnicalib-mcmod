package org.auioc.mcmod.arnicalib.api.java.function;

import java.util.Objects;

@FunctionalInterface
public interface QuadPredicate<A, B, C, D> {

    boolean test(A a, B b, C c, D d);

    default QuadPredicate<A, B, C, D> negate() {
        return (A a, B b, C c, D d) -> !test(a, b, c, d);
    }

    default QuadPredicate<A, B, C, D> and(QuadPredicate<? super A, ? super B, ? super C, ? super D> other) {
        Objects.requireNonNull(other);
        return (A a, B b, C c, D d) -> test(a, b, c, d) && other.test(a, b, c, d);
    }

    default QuadPredicate<A, B, C, D> or(QuadPredicate<? super A, ? super B, ? super C, ? super D> other) {
        Objects.requireNonNull(other);
        return (A a, B b, C c, D d) -> test(a, b, c, d) || other.test(a, b, c, d);
    }

}
