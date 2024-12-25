package merboxel.codeofadvent.y2024.D20;


import merboxel.codeofadvent.AoC;
import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class D20P2 extends AoC {

    private static final int year = 2024;
    private static final int day = 20;
    private static final int part = 2;

    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public D20P2(Scanner sc) {
        super(year, day, part, sc);
    }

    public D20P2() throws IOException {
        super(year, day, part);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("--------------- Part 2 ---------------");
        System.out.println(new D20P2().run());
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

        assert (s != null);

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
                    for (int dx = -20; dx <= 20; dx++) {
                        for (int dy = -20; dy <= 20; dy++) {
                            int cheatActive = Math.abs(dx) + Math.abs(dy);
                            if (cheatActive <= 20) {
                                try {
                                    if (grid[x + dx][y + dy] != '#') {
                                        int shortCut = visited[x + dx][y + dy];
                                        result[currentTrack - shortCut - cheatActive]++;
                                    }
                                } catch (Exception ignored) {
                                }
                            }
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

        @Override
        public boolean equals(Object o) {

            if (this == o) {
                return true;
            }
            if (o == null) {
                return false;
            }
            if (!(o instanceof Position that)) {
                return false;
            }
            return this.x == that.x && this.y == that.y;
        }
    }
}