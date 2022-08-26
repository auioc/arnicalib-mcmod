package org.auioc.mcmod.arnicalib.utils.game;

import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
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
        return tag(Registry.ATTRIBUTE_REGISTRY, tag);
    }

    public static TagKey<Biome> biome(ResourceLocation tag) {
        return tag(Registry.BIOME_REGISTRY, tag);
    }

    public static TagKey<BlockEntityType<?>> blockEntity(ResourceLocation tag) {
        return tag(Registry.BLOCK_ENTITY_TYPE_REGISTRY, tag);
    }

    public static TagKey<Block> block(ResourceLocation tag) {
        return tag(Registry.BLOCK_REGISTRY, tag);
    }

    public static TagKey<DimensionType> dimensionType(ResourceLocation tag) {
        return tag(Registry.DIMENSION_TYPE_REGISTRY, tag);
    }

    public static TagKey<Level> dimension(ResourceLocation tag) {
        return tag(Registry.DIMENSION_REGISTRY, tag);
    }

    public static TagKey<MobEffect> mobEffect(ResourceLocation tag) {
        return tag(Registry.MOB_EFFECT_REGISTRY, tag);
    }

    public static TagKey<Enchantment> enchantment(ResourceLocation tag) {
        return tag(Registry.ENCHANTMENT_REGISTRY, tag);
    }

    public static TagKey<EntityType<?>> entityType(ResourceLocation tag) {
        return tag(Registry.ENTITY_TYPE_REGISTRY, tag);
    }

    public static TagKey<Fluid> fluid(ResourceLocation tag) {
        return tag(Registry.FLUID_REGISTRY, tag);
    }

    public static TagKey<GameEvent> gameEvent(ResourceLocation tag) {
        return tag(Registry.GAME_EVENT_REGISTRY, tag);
    }

    public static TagKey<Item> item(ResourceLocation tag) {
        return tag(Registry.ITEM_REGISTRY, tag);
    }

    public static TagKey<MenuType<?>> menuType(ResourceLocation tag) {
        return tag(Registry.MENU_REGISTRY, tag);
    }

    public static TagKey<ParticleType<?>> particleType(ResourceLocation tag) {
        return tag(Registry.PARTICLE_TYPE_REGISTRY, tag);
    }

    public static TagKey<Potion> potion(ResourceLocation tag) {
        return tag(Registry.POTION_REGISTRY, tag);
    }

    public static TagKey<RecipeSerializer<?>> recipeSerializer(ResourceLocation tag) {
        return tag(Registry.RECIPE_SERIALIZER_REGISTRY, tag);
    }

    public static TagKey<RecipeType<?>> recipeType(ResourceLocation tag) {
        return tag(Registry.RECIPE_TYPE_REGISTRY, tag);
    }

    public static TagKey<SoundEvent> soundEvent(ResourceLocation tag) {
        return tag(Registry.SOUND_EVENT_REGISTRY, tag);
    }

    public static TagKey<ResourceLocation> stat(ResourceLocation tag) {
        return tag(Registry.CUSTOM_STAT_REGISTRY, tag);
    }

    public static TagKey<VillagerProfession> villagerProfession(ResourceLocation tag) {
        return tag(Registry.VILLAGER_PROFESSION_REGISTRY, tag);
    }

    public static TagKey<VillagerType> villagerType(ResourceLocation tag) {
        return tag(Registry.VILLAGER_TYPE_REGISTRY, tag);
    }

}
