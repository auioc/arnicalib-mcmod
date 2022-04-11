package org.auioc.mcmod.arnicalib.utils.game;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.registries.ForgeRegistries;

public interface SoundUtils {

    static SoundEvent getSoundEvent(ResourceLocation id) {
        return ForgeRegistries.SOUND_EVENTS.getValue(id);
    }

    static SoundEvent getSoundEvent(String id) {
        return getSoundEvent(new ResourceLocation(id));
    }

    static void playerToPlayer(Player player, String id, SoundSource source, float volume, float pitch) {
        player.playNotifySound(getSoundEvent(id), source, volume, pitch);
    }

    static void playerToPlayer(Player player, String id) {
        playerToPlayer(player, id, SoundSource.MASTER, 1, 1);
    }

    static void playerToPlayer(Player player, String id, float volume, float pitch) {
        playerToPlayer(player, id, SoundSource.MASTER, volume, pitch);
    }

    static void playerToPlayer(Player player, ResourceLocation id, SoundSource source, float volume, float pitch) {
        player.playNotifySound(getSoundEvent(id), source, volume, pitch);
    }

    static void playerToPlayer(Player player, ResourceLocation id) {
        playerToPlayer(player, id, SoundSource.MASTER, 1, 1);
    }

    static void playerToPlayer(Player player, ResourceLocation id, float volume, float pitch) {
        playerToPlayer(player, id, SoundSource.MASTER, volume, pitch);
    }

}
