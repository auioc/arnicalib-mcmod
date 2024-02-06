/*
 * Copyright (C) 2022-2024 AUIOC.ORG
 *
 * This file is part of ArnicaLib, a mod made for Minecraft.
 *
 * ArnicaLib is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <https://www.gnu.org/licenses/>.
 */

package org.auioc.mcmod.arnicalib.base.function;

import java.util.Objects;

@FunctionalInterface
public interface FailableTriPredicate<T, U, V, E extends Throwable> {

    boolean test(T t, U u, V v) throws E;

    default FailableTriPredicate<T, U, V, E> and(FailableTriPredicate<? super T, ? super U, ? super V, E> other) {
        Objects.requireNonNull(other);
        return (t, u, v) -> test(t, u, v) && other.test(t, u, v);
    }

    default FailableTriPredicate<T, U, V, E> negate() {
        return (t, u, v) -> !test(t, u, v);
    }

    default FailableTriPredicate<T, U, V, E> or(FailableTriPredicate<? super T, ? super U, ? super V, E> other) {
        Objects.requireNonNull(other);
        return (t, u, v) -> test(t, u, v) || other.test(t, u, v);
    }

}
