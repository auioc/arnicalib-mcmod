package org.auioc.mcmod.arnicalib.game.registry;

import java.util.function.Predicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

public interface VanillaPredicates {

    Predicate<ResourceLocation> ID = (id) -> id.getNamespace().equals("minecraft");
    Predicate<String> STRING_ID = (id) -> ID.test(new ResourceLocation(id));
    Predicate<ForgeRegistryEntry<?>> REGISTRY_ENTRY = (entry) -> ID.test(entry.getRegistryName());

}
