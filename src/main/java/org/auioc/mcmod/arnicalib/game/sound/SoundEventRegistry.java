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
