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

package org.auioc.mcmod.arnicalib.game.datagen.tag;

import net.minecraft.core.Registry;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider.IntrinsicTagAppender;
import net.minecraft.data.tags.TagsProvider.TagAppender;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public interface IHTagsProvider<V> {

    @Nullable
    default Registry<V> getRegistry() {
        return null;
    }

    default TagAppender<V> addFromRegistry(IntrinsicTagAppender<V> appender, Predicate<V> predicate) {
        var registry = this.getRegistry();
        if (registry == null) {
            throw new UnsupportedOperationException("'addFromRegistry' method should not be called unless a registry is specified");
        }
        registry.stream().filter(predicate).forEachOrdered(appender::add);
        return appender;
    }

}
