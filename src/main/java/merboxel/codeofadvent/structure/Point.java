package merboxel.codeofadvent.structure;

public class Point{
    long x;
    long y;
    long z;

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

    @Override
    public String toString() {
        return "["+x+","+y+","+z+"]";
    }
}
