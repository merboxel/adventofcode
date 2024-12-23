package merboxel.codeofadvent.y2024;


import merboxel.codeofadvent.structure.Point3D;
import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.*;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D10 {

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2024, 10);
    }

    static class P1 {

        static int[][] dir = {{-1,0}, {0,1}, {1,0}, {0,-1}};

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 1 ---------------");
            Scanner sc = readFile();

            int result = 0;

            char[][] _grid = ScannerUtil.toList(sc).stream().map(String::toCharArray).toArray(char[][]::new);
            int[][] grid = new int[_grid.length][_grid[0].length];
            for(int i = 0; i < _grid.length; i++) {
                for(int j = 0; j < _grid[0].length; j++) {
                    grid[i][j] = _grid[i][j] - '0';
                }
            }

            for(int x = 0; x < grid.length; x++) {
                for(int y = 0; y < grid[0].length; y++) {
                    if(grid[x][y] == 0) {
                        boolean[][] visited = new boolean[grid.length][grid[0].length];
                        PriorityQueue<PointWithValue> prio = new PriorityQueue<>();

                        prio.add(new PointWithValue(x,y,grid[x][y]));
                        while(!prio.isEmpty()) {
                            PointWithValue point = prio.poll();
                            int _x = (int) point.x;
                            int _y = (int) point.y;
                            if(visited[_x][_y]) {
                                continue;
                            } else {
                                visited[_x][_y] = true;
                            }

                            if(point.value == 9)
                                result++;

                            for(int[] dir : dir) {
                                try{
                                    int nextValue = grid[_x+dir[0]][_y+dir[1]];
                                    if(nextValue == point.value + 1) {
                                        prio.add(new PointWithValue(_x+dir[0], _y+dir[1], nextValue));
                                    }
                                } catch(Exception ignored) {}
                            }
                        }
                    }
                }
            }

            System.out.println(result);
            System.out.println("--------------------------------------");
        }

        public static class PointWithValue extends Point3D implements Comparable<PointWithValue> {
            final int value;

            PointWithValue(int x, int y, int value) {
                super(x, y);
                this.value = value;
            }

            @Override
            public int compareTo(PointWithValue o) {
                return Integer.compare(value, o.value);
            }
        }
    }

    static class P2 {

        static int[][] dir = {{-1,0}, {0,1}, {1,0}, {0,-1}};

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 2 ---------------");
            Scanner sc = readFile();

            int result = 0;

            char[][] _grid = ScannerUtil.toList(sc).stream().map(String::toCharArray).toArray(char[][]::new);
            int[][] grid = new int[_grid.length][_grid[0].length];
            for(int i = 0; i < _grid.length; i++) {
                for(int j = 0; j < _grid[0].length; j++) {
                    grid[i][j] = _grid[i][j] - '0';
                }
            }

            for(int x = 0; x < grid.length; x++) {
                for(int y = 0; y < grid[0].length; y++) {
                    if(grid[x][y] == 0) {
                        result += recursive(new PointWithValue(x,y,0), grid);
                    }
                }
            }

            System.out.println(result);
            System.out.println("--------------------------------------");
        }

        public static int recursive(PointWithValue point, int[][] grid) {
            int x = (int) point.x;
            int y = (int) point.y;
            int value = point.value;

            if(value == 9)
                return 1;

            int result = 0;

            for(int[] dir : dir) {
                try {
                    if (value + 1 == grid[x + dir[0]][y + dir[1]]) {
                        result += recursive(new PointWithValue(x + dir[0], y + dir[1], grid[x + dir[0]][y + dir[1]]), grid);
                    }
                } catch (Exception ignored) {}
            }

            return result;
        }

        public static class PointWithValue extends Point3D implements Comparable<P1.PointWithValue> {
            final int value;

            PointWithValue(int x, int y, int value) {
                super(x, y);
                this.value = value;
            }

            @Override
            public int compareTo(P1.PointWithValue o) {
                return Integer.compare(value, o.value);
            }
        }
    }
}