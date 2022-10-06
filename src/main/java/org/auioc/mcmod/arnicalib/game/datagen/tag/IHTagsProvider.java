package org.auioc.mcmod.arnicalib.game.datagen.tag;

import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.data.tags.TagsProvider.TagAppender;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

public interface IHTagsProvider<T extends IForgeRegistryEntry<T>> {

    @Nullable
    default IForgeRegistry<T> getRegistry() {
        return null;
    }

    default TagAppender<T> addFromRegistry(TagAppender<T> appender, Predicate<T> predicate) {
        var registry = this.getRegistry();
        if (registry == null) throw new UnsupportedOperationException("'addFromRegistry' method should not be called unless a registry is specified");
        for (var value : registry.getValues()) {
            if (predicate.test(value)) {
                appender.add(value);
            }
        }
        return appender;
    }

}
