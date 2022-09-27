package org.auioc.mcmod.arnicalib.utils.game;

import static org.auioc.mcmod.arnicalib.ArnicaLib.LOGGER;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.logging.log4j.Marker;
import org.auioc.mcmod.arnicalib.base.holder.LazyObjectHolder;
import org.auioc.mcmod.arnicalib.base.log.LogUtil;
import org.auioc.mcmod.arnicalib.base.validate.Validate;
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
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.IForgeRegistryModifiable;


public class OrderedForgeRegistries {

    private static final Marker MARKER = LogUtil.getMarker("OrderedForgeRegistries");

    public static final LazyObjectHolder<List<Entry<ResourceLocation, Block>>> BLOCKS = new LazyObjectHolder<>(() -> createEntryList(ForgeRegistries.BLOCKS));
    public static final LazyObjectHolder<List<Entry<ResourceLocation, Fluid>>> FLUIDS = new LazyObjectHolder<>(() -> createEntryList(ForgeRegistries.FLUIDS));
    public static final LazyObjectHolder<List<Entry<ResourceLocation, Item>>> ITEMS = new LazyObjectHolder<>(() -> createEntryList(ForgeRegistries.ITEMS));
    public static final LazyObjectHolder<List<Entry<ResourceLocation, SoundEvent>>> SOUND_EVENTS = new LazyObjectHolder<>(() -> createEntryList(ForgeRegistries.SOUND_EVENTS));
    public static final LazyObjectHolder<List<Entry<ResourceLocation, MobEffect>>> MOB_EFFECTS = new LazyObjectHolder<>(() -> createEntryList(ForgeRegistries.MOB_EFFECTS));
    public static final LazyObjectHolder<List<Entry<ResourceLocation, Potion>>> POTIONS = new LazyObjectHolder<>(() -> createEntryList(ForgeRegistries.POTIONS));
    public static final LazyObjectHolder<List<Entry<ResourceLocation, Enchantment>>> ENCHANTMENTS = new LazyObjectHolder<>(() -> createEntryList(ForgeRegistries.ENCHANTMENTS));
    public static final LazyObjectHolder<List<Entry<ResourceLocation, EntityType<?>>>> ENTITIES = new LazyObjectHolder<>(() -> createEntryList(ForgeRegistries.ENTITIES));
    public static final LazyObjectHolder<List<Entry<ResourceLocation, BlockEntityType<?>>>> BLOCK_ENTITIES = new LazyObjectHolder<>(() -> createEntryList(ForgeRegistries.BLOCK_ENTITIES));
    public static final LazyObjectHolder<List<Entry<ResourceLocation, ParticleType<?>>>> PARTICLE_TYPES = new LazyObjectHolder<>(() -> createEntryList(ForgeRegistries.PARTICLE_TYPES));

    private static <V extends IForgeRegistryEntry<V>> List<Entry<ResourceLocation, V>> createEntryList(IForgeRegistry<V> iRegistry) {
        Validate.isTrue(iRegistry instanceof IForgeRegistryModifiable, "Only supports IForgeRegistryModifiable");

        IForgeRegistryModifiable<V> registry = (IForgeRegistryModifiable<V>) iRegistry;

        ResourceLocation name = registry.getRegistryName();
        LOGGER.info(MARKER, "Creating entry list of registry '" + name + "'");

        Validate.isTrue(registry.isLocked(), "The entry list can only be created from a frozen registry");

        List<Entry<ResourceLocation, V>> list = new ArrayList<Entry<ResourceLocation, V>>();

        Iterator<Entry<ResourceKey<V>, V>> iterator = registry.getEntries().iterator();
        while (iterator.hasNext()) {
            Entry<ResourceKey<V>, V> entry = iterator.next();
            list.add(Map.entry(entry.getKey().location(), entry.getValue()));
        }

        LOGGER.info(MARKER, "Created entry list of registry '" + name + "'' with " + list.size() + " entries");
        return List.copyOf(list);
    }

}
