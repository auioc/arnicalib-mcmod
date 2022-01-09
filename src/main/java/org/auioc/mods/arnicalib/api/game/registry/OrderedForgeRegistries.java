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
import net.minecraft.stats.StatType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.decoration.Motive;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.entity.schedule.Schedule;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProviderType;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.world.ForgeWorldPreset;
import net.minecraftforge.registries.DataSerializerEntry;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;


public class OrderedForgeRegistries {

    private static final Marker MARKER = LogUtil.getMarker("OrderedForgeRegistries");

    private static <V extends IForgeRegistryEntry<V>> List<Entry<ResourceLocation, V>> createEntryList(IForgeRegistry<V> iRegistry) {
        ForgeRegistry<V> registry = (ForgeRegistry<V>) iRegistry;

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
        public static final List<Entry<ResourceLocation, Block>> ENTRIES = createEntryList(ForgeRegistries.BLOCKS);
    }
    public static class Fluids {
        public static final List<Entry<ResourceLocation, Fluid>> ENTRIES = createEntryList(ForgeRegistries.FLUIDS);
    }
    public static class Items {
        public static final List<Entry<ResourceLocation, Item>> ENTRIES = createEntryList(ForgeRegistries.ITEMS);
    }
    public static class MobEffects {
        public static final List<Entry<ResourceLocation, MobEffect>> ENTRIES = createEntryList(ForgeRegistries.MOB_EFFECTS);
    }
    public static class SoundEvents {
        public static final List<Entry<ResourceLocation, SoundEvent>> ENTRIES = createEntryList(ForgeRegistries.SOUND_EVENTS);
    }
    public static class Potions {
        public static final List<Entry<ResourceLocation, Potion>> ENTRIES = createEntryList(ForgeRegistries.POTIONS);
    }
    public static class Enchantments {
        public static final List<Entry<ResourceLocation, Enchantment>> ENTRIES = createEntryList(ForgeRegistries.ENCHANTMENTS);
    }
    public static class Entities {
        public static final List<Entry<ResourceLocation, EntityType<?>>> ENTRIES = createEntryList(ForgeRegistries.ENTITIES);
    }
    public static class BlockEntities {
        public static final List<Entry<ResourceLocation, BlockEntityType<?>>> ENTRIES = createEntryList(ForgeRegistries.BLOCK_ENTITIES);
    }
    public static class ParticleTypes {
        public static final List<Entry<ResourceLocation, ParticleType<?>>> ENTRIES = createEntryList(ForgeRegistries.PARTICLE_TYPES);
    }
    public static class Containers {
        public static final List<Entry<ResourceLocation, MenuType<?>>> ENTRIES = createEntryList(ForgeRegistries.CONTAINERS);
    }
    public static class PaintingTypes {
        public static final List<Entry<ResourceLocation, Motive>> ENTRIES = createEntryList(ForgeRegistries.PAINTING_TYPES);
    }
    public static class RecipeSerializers {
        public static final List<Entry<ResourceLocation, RecipeSerializer<?>>> ENTRIES = createEntryList(ForgeRegistries.RECIPE_SERIALIZERS);
    }
    public static class Attributes {
        public static final List<Entry<ResourceLocation, Attribute>> ENTRIES = createEntryList(ForgeRegistries.ATTRIBUTES);
    }
    public static class StatTypes {
        public static final List<Entry<ResourceLocation, StatType<?>>> ENTRIES = createEntryList(ForgeRegistries.STAT_TYPES);
    }
    public static class Professions {
        public static final List<Entry<ResourceLocation, VillagerProfession>> ENTRIES = createEntryList(ForgeRegistries.PROFESSIONS);
    }
    public static class PoiTypes {
        public static final List<Entry<ResourceLocation, PoiType>> ENTRIES = createEntryList(ForgeRegistries.POI_TYPES);
    }
    public static class MemoryModuleTypes {
        public static final List<Entry<ResourceLocation, MemoryModuleType<?>>> ENTRIES = createEntryList(ForgeRegistries.MEMORY_MODULE_TYPES);
    }
    public static class SensorTypes {
        public static final List<Entry<ResourceLocation, SensorType<?>>> ENTRIES = createEntryList(ForgeRegistries.SENSOR_TYPES);
    }
    public static class Schedules {
        public static final List<Entry<ResourceLocation, Schedule>> ENTRIES = createEntryList(ForgeRegistries.SCHEDULES);
    }
    public static class Activities {
        public static final List<Entry<ResourceLocation, Activity>> ENTRIES = createEntryList(ForgeRegistries.ACTIVITIES);
    }
    public static class WorldCarvers {
        public static final List<Entry<ResourceLocation, WorldCarver<?>>> ENTRIES = createEntryList(ForgeRegistries.WORLD_CARVERS);
    }
    public static class Features {
        public static final List<Entry<ResourceLocation, Feature<?>>> ENTRIES = createEntryList(ForgeRegistries.FEATURES);
    }
    public static class ChunkStatus {
        public static final List<Entry<ResourceLocation, net.minecraft.world.level.chunk.ChunkStatus>> ENTRIES = createEntryList(ForgeRegistries.CHUNK_STATUS);
    }
    public static class StructureFeatures {
        public static final List<Entry<ResourceLocation, StructureFeature<?>>> ENTRIES = createEntryList(ForgeRegistries.STRUCTURE_FEATURES);
    }
    public static class BlockStateProviderTypes {
        public static final List<Entry<ResourceLocation, BlockStateProviderType<?>>> ENTRIES = createEntryList(ForgeRegistries.BLOCK_STATE_PROVIDER_TYPES);
    }
    public static class FoliagePlacerTypes {
        public static final List<Entry<ResourceLocation, FoliagePlacerType<?>>> ENTRIES = createEntryList(ForgeRegistries.FOLIAGE_PLACER_TYPES);
    }
    public static class TreeDecoratorTypes {
        public static final List<Entry<ResourceLocation, TreeDecoratorType<?>>> ENTRIES = createEntryList(ForgeRegistries.TREE_DECORATOR_TYPES);
    }
    public static class Biomes {
        public static final List<Entry<ResourceLocation, Biome>> ENTRIES = createEntryList(ForgeRegistries.BIOMES);
    }
    public static class DataSerializers {
        public static final List<Entry<ResourceLocation, DataSerializerEntry>> ENTRIES = createEntryList(ForgeRegistries.DATA_SERIALIZERS);
    }
    public static class LootModifierSerializers {
        public static final List<Entry<ResourceLocation, GlobalLootModifierSerializer<?>>> ENTRIES = createEntryList(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS);
    }
    public static class WorldTypes {
        public static final List<Entry<ResourceLocation, ForgeWorldPreset>> ENTRIES = createEntryList(ForgeRegistries.WORLD_TYPES);
    }

}
