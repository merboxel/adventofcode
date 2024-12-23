package merboxel.codeofadvent.y2024.D20;


import merboxel.codeofadvent.AoC;
import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;

public class D20P1 extends AoC {

    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int[][] dirForward = {{-2, 0}, {0, 2}, {2, 0}, {0, -2}};

    public D20P1() throws IOException {
        super(2024, 20, 1);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("--------------- Part 1 ---------------");
        new D20P1().run();
        System.out.println("--------------------------------------");
    }

    public String run() throws IOException {

        char[][] grid = Arrays.stream(ScannerUtil.toArray(sc)).map(String::toCharArray).toArray(char[][]::new);
        int[][] visited = new int[grid.length][grid[0].length];

        PriorityQueue<Position> dfs = new PriorityQueue<>();
        Position s = null;

        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                if (grid[x][y] == 'S') {
                    s = new Position(x, y, 1);
                }
            }
        }

        dfs.add(s);
        while (!dfs.isEmpty()) {
            Position p = dfs.poll();
            try {
                if (visited[p.x][p.y] == 0) {
                    visited[p.x][p.y] = p.value;
                    for (int[] d : dir) {
                        Position _p = new Position(p.x + d[0], p.y + d[1], p.value + 1);
                        if (grid[_p.x][_p.y] != '#') {
                            dfs.add(_p);
                        }
                    }
                }
            } catch (Exception ignored) {
            }
        }
        int[] result = new int[10000];

        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                int currentTrack = visited[x][y];
                if (grid[x][y] != '#') {
                    for (int[] d : dirForward) {
                        try {
                            if (grid[x + d[0]][y + d[1]] != '#') {
                                int shortCut = visited[x + d[0]][y + d[1]];
                                result[currentTrack - shortCut - 2]++;
                            }
                        } catch (Exception ignored) {
                        }
                    }
                }
            }
        }
        return Integer.toString(Arrays.stream(result).skip(100).sum());
    }

    private record Position(int x, int y, int value) implements Comparable<Position> {

        @Override
        public int compareTo(Position that) {
            return Integer.compare(this.x, that.x);
        }
    }
}