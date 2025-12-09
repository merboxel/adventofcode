package merboxel.codeofadvent.y2025.D9;


import merboxel.codeofadvent.AoC;
import merboxel.codeofadvent.structure.Point2D;
import merboxel.codeofadvent.structure.Point3D;
import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class D9P1 extends AoC {

    private static final int year = 2025;
    private static final int day = 9;
    private static final int part = 1;

    public D9P1(Scanner sc) throws IOException {
        super(year, day, part, sc);
    }

    public D9P1() throws IOException {
        super(year, day, part);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("--------------- Part 1 ---------------");
        System.out.println(new D9P1().run());
        System.out.println("--------------------------------------");
    }

    public String run() throws IOException {
        Point2D.Point2DLong[] points = ScannerUtil.toList(sc).stream()
                .map(line -> line.split(","))
                .map(arr -> new Point2D.Point2DLong(Integer.parseInt(arr[0]), Integer.parseInt(arr[1])))
                .toArray(Point2D.Point2DLong[]::new);

        long result = 0;

        for(Point2D.Point2DLong point1 : points) {
            for(Point2D.Point2DLong point2 : points) {
                Point2D.Point2DLong tmp = point1.minus(point2);
                result = Math.max((Math.abs(tmp.getX())+1) * (Math.abs(tmp.getY()+1)), result);
            }
        }

        return Long.toString(result);
    }
}