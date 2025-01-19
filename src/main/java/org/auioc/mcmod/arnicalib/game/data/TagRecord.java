/*
 * Copyright (C) 2025 AUIOC.ORG
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

package org.auioc.mcmod.arnicalib.game.data;

import net.minecraft.core.Registry;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @since 7.0.0
 */
public record TagRecord<T>(TagKey<T> tag, Consumer<TagsProvider.TagAppender<T>> appender) {

    public void build(Function<TagKey<T>, TagsProvider.TagAppender<T>> builder) {
        appender.accept(builder.apply(tag));
    }

    // ============================================================================================================== //

    public static <T> TagRecord<T> of(TagKey<T> tag, Consumer<TagsProvider.TagAppender<T>> appender) {
        return new TagRecord<>(tag, appender);
    }

    public static <T> TagRecord<T> of(ResourceKey<? extends Registry<T>> registry, ResourceLocation id, Consumer<TagsProvider.TagAppender<T>> appender) {
        return new TagRecord<>(TagKey.create(registry, id), appender);
    }

}
