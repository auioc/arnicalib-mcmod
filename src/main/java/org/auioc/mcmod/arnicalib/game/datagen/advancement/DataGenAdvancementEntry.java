package org.auioc.mcmod.arnicalib.game.datagen.advancement;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import net.minecraft.advancements.Advancement;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;

public record DataGenAdvancementEntry(ResourceLocation id, Advancement.Builder builder) {

    public DataGenAdvancementEntry(ResourceLocation id, UnaryOperator<Advancement.Builder> builder) {
        this(id, builder.apply(Advancement.Builder.advancement()));
    }

    public DataGenAdvancementEntry(ResourceLocation id, BiFunction<ResourceLocation, Advancement.Builder, Advancement.Builder> builder) {
        this(id, builder.apply(id, Advancement.Builder.advancement()));
    }

    public void accept(Consumer<Advancement> writer, ExistingFileHelper fileHelper) {
        builder.save(writer, id, fileHelper);
    }

}
