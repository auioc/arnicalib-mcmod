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

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;

public class SoundPlayer {

    public static void play(Player player, SoundEvent sound, SoundSource source, float volume, float pitch) {
        player.playNotifySound(sound, source, volume, pitch);
    }

    public static void play(Player player, SoundEvent sound) {
        play(player, sound, SoundSource.MASTER, 1, 1);
    }

    public static void play(Player player, SoundEvent sound, float volume, float pitch) {
        play(player, sound, SoundSource.MASTER, volume, pitch);
    }


    public static void play(Player player, ResourceLocation id, SoundSource source, float volume, float pitch) {
        play(player, SoundEventRegistry.getOrElseThrow(id), source, volume, pitch);
    }

    public static void play(Player player, ResourceLocation id) {
        play(player, id);
    }

    public static void play(Player player, ResourceLocation id, float volume, float pitch) {
        play(player, id, volume, pitch);
    }


    public static void play(Player player, String id, SoundSource source, float volume, float pitch) {
        play(player, new ResourceLocation(id), source, volume, pitch);
    }

    public static void play(Player player, String id) {
        play(player, new ResourceLocation(id));
    }

    public static void play(Player player, String id, float volume, float pitch) {
        play(player, new ResourceLocation(id), volume, pitch);
    }

}
