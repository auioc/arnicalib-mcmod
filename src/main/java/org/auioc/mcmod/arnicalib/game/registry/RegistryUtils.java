package org.auioc.mcmod.arnicalib.game.registry;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

public class RegistryUtils {

    private RegistryUtils() {}

    public static <V> List<V> allObjects(DeferredRegister<V> deferredRegister) {
        return deferredRegister.getEntries().stream().map(RegistryObject::get).toList();
    }

    /*================================================================================================================*/
    // #region GetRegistryName

    public static <V> ResourceLocation id(IForgeRegistry<V> registry, V value) {
        return registry.getKey(value);
    }

    public static ResourceLocation id(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    public static ResourceLocation id(Item item) {
        return ForgeRegistries.ITEMS.getKey(item);
    }

    public static ResourceLocation id(SoundEvent soundEvent) {
        return ForgeRegistries.SOUND_EVENTS.getKey(soundEvent);
    }

    public static ResourceLocation id(MobEffect mobEffect) {
        return ForgeRegistries.MOB_EFFECTS.getKey(mobEffect);
    }

    public static ResourceLocation id(Potion potion) {
        return ForgeRegistries.POTIONS.getKey(potion);
    }

    public static ResourceLocation id(Enchantment enchantment) {
        return ForgeRegistries.ENCHANTMENTS.getKey(enchantment);
    }

    public static ResourceLocation id(EntityType<?> entityType) {
        return ForgeRegistries.ENTITY_TYPES.getKey(entityType);
    }

    public static ResourceLocation id(BlockEntityType<?> blockEntityType) {
        return ForgeRegistries.BLOCK_ENTITY_TYPES.getKey(blockEntityType);
    }

    public static ResourceLocation id(ParticleType<?> particleType) {
        return ForgeRegistries.PARTICLE_TYPES.getKey(particleType);
    }

    // #endregion GetRegistryName

    // ============================================================================================================== //

    public static <T> Comparator<T> comparator(IForgeRegistry<T> registry) {
        return new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) { return registry.getKey(o1).compareTo(registry.getKey(o2)); }
        };
    }

    public static <K, V> Comparator<Map.Entry<K, V>> mapComparator(IForgeRegistry<K> registry) {
        return Map.Entry.comparingByKey(comparator(registry));
    }

    // ====================================================================== //

    public static <K, V> LinkedHashMap<K, V> sortMap(Map<K, V> map, IForgeRegistry<K> registry) {
        var linkedMap = new LinkedHashMap<K, V>(map.size(), 1.0F);
        map.entrySet().stream().sorted(mapComparator(registry)).forEach((e) -> linkedMap.put(e.getKey(), e.getValue()));
        return linkedMap;
    }

    public static <T> List<T> sortList(List<T> list, IForgeRegistry<T> registry) {
        return list.stream().sorted(comparator(registry)).toList();
    }

}
