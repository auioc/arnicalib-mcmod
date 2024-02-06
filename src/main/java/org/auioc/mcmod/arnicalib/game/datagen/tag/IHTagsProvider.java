package org.auioc.mcmod.arnicalib.game.datagen.tag;

import net.minecraft.core.Registry;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider.IntrinsicTagAppender;
import net.minecraft.data.tags.TagsProvider.TagAppender;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public interface IHTagsProvider<V> {

    @Nullable
    default Registry<V> getRegistry() {
        return null;
    }

    default TagAppender<V> addFromRegistry(IntrinsicTagAppender<V> appender, Predicate<V> predicate) {
        var registry = this.getRegistry();
        if (registry == null) {
            throw new UnsupportedOperationException("'addFromRegistry' method should not be called unless a registry is specified");
        }
        registry.stream().filter(predicate).forEachOrdered(appender::add);
        return appender;
    }

}
