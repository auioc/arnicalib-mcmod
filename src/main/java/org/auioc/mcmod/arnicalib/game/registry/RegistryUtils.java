/*
 * Copyright (C) 2022-2024 AUIOC.ORG
 *
 * This file is part of ArnicaLib, a mod made for Minecraft.
 *
 * ArnicaLib is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <https://www.gnu.org/licenses/>.
 */

package org.auioc.mcmod.arnicalib.game.registry;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RegistryUtils {

    public static <V> List<? extends V> allObjects(DeferredRegister<V> deferredRegister) {
        return deferredRegister.getEntries().stream().map(DeferredHolder::get).toList();
    }

    // ============================================================================================================== //

    public static <V> V random(Registry<V> registry, RandomSource random) {
        return registry.getRandom(random).map(Holder.Reference::value).orElseThrow();
    }

    // ============================================================================================================== //
    // #region GetRegistryName

    public static <V> ResourceLocation id(Registry<V> registry, V value) {
        return registry.getKey(value);
    }

    public static ResourceLocation id(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }

    public static ResourceLocation id(Item item) {
        return BuiltInRegistries.ITEM.getKey(item);
    }

    public static ResourceLocation id(SoundEvent soundEvent) {
        return BuiltInRegistries.SOUND_EVENT.getKey(soundEvent);
    }

    public static ResourceLocation id(MobEffect mobEffect) {
        return BuiltInRegistries.MOB_EFFECT.getKey(mobEffect);
    }

    public static ResourceLocation id(Potion potion) {
        return BuiltInRegistries.POTION.getKey(potion);
    }

    public static ResourceLocation id(Enchantment enchantment) {
        return BuiltInRegistries.ENCHANTMENT.getKey(enchantment);
    }

    public static ResourceLocation id(EntityType<?> entityType) {
        return BuiltInRegistries.ENTITY_TYPE.getKey(entityType);
    }

    public static ResourceLocation id(BlockEntityType<?> blockEntityType) {
        return BuiltInRegistries.BLOCK_ENTITY_TYPE.getKey(blockEntityType);
    }

    public static ResourceLocation id(ParticleType<?> particleType) {
        return BuiltInRegistries.PARTICLE_TYPE.getKey(particleType);
    }

    public static ResourceLocation id(Attribute attribute) {
        return BuiltInRegistries.ATTRIBUTE.getKey(attribute);
    }

    // #endregion GetRegistryName

    // ============================================================================================================== //

    public static <T> Comparator<T> comparator(Registry<T> registry) {
        return new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) { return registry.getKey(o1).compareTo(registry.getKey(o2)); }
        };
    }

    public static <K, V> Comparator<Map.Entry<K, V>> mapComparator(Registry<K> registry) {
        return Map.Entry.comparingByKey(comparator(registry));
    }

    // ====================================================================== //

    public static <K, V> LinkedHashMap<K, V> sortMap(Map<K, V> map, Registry<K> registry) {
        var linkedMap = new LinkedHashMap<K, V>(map.size(), 1.0F);
        map.entrySet().stream().sorted(mapComparator(registry)).forEach((e) -> linkedMap.put(e.getKey(), e.getValue()));
        return linkedMap;
    }

    public static <T> List<T> sortList(List<T> list, Registry<T> registry) {
        return list.stream().sorted(comparator(registry)).toList();
    }

}
