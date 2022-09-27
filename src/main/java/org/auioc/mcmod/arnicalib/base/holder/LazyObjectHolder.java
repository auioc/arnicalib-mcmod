package org.auioc.mcmod.arnicalib.base.holder;

import java.util.function.Supplier;
import javax.annotation.Nullable;

public class LazyObjectHolder<T> implements IObjectHolder<T> {

    private final Supplier<T> supplier;
    @Nullable
    private T resolved;

    public LazyObjectHolder(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    @Override
    @Nullable
    public T get() {
        if (this.resolved == null) {
            this.resolved = this.supplier.get();
        }
        return this.resolved;
    }

    @Override
    public void set(T value) {
        throw new UnsupportedOperationException();
    }

}
