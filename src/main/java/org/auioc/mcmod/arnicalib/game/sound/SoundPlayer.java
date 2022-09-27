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
