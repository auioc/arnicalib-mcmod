package org.auioc.mcmod.arnicalib.game.registry;

import java.util.function.Predicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class VanillaPredicates {

    public static final Predicate<ResourceLocation> ID = (id) -> id.getNamespace().equals("minecraft");
    public static final Predicate<String> STRING_ID = (id) -> ID.test(new ResourceLocation(id));
    public static final Predicate<ForgeRegistryEntry<?>> REGISTRY_ENTRY = (entry) -> ID.test(entry.getRegistryName());

}
