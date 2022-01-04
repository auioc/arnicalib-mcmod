package org.auioc.mods.arnicalib.utils.game;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.registries.ForgeRegistries;

public interface SoundUtils {
    static SoundEvent getSoundEvent(String key) {
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(key));
    }

    static void playSoundToPlayer(Player player, String key, SoundSource source, float volume, float pitch) {
        if (!key.equals("")) {
            player.playNotifySound(getSoundEvent(key), source, volume, pitch);
        }
    }


    static void playSoundToPlayer(Player player, String key) {
        playSoundToPlayer(player, key, SoundSource.MASTER, 1, 1);
    }

    static void playSoundToPlayer(Player player, String key, float volume, float pitch) {
        playSoundToPlayer(player, key, SoundSource.MASTER, volume, pitch);
    }

}
