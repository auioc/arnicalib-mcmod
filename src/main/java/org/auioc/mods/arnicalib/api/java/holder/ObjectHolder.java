package org.auioc.mods.arnicalib.api.java.holder;

public class ObjectHolder<T> {

    protected T value;

    public ObjectHolder(T value) {
        this.value = value;
    }

    public T get() {
        return this.value;
    }

    public void set(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return super.toString() + "(" + this.value.toString() + ")";
    }

}
