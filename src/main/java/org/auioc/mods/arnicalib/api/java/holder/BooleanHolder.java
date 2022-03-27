package org.auioc.mods.arnicalib.api.java.holder;

public class BooleanHolder extends ObjectHolder<Boolean> {

    public BooleanHolder(Boolean value) {
        super(value);
    }

    public void reverse() {
        this.value = !this.value;
    }

}
