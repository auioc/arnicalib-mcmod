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

package org.auioc.mcmod.arnicalib.base.cache;

import java.util.HashMap;
import java.util.function.Function;
import javax.annotation.Nonnull;

public class PlainLoadingCache<K, V> implements LoadingCache<K, V> {

    private final HashMap<K, V> map = new HashMap<K, V>();
    private final Function<K, V> loader;

    public PlainLoadingCache(Function<K, V> loader) {
        this.loader = loader;
    }

    @Nonnull
    @Override
    public V get(K key) {
        return (map.containsKey(key)) ? map.get(key) : load(key);
    }

    @Nonnull
    @Override
    public V load(K key) {
        V value = loader.apply(key);
        map.put(key, value);
        return value;
    }


    @Override
    public void clear() {
        map.clear();
    }

    public void refresh(K key) {
        load(key);
    }

}
