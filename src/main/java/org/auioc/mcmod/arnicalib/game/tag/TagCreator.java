package org.auioc.mcmod.arnicalib.game.tag;

import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;

public class TagCreator {

    public static <T> TagKey<T> tag(ResourceKey<? extends Registry<T>> registryKey, ResourceLocation tag) {
        return TagKey.create(registryKey, tag);
    }


    public static TagKey<Attribute> attribute(ResourceLocation tag) {
        return tag(Registries.ATTRIBUTE, tag);
    }

    public static TagKey<Biome> biome(ResourceLocation tag) {
        return tag(Registries.BIOME, tag);
    }

    public static TagKey<BlockEntityType<?>> blockEntity(ResourceLocation tag) {
        return tag(Registries.BLOCK_ENTITY_TYPE, tag);
    }

    public static TagKey<Block> block(ResourceLocation tag) {
        return tag(Registries.BLOCK, tag);
    }

    public static TagKey<DimensionType> dimensionType(ResourceLocation tag) {
        return tag(Registries.DIMENSION_TYPE, tag);
    }

    public static TagKey<Level> dimension(ResourceLocation tag) {
        return tag(Registries.DIMENSION, tag);
    }

    public static TagKey<MobEffect> mobEffect(ResourceLocation tag) {
        return tag(Registries.MOB_EFFECT, tag);
    }

    public static TagKey<Enchantment> enchantment(ResourceLocation tag) {
        return tag(Registries.ENCHANTMENT, tag);
    }

    public static TagKey<EntityType<?>> entityType(ResourceLocation tag) {
        return tag(Registries.ENTITY_TYPE, tag);
    }

    public static TagKey<Fluid> fluid(ResourceLocation tag) {
        return tag(Registries.FLUID, tag);
    }

    public static TagKey<GameEvent> gameEvent(ResourceLocation tag) {
        return tag(Registries.GAME_EVENT, tag);
    }

    public static TagKey<Item> item(ResourceLocation tag) {
        return tag(Registries.ITEM, tag);
    }

    public static TagKey<MenuType<?>> menuType(ResourceLocation tag) {
        return tag(Registries.MENU, tag);
    }

    public static TagKey<ParticleType<?>> particleType(ResourceLocation tag) {
        return tag(Registries.PARTICLE_TYPE, tag);
    }

    public static TagKey<Potion> potion(ResourceLocation tag) {
        return tag(Registries.POTION, tag);
    }

    public static TagKey<RecipeSerializer<?>> recipeSerializer(ResourceLocation tag) {
        return tag(Registries.RECIPE_SERIALIZER, tag);
    }

    public static TagKey<RecipeType<?>> recipeType(ResourceLocation tag) {
        return tag(Registries.RECIPE_TYPE, tag);
    }

    public static TagKey<SoundEvent> soundEvent(ResourceLocation tag) {
        return tag(Registries.SOUND_EVENT, tag);
    }

    public static TagKey<ResourceLocation> stat(ResourceLocation tag) {
        return tag(Registries.CUSTOM_STAT, tag);
    }

    public static TagKey<VillagerProfession> villagerProfession(ResourceLocation tag) {
        return tag(Registries.VILLAGER_PROFESSION, tag);
    }

    public static TagKey<VillagerType> villagerType(ResourceLocation tag) {
        return tag(Registries.VILLAGER_TYPE, tag);
    }

}
