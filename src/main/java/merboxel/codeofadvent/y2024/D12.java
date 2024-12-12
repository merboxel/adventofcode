package merboxel.codeofadvent.y2024;


import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
                    if (!visited[x][y]) {
                        Region newRegion = new Region(grid[x][y]);
                        regions.add(newRegion);
                        recursive(x, y, newRegion);
                    }
                }
            }
            int result = 0;

            for (Region region : regions) {
                result += region.getValue();
            }

            System.out.println(result);
            System.out.println("--------------------------------------");
        }

        private static void recursive(int x, int y, Region region) {
            if (!visited[x][y]) {
                visited[x][y] = true;
                for (int[] d : dir) {
                    try {
                        char thatPlant = grid[x + d[0]][y + d[1]];
                        if (thatPlant == region.plant && !visited[x + d[0]][y + d[1]]) {
                            region.area++;
                            recursive(x + d[0], y + d[1], region);
                        } else if (thatPlant != region.plant) {
                            region.perimeter++;
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
                if (visited)
                    return 0;
                visited = true;
                return area * perimeter;
            }
        }
    }

    static class P2 {

        static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        static char[][] grid;
        static Region[][] gridRegions;
        static boolean[][] visited;
        static List<Region> regions = new ArrayList<>();

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 2 ---------------");
            Scanner sc = readFile();

            grid = ScannerUtil.toList(sc).stream().map(String::toCharArray).toArray(char[][]::new);

            visited = new boolean[grid.length][grid[0].length];
            gridRegions = new Region[grid.length][grid[0].length];

            for (int x = 0; x < grid.length; x++) {
                for (int y = 0; y < grid[0].length; y++) {
                    if (!visited[x][y]) {
                        Region newRegion = new Region(grid[x][y]);
                        regions.add(newRegion);
                        recursive(x, y, newRegion);
                    }
                }
            }

            Node[][] nodes = new Node[grid.length][grid[0].length];

            for (int x = 0; x < grid.length; x++) {
                for (int y = 0; y < grid[0].length; y++) {
                    nodes[x][y] = new Node(gridRegions[x][y]);
                }
            }

            for (int x = 0; x < grid.length; x++) {
                for (int y = 0; y < grid[0].length; y++) {
                    Node curr = nodes[x][y];
                    try {
                        if (nodes[x - 1][y].region == curr.region) {
                            curr.N = true;
                        }
                    } catch (Exception ignored) {}
                    try {
                        if (nodes[x + 1][y].region == curr.region) {
                            curr.S = true;
                        }
                    } catch (Exception ignored) {}
                    try {
                        if (nodes[x][y - 1].region == curr.region) {
                            curr.W = true;
                        }
                    } catch (Exception ignored) {}
                    try {
                        if (nodes[x][y + 1].region == curr.region) {
                            curr.E = true;
                        }
                    } catch (Exception ignored) {}
                }
            }

            for(int x = 0; x < grid.length; x++) {
                Region currRegion = null;
                boolean fence = true;
                for(int y = 0; y < grid[0].length; y++) {
                    if(currRegion != nodes[x][y].region) {
                        fence = true;
                        currRegion = nodes[x][y].region;
                        if(!nodes[x][y].N) {
                            fence = true;
                            currRegion.perimeter ++;
                        } else {
                            fence = false;
                        }
                    } else if (nodes[x][y].N){
                        fence = false;
                    } else if(!fence) {
                        fence = true;
                        currRegion.perimeter ++;
                    }
                }
            }

            for(int x = 0; x < grid.length; x++) {
                Region currRegion = null;
                boolean fence = true;
                for(int y = 0; y < grid[0].length; y++) {
                    if(currRegion != nodes[x][y].region) {
                        currRegion = nodes[x][y].region;
                        if(!nodes[x][y].S) {
                            fence = true;
                            currRegion.perimeter ++;
                        } else {
                            fence = false;
                        }
                    } else if (nodes[x][y].S){
                        fence = false;
                    } else if(!fence) {
                        fence = true;
                        currRegion.perimeter ++;
                    }
                }
            }

            for(int x = 0; x < grid[0].length; x++) {
                Region currRegion = null;
                boolean fence = true;
                for(int y = 0; y < grid.length; y++) {
                    if(currRegion != nodes[y][x].region) {
                        fence = true;
                        currRegion = nodes[y][x].region;
                        if(!nodes[y][x].W) {
                            fence = true;
                            currRegion.perimeter ++;
                        } else {
                            fence = false;
                        }
                    } else if (nodes[y][x].W){
                        fence = false;
                    } else if(!fence) {
                        fence = true;
                        currRegion.perimeter ++;
                    }
                }
            }

            for(int x = 0; x < grid[0].length; x++) {
                Region currRegion = null;
                boolean fence = true;
                for(int y = 0; y < grid.length; y++) {
                    if(currRegion != nodes[y][x].region) {
                        fence = true;
                        currRegion = nodes[y][x].region;
                        if(!nodes[y][x].E) {
                            fence = true;
                            currRegion.perimeter ++;
                        } else {
                            fence = false;
                        }
                    } else if (nodes[y][x].E){
                        fence = false;
                    } else if(!fence) {
                        fence = true;
                        currRegion.perimeter ++;
                    }
                }
            }

            int result = 0;

            for (Region region : regions) {
                result += region.getValue();
            }

            System.out.println(result);
            System.out.println("--------------------------------------");
        }

        private static void recursive(int x, int y, Region region) {
            if (!visited[x][y]) {
                visited[x][y] = true;
                gridRegions[x][y] = region;
                for (int[] d : dir) {
                    try {
                        char thatPlant = grid[x + d[0]][y + d[1]];
                        if (thatPlant == region.plant && !visited[x + d[0]][y + d[1]]) {
                            region.area++;
                            recursive(x + d[0], y + d[1], region);
                        }
                    } catch (Exception ignored) {
                    }
                }
            }
        }

        static class Node {
            Region region;
            boolean N, E, S, W;

            public Node(Region region) {
                this.region = region;
            }
        }

        static class Region {
            char plant;
            int area = 1;
            int perimeter = 0;

            public Region(char plant) {
                this.plant = plant;
            }

            public void calculatePerimeter() {

            }

            public int getValue() {
                return area * perimeter;
            }
        }
    }
}