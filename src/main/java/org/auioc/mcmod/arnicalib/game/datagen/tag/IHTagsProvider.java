package org.auioc.mcmod.arnicalib.game.datagen.tag;

import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider.IntrinsicTagAppender;
import net.minecraft.data.tags.TagsProvider.TagAppender;
import net.minecraftforge.registries.IForgeRegistry;

public interface IHTagsProvider<V> {

    @Nullable
    default IForgeRegistry<V> getRegistry() {
        return null;
    }

    default TagAppender<V> addFromRegistry(IntrinsicTagAppender<V> appender, Predicate<V> predicate) {
        var registry = this.getRegistry();
        if (registry == null) throw new UnsupportedOperationException("'addFromRegistry' method should not be called unless a registry is specified");
        for (V value : registry.getValues()) {
            if (predicate.test(value)) {
                appender.add(value);
            }
        }
        return appender;
    }

}
