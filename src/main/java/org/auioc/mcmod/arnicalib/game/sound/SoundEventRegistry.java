package org.auioc.mcmod.arnicalib.game.sound;

import java.util.Optional;
import javax.annotation.Nonnull;
import org.auioc.mcmod.arnicalib.game.registry.RegistryEntryException;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundEventRegistry {

    @Nonnull
    public static Optional<SoundEvent> get(ResourceLocation id) {
        return Optional.ofNullable(ForgeRegistries.SOUND_EVENTS.containsKey(id) ? ForgeRegistries.SOUND_EVENTS.getValue(id) : null);
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
