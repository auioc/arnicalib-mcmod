package org.auioc.mcmod.arnicalib.game.enchantment;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import org.auioc.mcmod.arnicalib.game.registry.RegistryEntryException;

import javax.annotation.Nonnull;
import java.util.Optional;

public class EnchantmentRegistry {

    @Nonnull
    public static Optional<Enchantment> get(ResourceLocation id) {
        return Optional.ofNullable(BuiltInRegistries.ENCHANTMENT.containsKey(id) ? BuiltInRegistries.ENCHANTMENT.get(id) : null);
    }

    @Nonnull
    public static Optional<Enchantment> get(String id) {
        return get(new ResourceLocation(id));
    }

    @Nonnull
    public static Enchantment getOrElseThrow(ResourceLocation id) {
        return get(id).orElseThrow(RegistryEntryException.UNKNOWN_ENCHANTMENT.create(id.toString()));
    }

    @Nonnull
    public static Enchantment getOrElseThrow(String id) {
        return get(id).orElseThrow(RegistryEntryException.UNKNOWN_ENCHANTMENT.create(id));
    }

}
