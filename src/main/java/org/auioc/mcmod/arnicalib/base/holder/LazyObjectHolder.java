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

package org.auioc.mcmod.arnicalib.base.holder;

import java.util.function.Supplier;
import javax.annotation.Nullable;

public class LazyObjectHolder<T> implements IObjectHolder<T> {

    private final Supplier<T> supplier;
    @Nullable
    private T resolved;

    public LazyObjectHolder(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    @Override
    @Nullable
    public T get() {
        if (this.resolved == null) {
            this.resolved = this.supplier.get();
        }
        return this.resolved;
    }

    @Override
    public void set(T value) {
        throw new UnsupportedOperationException();
    }

}
