package org.auioc.mcmod.arnicalib.game.item;

import java.util.List;
import java.util.Optional;
import javax.annotation.Nonnull;
import org.auioc.mcmod.arnicalib.game.registry.RegistryEntryException;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemRegistry {

    @Nonnull
    public static Optional<Item> get(ResourceLocation id) {
        return Optional.ofNullable(ForgeRegistries.ITEMS.containsKey(id) ? ForgeRegistries.ITEMS.getValue(id) : null);
    }

    @Nonnull
    public static Optional<Item> get(String id) {
        return get(new ResourceLocation(id));
    }

    @Nonnull
    public static Item getOrElseThrow(ResourceLocation id) {
        return get(id).orElseThrow(RegistryEntryException.UNKNOWN_ITEM.create(id.toString()));
    }

    @Nonnull
    public static Item getOrElseThrow(String id) {
        return get(id).orElseThrow(RegistryEntryException.UNKNOWN_ITEM.create(id.toString()));
    }

    public static List<Item> getItems(List<String> idList) {
        return idList.stream().map(ItemRegistry::getOrElseThrow).toList();
    }

}
