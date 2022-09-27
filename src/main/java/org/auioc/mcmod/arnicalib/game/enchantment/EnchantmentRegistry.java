package org.auioc.mcmod.arnicalib.game.enchantment;

import java.util.Optional;
import javax.annotation.Nonnull;
import org.auioc.mcmod.arnicalib.game.registry.RegistryEntryException;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.ForgeRegistries;

public class EnchantmentRegistry {

    @Nonnull
    public static Optional<Enchantment> get(ResourceLocation id) {
        return Optional.ofNullable(ForgeRegistries.ENCHANTMENTS.containsKey(id) ? ForgeRegistries.ENCHANTMENTS.getValue(id) : null);
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
        return get(id).orElseThrow(RegistryEntryException.UNKNOWN_ENCHANTMENT.create(id.toString()));
    }

}
