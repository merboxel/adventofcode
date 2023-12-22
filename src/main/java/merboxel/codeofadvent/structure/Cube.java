package merboxel.codeofadvent.structure;

public class Cube {
    public long x;
    public long y;
    public long z;

    public long lengthX;
    public long lengthY;
    public long lengthZ;

    public Cube() {
        this.x = 0L;
        this.y = 0L;
        this.z = 0L;
        this.lengthX = 0L;
        this.lengthY = 0L;
        this.lengthZ = 0L;
    }

    public Cube(long x, long y, long z, long lengthX, long lengthY, long lengthZ) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.lengthX = lengthX;
        this.lengthY = lengthY;
        this.lengthZ = lengthZ;
    }

    public Cube(Point p1, Point p2) {
        this.x = Math.min(p1.x,p2.x);
        this.y = Math.min(p1.y,p2.y);
        this.z = Math.min(p1.z,p2.z);

        this.lengthX = Math.abs(p1.x-p2.x);
        this.lengthY = Math.abs(p1.y-p2.y);
        this.lengthZ = Math.abs(p1.z-p2.z);
    }
}
