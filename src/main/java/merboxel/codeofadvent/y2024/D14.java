package merboxel.codeofadvent.y2024;


import merboxel.codeofadvent.structure.Point;
import merboxel.codeofadvent.util.PatternMatching;
import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.*;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D14 {

    static int width;
    static int height;

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2024, 14);
    }

    static class P1 {
        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 1 ---------------");
            Scanner sc = readFile();

            width = 101;
            height = 103;

            List<Robot> robots = new ArrayList<>();

            while(sc.hasNextLine()) {
                robots.add(new Robot(sc.nextLine()));
            }

            for(int i = 0; i < 100; i ++) {
                robots.forEach(Robot::move);
            }

            long q1 = robots.stream().filter(r -> r.findQuadrant() == 1).count();
            long q2 = robots.stream().filter(r -> r.findQuadrant() == 2).count();
            long q3 = robots.stream().filter(r -> r.findQuadrant() == 3).count();
            long q4 = robots.stream().filter(r -> r.findQuadrant() == 4).count();

            System.out.println(q1*q2*q3*q4);
            System.out.println("--------------------------------------");
        }

        private static class Robot {
            Point loc;
            Point vel;

            public Robot(Point loc, Point vel) {
                this.loc = loc;
                this.vel = vel;
            }

            public Robot(String line) {
                String[] tmp = line.split(" ");
                long[] p = PatternMatching.getLongsWithNegativesAsArray(tmp[0]);
                long[] v = PatternMatching.getLongsWithNegativesAsArray(tmp[1]);
                this.loc = new Point(p[0], p[1]);
                this.vel = new Point(v[0], v[1]);
            }

            public void move() {
                this.loc = new Point(Math.floorMod(loc.x + vel.x, width), Math.floorMod(loc.y + vel.y, height));
            }

            public int findQuadrant() {
                if(loc.x < width/2 && loc.y < height/2)
                    return 1;
                if(loc.x > width/2 && loc.y < height/2)
                    return 2;
                if(loc.x < width/2 && loc.y > height/2)
                    return 3;
                if(loc.x > width/2 && loc.y > height/2)
                    return 4;
                return 0;
            }
        }
    }

    static class P2 {
        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 2 ---------------");
            Scanner sc = readFile();

            width = 101;
            height = 103;

            List<Robot> robots = new ArrayList<>();

            while(sc.hasNextLine()) {
                robots.add(new Robot(sc.nextLine()));
            }

            int seconds = 1;// 7037
            while(true) {
                robots.forEach(Robot::move);
                int[][] grid = new int[width][height];
                boolean noOverlap = true;
                for(Robot r: robots) {
                    grid[(int)r.loc.x][(int)r.loc.y] ++;
                    if (grid[(int)r.loc.x][(int)r.loc.y] > 1)
                        noOverlap = false;
                }
                if(noOverlap) {
                    System.out.println("------" + (seconds) + "-----");
                    for (int[] line : grid) {
                        System.out.println(Arrays.toString(line));
                    }
                    break;
                }
                seconds ++;
            }

            System.out.println("--------------------------------------");
        }

        private static class Robot {
            Point loc;
            Point vel;

            public Robot(Point loc, Point vel) {
                this.loc = loc;
                this.vel = vel;
            }

            public Robot(String line) {
                String[] tmp = line.split(" ");
                long[] p = PatternMatching.getLongsWithNegativesAsArray(tmp[0]);
                long[] v = PatternMatching.getLongsWithNegativesAsArray(tmp[1]);
                this.loc = new Point(p[0], p[1]);
                this.vel = new Point(v[0], v[1]);
            }

            public void move() {
                this.loc = new Point(Math.floorMod(loc.x + vel.x, width), Math.floorMod(loc.y + vel.y, height));
            }

            public int findQuadrant() {
                if(loc.x < width/2 && loc.y < height/2)
                    return 1;
                if(loc.x > width/2 && loc.y < height/2)
                    return 2;
                if(loc.x < width/2 && loc.y > height/2)
                    return 3;
                if(loc.x > width/2 && loc.y > height/2)
                    return 4;
                return 0;
            }
        }
    }
}