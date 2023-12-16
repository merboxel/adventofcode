package merboxel.codeofadvent.y2023;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D16 {

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2023, 16);
    }

    static class P1 {

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 1 ---------------");
            long result = 0L;
            Scanner sc = readFile();

            List<String> lines = new ArrayList<>();

            while (sc.hasNext()) {
                lines.add(sc.nextLine());
            }

            char[][] grid = lines.stream().map(String::toCharArray).toArray(char[][]::new);
            int[][] hit = new int[grid.length][grid[0].length];
            int[] currPos = new int[] { 0, 0};
            int[] dir = new int[] { 0, 1};

            beam(grid,hit,currPos,dir);

            for (int[] row : hit)
                for (int cell : row)
                    if (cell > 0)
                        result++;

            System.out.println(result);
            System.out.println("--------------------------------------");
        }

        public static void beam(char[][] grid, int[][] hit, int[] currPos, int[] dir) {

            int x = currPos[1];
            int y = currPos[0];

            try {
                if(isCycle(hit,currPos,dir))
                    return;

                switch (grid[y][x]) {
                    case '.' -> {
                        beam(grid,hit,new int[] {y+dir[0], x+dir[1]},dir);
                    }
                    case '/' -> {
                        int[] newDir = hitMirror1(dir);
                        beam(grid,hit,new int[] {y + newDir[0], x + newDir[1]}, newDir);
                    }
                    case '\\' -> {
                        int[] newDir = hitMirror2(dir);
                        beam(grid,hit,new int[] {y + newDir[0], x + newDir[1]}, newDir);
                    }
                    case '-' -> {
                        if(dir[0] != 0) {
                            int[] left = new int[] {0,-1};
                            int[] right = new int[] {0,1};
                            beam(grid,hit,new int[] {y+left[0],x+left[1]},left);
                            beam(grid,hit,new int[] {y+right[0],x+right[1]},right);
                        } else {
                            beam(grid,hit,new int[] {y+dir[0], x+dir[1]},dir);
                        }
                    }
                    case '|' -> {
                        if(dir[1] != 0) {
                            int[] top = new int[] {-1,0};
                            int[] bot = new int[] {1,0};
                            beam(grid,hit,new int[] {y+top[0],x+top[1]},top);
                            beam(grid,hit,new int[] {y+bot[0],x+bot[1]},bot);
                        } else {
                            beam(grid,hit,new int[] {y+dir[0], x+dir[1]},dir);
                        }
                    }
                }
            } catch (ArrayIndexOutOfBoundsException ignored) { }
        }

        public static boolean isCycle(int[][] hit,int[] currPos,  int[] dir) {

            int x = currPos[1];
            int y = currPos[0];

            if(dir[1] == -1) {
                if(((hit[y][x] >>> 0) & 1) == 1) {
                    return true;
                }
                hit[y][x] += 1;
                return false;
            }
            if(dir[1] == 1) {
                if (((hit[y][x] >>> 1) & 1) == 1) {
                    return true;
                }
                hit[y][x] += 2;
                return false;
            }
            if(dir[0] == -1) {
                if(((hit[y][x] >>> 2) & 1) == 1) {
                    return true;
                }
                hit[y][x] += 4;
                return false;
            }
            if(dir[0] == 1) {
                if(((hit[y][x] >>> 3) & 1) == 1) {
                    return true;
                }
                hit[y][x] += 8;
                return false;
            }
            throw new RuntimeException("Wrong direction detected");
        }

        // '/'
        public static int[] hitMirror1(int[] dir) {
            if(dir[1] != 0)
                return new int[] { -dir[1],0  };
            return new int[] { 0,-dir[0]};
        }

        // '\'
        public static int[] hitMirror2(int[] dir) {
            if(dir[1] != 0)
                return new int[] { dir[1], 0 };
            return new int[] { 0, dir[0]};
        }
    }

    static class P2 {

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 2 ---------------");
            long result = 0L;
            Scanner sc = readFile();

            while (sc.hasNext()) {

            }

            System.out.println(result);
            System.out.println("--------------------------------------");
        }
    }
}