package merboxel.codeofadvent.y2024;


import merboxel.codeofadvent.util.ArrayUtils;
import merboxel.codeofadvent.util.PatternMatching;
import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D20 {

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2024, 20);
    }

    static class P1 {

        static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        static int[][] dirForward = {{-2, 0}, {0, 2}, {2, 0}, {0, -2}};

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 1 ---------------");
            Scanner sc = readFile();

            char[][] grid = Arrays.stream(ScannerUtil.toArray(sc)).map(String::toCharArray).toArray(char[][]::new);
            int[][] visited = new int[grid.length][grid[0].length];

            PriorityQueue<Position> dfs = new PriorityQueue<>();
            Position s = null;
            Position e = null;

            for(int x = 0; x < grid.length; x++){
                for(int y = 0; y < grid[0].length; y++){
                    if(grid[x][y] == 'S'){
                        s = new Position(x,y,1);
                    }
                    if(grid[x][y] == 'E'){
                        e = new Position(x,y,1);
                    }
                }
            }

            dfs.add(s);
            while(!dfs.isEmpty()){
                Position p = dfs.poll();
                try {
                    if(visited[p.x][p.y] == 0) {
                        visited[p.x][p.y] = p.value;
                        for (int[] d : dir) {
                            Position _p = new Position(p.x + d[0],p.y + d[1], p.value + 1);
                            if(grid[_p.x][_p.y] != '#'){
                                dfs.add(_p);
                            }
                        }
                    }
                } catch (Exception ignored) {}
            }
            int[] result = new int[10000];

            for(int x = 0; x < grid.length; x++){
                for(int y = 0; y < grid[0].length; y++){
                    int currentTrack = visited[x][y];
                    if(grid[x][y] != '#') {
                        for (int[] d : dirForward) {
                            try {
                                if (grid[x + d[0]][y + d[1]] != '#') {
                                    int shortCut = visited[x + d[0]][y + d[1]];
                                    result[currentTrack - shortCut -2] ++;
                                }
                            } catch (Exception ignored) {}
                        }
                    }
                }
            }
            System.out.println(Arrays.stream(result).skip(100).sum());
            System.out.println("--------------------------------------");
        }

        private record Position(
                int x,
                int y,
                int value
        ) implements Comparable<Position> {

            @Override
            public int compareTo(Position that) {
                return Integer.compare(this.x, that.x);
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

            char[][] grid = Arrays.stream(ScannerUtil.toArray(sc)).map(String::toCharArray).toArray(char[][]::new);
            int[][] visited = new int[grid.length][grid[0].length];

            PriorityQueue<Position> dfs = new PriorityQueue<>();
            Position s = null;
            Position e = null;

            for(int x = 0; x < grid.length; x++){
                for(int y = 0; y < grid[0].length; y++){
                    if(grid[x][y] == 'S'){
                        s = new Position(x,y,1);
                    }
                    if(grid[x][y] == 'E'){
                        e = new Position(x,y,1);
                    }
                }
            }

            dfs.add(s);
            while(!dfs.isEmpty()){
                Position p = dfs.poll();
                try {
                    if(visited[p.x][p.y] == 0) {
                        visited[p.x][p.y] = p.value;
                        for (int[] d : dir) {
                            Position _p = new Position(p.x + d[0],p.y + d[1], p.value + 1);
                            if(grid[_p.x][_p.y] != '#'){
                                dfs.add(_p);
                            }
                        }
                    }
                } catch (Exception ignored) {}
            }
            int[] result = new int[10000];

            for(int x = 0; x < grid.length; x++){
                for(int y = 0; y < grid[0].length; y++){
                    int currentTrack = visited[x][y];
                    if(grid[x][y] != '#') {
                        for(int dx = -20; dx <= 20; dx++){
                            for(int dy = -20; dy <= 20; dy++){
                                int cheatActive = Math.abs(dx) + Math.abs(dy);
                                if(cheatActive <= 20){
                                    try{
                                        if (grid[x + dx][y + dy] != '#') {
                                            int shortCut = visited[x + dx][y + dy];
                                            result[currentTrack - shortCut - cheatActive] ++;
                                        }
                                    } catch (Exception ignored) {}
                                }
                            }
                        }
                    }
                }
            }
            System.out.println(Arrays.stream(result).skip(100).sum());
            System.out.println("--------------------------------------");
        }

        private record Position(
                int x,
                int y,
                int value
        ) implements Comparable<Position> {

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
}