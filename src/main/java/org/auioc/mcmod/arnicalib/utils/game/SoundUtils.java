package org.auioc.mcmod.arnicalib.utils.game;

import java.util.Optional;
import javax.annotation.Nonnull;
import org.auioc.mcmod.arnicalib.game.registry.RegistryEntryException;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.registries.ForgeRegistries;

public interface SoundUtils {

    @Nonnull
    static Optional<SoundEvent> getSoundEvent(ResourceLocation id) {
        return Optional.ofNullable(ForgeRegistries.SOUND_EVENTS.containsKey(id) ? ForgeRegistries.SOUND_EVENTS.getValue(id) : null);
    }

    @Nonnull
    static Optional<SoundEvent> getSoundEvent(String id) {
        return getSoundEvent(new ResourceLocation(id));
    }

    @Nonnull
    static SoundEvent getSoundEventOrElseThrow(ResourceLocation id) {
        return getSoundEvent(id).orElseThrow(RegistryEntryException.UNKNOWN_SOUND_EVENT.create(id.toString()));
    }

    @Nonnull
    static SoundEvent getSoundEventOrElseThrow(String id) {
        return getSoundEvent(id).orElseThrow(RegistryEntryException.UNKNOWN_SOUND_EVENT.create(id));
    }

    static void play(Player player, String id, SoundSource source, float volume, float pitch) {
        player.playNotifySound(getSoundEventOrElseThrow(id), source, volume, pitch);
    }

    static void play(Player player, String id) {
        play(player, id, SoundSource.MASTER, 1, 1);
    }

    static void play(Player player, String id, float volume, float pitch) {
        play(player, id, SoundSource.MASTER, volume, pitch);
    }

    static void play(Player player, ResourceLocation id, SoundSource source, float volume, float pitch) {
        player.playNotifySound(getSoundEventOrElseThrow(id), source, volume, pitch);
    }

    static void play(Player player, ResourceLocation id) {
        play(player, id, SoundSource.MASTER, 1, 1);
    }

    static void play(Player player, ResourceLocation id, float volume, float pitch) {
        play(player, id, SoundSource.MASTER, volume, pitch);
    }

}
