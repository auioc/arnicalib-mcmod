package org.auioc.mcmod.arnicalib.base.function;

import java.util.function.UnaryOperator;

@FunctionalInterface
public interface StringUnaryOperator extends UnaryOperator<String> {

    String apply(String s);

    @Deprecated(since = "6.0.3", forRemoval = true)
    default String applyAsString(String s) {
        return apply(s);
    }

}
