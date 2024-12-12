package merboxel.codeofadvent.y2024;


import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D12 {

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2024, 12);
    }

    static class P1 {

        static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        static char[][] grid;
        static boolean[][] visited;
        static List<Region> regions = new ArrayList<>();

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 1 ---------------");
            Scanner sc = readFile();

            grid = ScannerUtil.toList(sc).stream().map(String::toCharArray).toArray(char[][]::new);

            visited = new boolean[grid.length][grid[0].length];

            for (int x = 0; x < grid.length; x++) {
                for (int y = 0; y < grid[0].length; y++) {
                    Region newRegion = new Region(grid[x][y]);
                    regions.add(newRegion);
                    recursive(x, y, newRegion);
                }
            }
            int result = 0;

            for( Region region: regions) {
                result += region.getValue();
            }

            System.out.println(result);
            System.out.println("--------------------------------------");
        }

        private static void recursive(int x, int y, Region region) {
            if(!visited[x][y]) {
                visited[x][y] = true;
                for (int[] d : dir) {
                    try {
                        char thatPlant = grid[x + d[0]][y + d[1]];
                        if (!visited[x + d[0]][y + d[1]] && region.plant == thatPlant) {
                            char that = grid[x + d[0]][y + d[1]];
                            region.area ++;
                        }
                    } catch (Exception ignored) {
                        region.perimeter++;
                    }
                }
            }
        }

        static class Region {
            char plant;
            int area = 1;
            int perimeter = 0;
            boolean visited = false;

            public Region(char plant) {
                this.plant = plant;
            }

            public int getValue() {
                if(visited)
                    return 0;
                visited = true;
                return area * perimeter;
            }
        }
    }

    static class P2 {

        static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 2 ---------------");
            Scanner sc = readFile();

            char[][] _grid = ScannerUtil.toList(sc).stream().map(String::toCharArray).toArray(char[][]::new);
            Region[][] grid = Arrays.stream(_grid).map(gridLine ->
                    IntStream.range(0, gridLine.length).mapToObj(i -> gridLine[i]).map(P1.Region::new).toArray(P1.Region[]::new)
            ).toArray(Region[][]::new);
            boolean[][] visited = new boolean[grid.length][grid[0].length];

            for (int x = 0; x < grid.length; x++) {
                for (int y = 0; y < grid[0].length; y++) {
                    Region _this = grid[x][y];
                    for (int[] d : dir) {
                        try {
                            if (!visited[x + d[0]][y + d[1]]) {
                                Region that = grid[x + d[0]][y + d[1]];
                                grid[x + d[0]][y + d[1]] = that.combine(_this);
                            }
                        } catch (Exception ignored) {
                            _this.perimeter++;
                        }
                    }
                    visited[x][y] = true;
                }
            }
            int result = 0;

            for(Region[] row : grid) {
                for(Region region : row) {
                    result += region.getValue();
                }
            }


            System.out.println(result);
            System.out.println("--------------------------------------");
        }

        public static class Region {
            char plant;
            int area = 1;
            int perimeter = 0;
            boolean visited = false;

            public Region(char plant) {
                this.plant = plant;
            }

            public Region combine(Region that) {
                if(this == that)
                    return this;
                if (this.plant == that.plant) {
                    that.area++;
                    that.perimeter += this.perimeter;
                    return that;
                } else {
                    this.perimeter++;
                    that.perimeter++;
                    return this;
                }
            }

            public int getValue() {
                if(visited)
                    return 0;
                visited = true;
                return area * perimeter;
            }
        }
    }
}