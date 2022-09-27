package org.auioc.mcmod.arnicalib.base.holder;

public class ObjectHolder<T> implements IObjectHolder<T> {

    protected T value;

    public ObjectHolder(T value) {
        this.value = value;
    }

    @Override
    public T get() {
        return this.value;
    }

    @Override
    public void set(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return super.toString() + "(" + this.value.toString() + ")";
    }

}
