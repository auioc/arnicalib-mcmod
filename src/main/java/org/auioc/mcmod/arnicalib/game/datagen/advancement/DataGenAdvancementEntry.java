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

package org.auioc.mcmod.arnicalib.game.datagen.advancement;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public record DataGenAdvancementEntry(ResourceLocation id, Advancement.Builder builder) {

    public DataGenAdvancementEntry(ResourceLocation id, UnaryOperator<Advancement.Builder> builder) {
        this(id, builder.apply(Advancement.Builder.advancement()));
    }

    public DataGenAdvancementEntry(ResourceLocation id, BiFunction<ResourceLocation, Advancement.Builder, Advancement.Builder> builder) {
        this(id, builder.apply(id, Advancement.Builder.advancement()));
    }

    public void accept(Consumer<AdvancementHolder> saver, ExistingFileHelper fileHelper) {
        builder.save(saver, id, fileHelper);
    }

}
