package org.auioc.mcmod.arnicalib.base.tuple;

public record IntTriple(int x, int y, int z) {

    @Override
    public String toString() {
        return "(" + x + "," + y + "," + z + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj instanceof IntTriple other) {
            return (x == other.x()) && (y == other.y()) && (z == other.z());
        }
        return false;
    }

    @Override
    public int hashCode() { return (x * 17) + (y * 17) + z; }

}
