package merboxel.codeofadvent.y2025.D9;


import merboxel.codeofadvent.AoC;
import merboxel.codeofadvent.structure.Point2D;
import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.Scanner;

public class D9P2 extends AoC {

    private static final int year = 2025;
    private static final int day = 9;
    private static final int part = 2;

    public D9P2(Scanner sc) throws IOException {
        super(year, day, part, sc);
    }

    public D9P2() throws IOException {
        super(year, day, part);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("--------------- Part 2 ---------------");
        System.out.println(new D9P2().run());
        System.out.println("--------------------------------------");
    }

    public String run() throws IOException {
        Point2D.Point2DLong[] points = ScannerUtil.toList(sc).stream()
                .map(line -> line.split(","))
                .map(arr -> new Point2D.Point2DLong(Integer.parseInt(arr[0]), Integer.parseInt(arr[1])))
                .toArray(Point2D.Point2DLong[]::new);

        Square[] allLines = new Square[points.length];

        for(int i = 0; i < allLines.length; i++){
            long minX = Math.min(points[i].getX(), points[(i+1)% points.length].getX());
            long maxX = Math.max(points[i].getX(), points[(i+1)% points.length].getX());
            long minY = Math.min(points[i].getY(), points[(i+1)% points.length].getY());
            long maxY = Math.max(points[i].getY(), points[(i+1)% points.length].getY());

            allLines[i] = new Square(minX, maxX, minY, maxY);
        }

        long result = 0;

        for(int i = 0; i < points.length; i++){
            next : for(int j = i+1; j < points.length; j++){
                long minX = Math.min(points[i].getX(), points[j].getX());
                long maxX = Math.max(points[i].getX(), points[j].getX());
                long minY = Math.min(points[i].getY(), points[j].getY());
                long maxY = Math.max(points[i].getY(), points[j].getY());

                Square square = new Square(minX, maxX, minY, maxY);

                for(Square line: allLines){
                    if(square.isLineCrossing(line)){
                        continue next;
                    }
                }
                result = Math.max(result,square.getArea());
            }
        }
        return Long.toString(result);
    }

    record Square(long minX, long maxX, long minY, long maxY) {

        public boolean isLineCrossing(Square line) {

            if(line.maxX - line.minX == 0) {
                if(this.minX < line.minX && line.maxX < this.maxX){ // check if the x coordinate is between the bounds of the square
                    return this.minY < line.maxY && this.maxY > line.minY; // does the line cross the square on the y?
                }
            } else {
                if(this.minY < line.minY && line.maxY < this.maxY){ // check if the y coordinate is between the bounds of the square
                    return this.minX < line.maxX && this.maxX > line.minX; // does the line cross the square on the x?
                }
            }
            return false;
        }

        public long getArea() {
            return  (maxX - minX + 1) * (maxY - minY + 1);
        }
    }
}