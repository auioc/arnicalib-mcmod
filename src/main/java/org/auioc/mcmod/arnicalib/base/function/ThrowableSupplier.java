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

import java.util.function.Supplier;

@FunctionalInterface
public interface ThrowableSupplier<E extends Throwable> {

    Supplier<E> create(String message);

    @FunctionalInterface
    interface Any extends ThrowableSupplier<Throwable> {
    }


    @FunctionalInterface
    interface Error extends ThrowableSupplier<java.lang.Error> {
    }


    @FunctionalInterface
    interface Exception extends ThrowableSupplier<java.lang.Exception> {
    }


    @FunctionalInterface
    interface RuntimeException extends ThrowableSupplier<java.lang.RuntimeException> {
    }

}
