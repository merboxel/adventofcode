package merboxel.codeofadvent.structure;

import java.util.Objects;

public class Point {
    public long x;
    public long y;
    public long z;

    public Point() {
        this.x = 0L;
        this.y = 0L;
        this.z = 0L;
    }

    public Point(long x, long y, long z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point(long x, long y) {
        this(x,y,0);
    }

    @Override
    public boolean equals(Object _that) {
        if(_that instanceof Point that) {
            return this.x == that.x && this.y == that.y && this.z == that.z;
        }
        return false;
    }

    public Point distanceVector(Point _that) {
        return new Point(
                this.x - _that.x,
                this.y - _that.y,
                this.z - _that.z
        );
    }

    public Point addVector(Point _that) {
        return new Point(
                this.x + _that.x,
                this.y + _that.y,
                this.z + _that.z
        );
    }

    public long distance(Point _that) {
        return Math.abs(this.x - _that.x) +
                Math.abs(this.y - _that.y) +
                Math.abs(this.z - _that.z);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x,y,z);
    }

    @Override
    public String toString() {
        return "["+x+","+y+","+z+"]";
    }
}
