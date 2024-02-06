package org.auioc.mcmod.arnicalib.game.datagen.advancement;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public record DataGenAdvancementEntry(ResourceLocation id, Advancement.Builder builder) {

    public DataGenAdvancementEntry(ResourceLocation id, UnaryOperator<Advancement.Builder> builder) {
        this(id, builder.apply(Advancement.Builder.advancement()));
    }

    public DataGenAdvancementEntry(ResourceLocation id, BiFunction<ResourceLocation, Advancement.Builder, Advancement.Builder> builder) {
        this(id, builder.apply(id, Advancement.Builder.advancement()));
    }

    public void accept(Consumer<AdvancementHolder> saver, ExistingFileHelper fileHelper) {
        builder.save(saver, id, fileHelper);
    }

}
