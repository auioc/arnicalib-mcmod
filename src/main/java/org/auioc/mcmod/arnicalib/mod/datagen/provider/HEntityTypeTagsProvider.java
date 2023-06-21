package org.auioc.mcmod.arnicalib.mod.datagen.provider;

import static org.auioc.mcmod.arnicalib.game.tag.HEntityTypeTags.*;
import java.util.concurrent.CompletableFuture;
import javax.annotation.Nonnull;
import org.auioc.mcmod.arnicalib.ArnicaLib;
import org.auioc.mcmod.arnicalib.game.datagen.tag.IHTagsProvider;
import org.auioc.mcmod.arnicalib.game.entity.EntityTypePredicates;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public class HEntityTypeTagsProvider extends EntityTypeTagsProvider implements IHTagsProvider<EntityType<?>> {

    public HEntityTypeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup, ExistingFileHelper fileHelper) {
        super(output, lookup, ArnicaLib.MOD_ID, fileHelper);
    }

    @Override
    public String getName() {
        return "HEntityTypeTags";
    }

    @Nonnull
    @Override
    public IForgeRegistry<EntityType<?>> getRegistry() {
        return ForgeRegistries.ENTITY_TYPES;
    }

    @Override
    protected void addTags(HolderLookup.Provider lookup) {
        tag(UNDEFINED_MOBS);
        tag(UNDEAD_MOBS).add(
            EntityType.DROWNED, EntityType.HUSK, EntityType.PHANTOM,
            EntityType.SKELETON, EntityType.SKELETON_HORSE, EntityType.STRAY,
            EntityType.WITHER, EntityType.WITHER_SKELETON, EntityType.ZOGLIN,
            EntityType.ZOMBIE, EntityType.ZOMBIE_HORSE,
            EntityType.ZOMBIE_VILLAGER, EntityType.ZOMBIFIED_PIGLIN
        );
        tag(ARTHROPODS).add(
            EntityType.BEE, EntityType.ENDERMAN, EntityType.SILVERFISH,
            EntityType.SPIDER, EntityType.CAVE_SPIDER
        );
        tag(ILLAGERS).add(
            EntityType.PILLAGER, EntityType.ILLUSIONER, EntityType.RAVAGER,
            EntityType.EVOKER, EntityType.VINDICATOR
        );
        tag(AQUATIC_MOBS).add(
            EntityType.AXOLOTL, EntityType.COD, EntityType.DOLPHIN,
            EntityType.ELDER_GUARDIAN, EntityType.GLOW_SQUID,
            EntityType.GUARDIAN, EntityType.PUFFERFISH, EntityType.SALMON,
            EntityType.SQUID, EntityType.TROPICAL_FISH, EntityType.TURTLE
        );

        addFromRegistry(tag(MISC_ENTITIES), EntityTypePredicates.IS_MISC);
        addFromRegistry(tag(MONSTERS), EntityTypePredicates.IS_MONSTER);
        addFromRegistry(tag(CREATURES), EntityTypePredicates.IS_CREATURE);
        addFromRegistry(tag(AXOLOTLS), EntityTypePredicates.IS_AXOLOTLS);
        addFromRegistry(tag(UNDERGROUND_WATER_CREATURES), EntityTypePredicates.IS_UNDERGROUND_WATER_CREATURE);
        addFromRegistry(tag(WATER_CREATURES), EntityTypePredicates.IS_WATER_CREATURE);
        addFromRegistry(tag(WATER_AMBIENT_ENTITIES), EntityTypePredicates.IS_WATER_AMBIENT);
        addFromRegistry(tag(AMBIENT_ENTITIES), EntityTypePredicates.IS_AMBIENT);

        addFromRegistry(tag(FRIENDLY_ENTITIES), EntityTypePredicates.IS_FRIENDLY);
        addFromRegistry(tag(PERSISTENT_ENTITIES), EntityTypePredicates.IS_PERSISTENT);
    }

}
