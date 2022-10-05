package org.auioc.mcmod.arnicalib.mod.datagen.provider;

import static org.auioc.mcmod.arnicalib.game.tag.HEntityTypeTags.*;
import java.util.function.Predicate;
import org.auioc.mcmod.arnicalib.ArnicaLib;
import org.auioc.mcmod.arnicalib.game.entity.EntityTypePredicates;
import org.jetbrains.annotations.Nullable;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class HEntityTypeTagsProvider extends EntityTypeTagsProvider {

    public HEntityTypeTagsProvider(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, ArnicaLib.MOD_ID, existingFileHelper);
    }

    @Override
    public String getName() {
        return "HEntityTypeTags";
    }

    @Override
    protected void addTags() {
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

        add(tag(MISC_ENTITIES), EntityTypePredicates.IS_MISC);
        add(tag(MONSTERS), EntityTypePredicates.IS_MONSTER);
        add(tag(CREATURES), EntityTypePredicates.IS_CREATURE);
        add(tag(AXOLOTLS), EntityTypePredicates.IS_AXOLOTLS);
        add(tag(UNDERGROUND_WATER_CREATURES), EntityTypePredicates.IS_UNDERGROUND_WATER_CREATURE);
        add(tag(WATER_CREATURES), EntityTypePredicates.IS_WATER_CREATURE);
        add(tag(WATER_AMBIENT_ENTITIES), EntityTypePredicates.IS_WATER_AMBIENT);
        add(tag(AMBIENT_ENTITIES), EntityTypePredicates.IS_AMBIENT);

        add(tag(FRIENDLY_ENTITIES), EntityTypePredicates.IS_FRIENDLY);
        add(tag(PERSISTENT_ENTITIES), EntityTypePredicates.IS_PERSISTENT);
    }

    private static void add(TagAppender<EntityType<?>> appender, Predicate<EntityType<?>> predicate) {
        for (var entityType : ForgeRegistries.ENTITIES.getValues()) {
            if (predicate.test(entityType)) {
                appender.add(entityType);
            }
        }
    }

}
