package org.auioc.mods.arnicalib.api.game.registry;

import static org.auioc.mods.arnicalib.ArnicaLib.LOGGER;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.logging.log4j.Marker;
import org.auioc.mods.arnicalib.utils.LogUtil;
import org.auioc.mods.arnicalib.utils.java.Validate;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.IForgeRegistryModifiable;


public class OrderedForgeRegistries {

    private static final Marker MARKER = LogUtil.getMarker("OrderedForgeRegistries");

    private static <V extends IForgeRegistryEntry<V>> List<Entry<ResourceLocation, V>> createEntryList(IForgeRegistry<V> iRegistry) {
        Validate.isTrue(iRegistry instanceof IForgeRegistryModifiable, "Only supports IForgeRegistryModifiable");

        IForgeRegistryModifiable<V> registry = (IForgeRegistryModifiable<V>) iRegistry;

        ResourceLocation name = registry.getRegistryName();
        LOGGER.info(MARKER, "Creating entry list of registry " + name);

        Validate.isTrue(registry.isLocked(), "The entry list can only be created from a frozen registry");

        List<Entry<ResourceLocation, V>> list = new ArrayList<Entry<ResourceLocation, V>>();

        Iterator<Entry<ResourceKey<V>, V>> iterator = registry.getEntries().iterator();
        while (iterator.hasNext()) {
            Entry<ResourceKey<V>, V> entry = iterator.next();
            list.add(Map.entry(entry.getKey().location(), entry.getValue()));
        }

        LOGGER.info(MARKER, "Created entry list of registry " + name + " with " + list.size() + " entries");
        return List.copyOf(list);
    }


    public static class Blocks {
        private static final List<Entry<ResourceLocation, Block>> ENTRIES = createEntryList(ForgeRegistries.BLOCKS);

        public static List<Entry<ResourceLocation, Block>> get() {
            return ENTRIES;
        }
    }
    public static class Fluids {
        private static final List<Entry<ResourceLocation, Fluid>> ENTRIES = createEntryList(ForgeRegistries.FLUIDS);

        public static List<Entry<ResourceLocation, Fluid>> get() {
            return ENTRIES;
        }
    }
    public static class Items {
        private static final List<Entry<ResourceLocation, Item>> ENTRIES = createEntryList(ForgeRegistries.ITEMS);

        public static List<Entry<ResourceLocation, Item>> get() {
            return ENTRIES;
        }
    }
    public static class MobEffects {
        private static final List<Entry<ResourceLocation, MobEffect>> ENTRIES = createEntryList(ForgeRegistries.MOB_EFFECTS);

        public static List<Entry<ResourceLocation, MobEffect>> get() {
            return ENTRIES;
        }
    }
    public static class SoundEvents {
        private static final List<Entry<ResourceLocation, SoundEvent>> ENTRIES = createEntryList(ForgeRegistries.SOUND_EVENTS);

        public static List<Entry<ResourceLocation, SoundEvent>> get() {
            return ENTRIES;
        }
    }
    public static class Potions {
        private static final List<Entry<ResourceLocation, Potion>> ENTRIES = createEntryList(ForgeRegistries.POTIONS);

        public static List<Entry<ResourceLocation, Potion>> get() {
            return ENTRIES;
        }
    }
    public static class Enchantments {
        private static final List<Entry<ResourceLocation, Enchantment>> ENTRIES = createEntryList(ForgeRegistries.ENCHANTMENTS);

        public static List<Entry<ResourceLocation, Enchantment>> get() {
            return ENTRIES;
        }
    }
    public static class Entities {
        private static final List<Entry<ResourceLocation, EntityType<?>>> ENTRIES = createEntryList(ForgeRegistries.ENTITIES);

        public static List<Entry<ResourceLocation, EntityType<?>>> get() {
            return ENTRIES;
        }
    }
    public static class ParticleTypes {
        private static final List<Entry<ResourceLocation, ParticleType<?>>> ENTRIES = createEntryList(ForgeRegistries.PARTICLE_TYPES);

        public static List<Entry<ResourceLocation, ParticleType<?>>> get() {
            return ENTRIES;
        }
    }

}
