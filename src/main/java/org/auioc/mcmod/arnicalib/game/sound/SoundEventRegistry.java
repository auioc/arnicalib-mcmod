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

package org.auioc.mcmod.arnicalib.game.sound;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import org.auioc.mcmod.arnicalib.game.registry.RegistryEntryException;

import javax.annotation.Nonnull;
import java.util.Optional;

public class SoundEventRegistry {

    @Nonnull
    public static Optional<SoundEvent> get(ResourceLocation id) {
        return Optional.ofNullable(BuiltInRegistries.SOUND_EVENT.containsKey(id) ? BuiltInRegistries.SOUND_EVENT.get(id) : null);
    }

    @Nonnull
    public static Optional<SoundEvent> get(String id) {
        return get(new ResourceLocation(id));
    }

    @Nonnull
    public static SoundEvent getOrElseThrow(ResourceLocation id) {
        return get(id).orElseThrow(RegistryEntryException.UNKNOWN_SOUND_EVENT.create(id.toString()));
    }

    @Nonnull
    public static SoundEvent getOrElseThrow(String id) {
        return get(id).orElseThrow(RegistryEntryException.UNKNOWN_SOUND_EVENT.create(id));
    }

}
