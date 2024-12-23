package merboxel.codeofadvent.structure;

import java.util.Objects;

public abstract class Point2D<T extends Number, S> implements Cloneable, Comparable<S> {

    protected Point2D() {
    }

    public abstract T getX();

    public abstract T getY();

    public abstract S add(Point2D<T, S> that);

    public abstract S minus(Point2D<T, S> that);

    public abstract int hashCode();

    public abstract boolean equals(Object obj);

    public Point2D<T, S> clone() {
        try {
            return (Point2D<T, S>) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    public abstract int compareTo(S that);

    public static class Point2DInteger extends Point2D<Integer, Point2DInteger> implements Cloneable, Comparable<Point2DInteger> {

        private final int x;
        private final int y;

        public Point2DInteger() {
            this.x = 0;
            this.y = 0;
        }

        public Point2DInteger(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public Integer getX() {
            return this.x;
        }

        @Override
        public Integer getY() {
            return this.y;
        }

        @Override
        public Point2DInteger add(Point2D<Integer, Point2DInteger> that) {
            return new Point2DInteger(this.getX() + that.getX(), this.getY() + that.getY());
        }

        public Point2DInteger minus(Point2D<Integer, Point2DInteger> that) {
            return new Point2DInteger(this.getX() - that.getX(), this.getY() - that.getY());
        }

        @Override
        public boolean equals(Object _that) {
            if (_that instanceof Point2DInteger that) {
                return Objects.equals(this.getX(), that.getX()) && Objects.equals(this.getY(), that.getX());
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Point2D.Point2DInteger[" + x + "," + y + "]";
        }

        @Override
        public Point2DInteger clone() {
            return (Point2DInteger) super.clone();
        }

        @Override
        public int compareTo(Point2DInteger that) {
            if (this.equals(that)) {
                return 0;
            }

            if (this.getX() < that.getX()) {
                return -1;
            }
            if (this.getX() > that.getX()) {
                return 1;
            }
            if (this.getY() < that.getY()) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    public static class Point2DLong extends Point2D<Long, Point2DLong> implements Cloneable, Comparable<Point2DLong> {

        private final long x;
        private final long y;

        public Point2DLong() {
            this.x = 0L;
            this.y = 0L;
        }

        public Point2DLong(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public Long getX() {
            return this.x;
        }

        @Override
        public Long getY() {
            return this.y;
        }

        @Override
        public Point2DLong add(Point2D<Long, Point2DLong> that) {
            return new Point2DLong(this.getX() + that.getX(), this.getY() + that.getY());
        }

        public Point2DLong minus(Point2D<Long, Point2DLong> that) {
            return new Point2DLong(this.getX() - that.getX(), this.getY() - that.getY());
        }

        @Override
        public boolean equals(Object _that) {
            if (_that instanceof Point2DLong that) {
                return Objects.equals(this.getX(), that.getX()) && Objects.equals(this.getY(), that.getX());
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Point2D.Point2DLong[" + x + "," + y + "]";
        }

        @Override
        public int compareTo(Point2DLong that) {
            if (this.equals(that)) {
                return 0;
            }

            if (this.getX() < that.getX()) {
                return -1;
            }
            if (this.getX() > that.getX()) {
                return 1;
            }
            if (this.getY() < that.getY()) {
                return -1;
            } else {
                return 1;
            }
        }

        @Override
        public Point2DLong clone() {
            return (Point2DLong) super.clone();
        }
    }

    public static class Point2DDouble extends Point2D<Double, Point2DDouble> implements Cloneable, Comparable<Point2DDouble> {

        private final double x;
        private final double y;

        public Point2DDouble() {
            this.x = 0D;
            this.y = 0D;
        }

        public Point2DDouble(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public Double getX() {
            return this.x;
        }

        @Override
        public Double getY() {
            return this.y;
        }

        @Override
        public Point2DDouble add(Point2D<Double, Point2DDouble> that) {
            return new Point2DDouble(this.getX() + that.getX(), this.getY() + that.getY());
        }

        public Point2DDouble minus(Point2D<Double, Point2DDouble> that) {
            return new Point2DDouble(this.getX() - that.getX(), this.getY() - that.getY());
        }

        @Override
        public boolean equals(Object _that) {
            if (_that instanceof Point2DDouble that) {
                return Objects.equals(this.getX(), that.getX()) && Objects.equals(this.getY(), that.getX());
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Point2D.Point2DDouble[" + x + "," + y + "]";
        }

        @Override
        public int compareTo(Point2DDouble that) {
            if (this.equals(that)) {
                return 0;
            }

            if (this.getX() < that.getX()) {
                return -1;
            }
            if (this.getX() > that.getX()) {
                return 1;
            }
            if (this.getY() < that.getY()) {
                return -1;
            } else {
                return 1;
            }
        }

        @Override
        public Point2DDouble clone() {
            return (Point2DDouble) super.clone();
        }
    }

    public static class Point2DFloat extends Point2D<Float, Point2DFloat> implements Cloneable, Comparable<Point2DFloat> {

        private final float x;
        private final float y;

        public Point2DFloat() {
            this.x = 0F;
            this.y = 0F;
        }

        public Point2DFloat(float x, float y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public Float getX() {
            return this.x;
        }

        @Override
        public Float getY() {
            return this.y;
        }

        @Override
        public Point2DFloat add(Point2D<Float, Point2DFloat> that) {
            return new Point2DFloat(this.getX() + that.getX(), this.getY() + that.getY());
        }

        public Point2DFloat minus(Point2D<Float, Point2DFloat> that) {
            return new Point2DFloat(this.getX() - that.getX(), this.getY() - that.getY());
        }

        @Override
        public boolean equals(Object _that) {
            if (_that instanceof Point2DFloat that) {
                return Objects.equals(this.getX(), that.getX()) && Objects.equals(this.getY(), that.getX());
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Point2D.Point2DFloat[" + x + "," + y + "]";
        }

        @Override
        public int compareTo(Point2DFloat that) {
            if (this.equals(that)) {
                return 0;
            }

            if (this.getX() < that.getX()) {
                return -1;
            }
            if (this.getX() > that.getX()) {
                return 1;
            }
            if (this.getY() < that.getY()) {
                return -1;
            } else {
                return 1;
            }
        }

        @Override
        public Point2DFloat clone() {
            return (Point2DFloat) super.clone();
        }
    }
}
