package org.auioc.mods.arnicalib.api.java.data;

public class ThreeTuple<A, B, C> extends Tuple<A, B> {

    private C c;

    public ThreeTuple(A a, B b, C c) {
        super(a, b);
        this.c = c;
    }

    public C getC() {
        return this.c;
    }

    public void setC(C c) {
        this.c = c;
    }

}
