package agh.cs.lab;


import org.jetbrains.annotations.NotNull;

public class Vector2d {
    public final int x;
    public final int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }

    public boolean precedes(@NotNull Vector2d other) {
        return (this.x <= other.x && this.y <= other.y);
    }

    boolean follows(@NotNull Vector2d other) {
        return (this.x >= other.x && this.y >= other.y);
    }

    Vector2d upperRight(@NotNull Vector2d other) {
        Vector2d uppy = new Vector2d(Math.max(this.x, other.x), Math.max(this.y, other.y));
        return uppy;
    }

    Vector2d lowerLeft(@NotNull Vector2d other) {
        Vector2d lowwy = new Vector2d(Math.min(this.x, other.x), Math.min(this.y, other.y));
        return lowwy;
    }

    Vector2d add(@NotNull Vector2d other) {
        Vector2d addy = new Vector2d(this.x + other.x, this.y + other.y);
        return addy;
    }

    Vector2d substract(@NotNull Vector2d other) {
        Vector2d subby = new Vector2d(this.x - other.x, this.y - other.y);
        return subby;
    }

    Vector2d unEndingAdd (@NotNull Vector2d other, Vector2d mapUpperRight){
        return new Vector2d((this.x + other.x) % (mapUpperRight.x + 1), (this.y + other.y) % (mapUpperRight.y + 1));
    }

    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!(other instanceof Vector2d))
            return false;
        Vector2d that = (Vector2d) other;
        return (this.x == that.x && this.y == that.y);
    }

    Vector2d opposite() {
        Vector2d oppy = new Vector2d(-this.y, -this.x);
        return oppy;
    }


    @Override
    public int hashCode() {
        int hash = 13;
        hash += this.x * 31;
        hash += this.y * 17;
        return hash;
    }

}
