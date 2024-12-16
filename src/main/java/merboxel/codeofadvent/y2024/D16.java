package merboxel.codeofadvent.y2024;


import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D16 {

    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2024, 16);
    }

    static class P1 {
        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 1 ---------------");
            Scanner sc = readFile();

            Position result = null;

            char[][] grid = Arrays.stream(ScannerUtil.toArray(sc)).map(String::toCharArray).toArray(char[][]::new);
            long[][][] visited = new long[grid.length][grid[0].length][4];
            PriorityQueue<Position> dfs = new PriorityQueue<>();

            for(int x = 0; x < grid.length; x++) {
                for(int y = 0; y < grid[0].length; y++) {
                    if(grid[x][y] == 'S') {
                        dfs.add(new Position(x,y,0,1));
                    }
                }
            }

            while(!dfs.isEmpty()) {
                Position pos = dfs.poll();
                if(visited[pos.x][pos.y][pos.dir] > 0)
                    continue;
                visited[pos.x][pos.y][pos.dir] = pos.value;
                if(grid[pos.x][pos.y] == 'E') {
                    result = pos;
                    break;
                }

                int[] d = dir[pos.dir];

                if(grid[pos.x+d[0]][pos.y+d[1]] != '#') {
                    dfs.add(new Position(pos.x+d[0],pos.y+d[1],pos.value+1,pos.dir));
                }
                dfs.add(new Position(pos.x,pos.y,pos.value+1000,Math.floorMod(pos.dir+1,4)));
                dfs.add(new Position(pos.x,pos.y,pos.value+1000,Math.floorMod(pos.dir-1,4)));
            }

            assert result != null;
            System.out.println(result.value);
            System.out.println("--------------------------------------");
        }

        private record Position(
                int x,
                int y,
                long value,
                int dir
        ) implements Comparable<Position> {

            @Override
            public int compareTo(Position that) {
                return Long.compare(this.value, that.value);
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

            Position finish = null;
            int eX;
            int eY;

            char[][] grid = Arrays.stream(ScannerUtil.toArray(sc)).map(String::toCharArray).toArray(char[][]::new);
            long[][][] visited = new long[grid.length][grid[0].length][4];
            PositionTrack[][][] track = new PositionTrack[grid.length][grid[0].length][4];
            PriorityQueue<Position> dfs = new PriorityQueue<>();

            for(int x = 0; x < grid.length; x++) {
                for(int y = 0; y < grid[0].length; y++) {
                    if(grid[x][y] == 'S') {
                        dfs.add(new Position(x,y,0,1, null));
                    }
                    if(grid[x][y] == 'E') {
                        eX = x;
                        eY = y;
                    }
                }
            }

            while(!dfs.isEmpty()) {
                Position pos = dfs.poll();
                if(visited[pos.x][pos.y][pos.dir] == 0) {
                    track[pos.x][pos.y][pos.dir] = new PositionTrack();
                } else if(visited[pos.x][pos.y][pos.dir] < pos.value) {
                    continue;
                }

                track[pos.x][pos.y][pos.dir].addPosition(pos.prev);
                visited[pos.x][pos.y][pos.dir] = pos.value;
                if(grid[pos.x][pos.y] == 'E') {
                    if(finish == null) {
                        finish = pos;
                    }
                    continue;
                }

                int[] d = dir[pos.dir];

                if(grid[pos.x+d[0]][pos.y+d[1]] != '#') {
                    dfs.add(new Position(pos.x+d[0],pos.y+d[1],pos.value+1,pos.dir, pos));
                }
                dfs.add(new Position(pos.x,pos.y,pos.value+1000,Math.floorMod(pos.dir+1,4), pos));
                dfs.add(new Position(pos.x,pos.y,pos.value+1000,Math.floorMod(pos.dir-1,4), pos));
            }

            boolean[][][] bestTiles = new boolean[grid.length][grid[0].length][4];

            dfs = new PriorityQueue<>();
            dfs.add(finish);

            while(!dfs.isEmpty()) {
                Position pos = dfs.poll();
                if(bestTiles[pos.x][pos.y][pos.dir]) {
                    continue;
                }
                bestTiles[pos.x][pos.y][pos.dir] = true;
                dfs.addAll(track[pos.x][pos.y][pos.dir].positions);
            }

            int result = 0;
            for(int x = 0; x < grid.length; x++) {
                middle: for(int y = 0; y < grid[0].length; y++) {
                    for(int dir = 0; dir < 4; dir++) {
                        if(bestTiles[x][y][dir]) {
                            result ++;
                            continue middle;
                        }
                    }
                }
            }
            System.out.println(result);
            System.out.println("--------------------------------------");
        }

        private record Position(
                int x,
                int y,
                long value,
                int dir,
                Position prev
        ) implements Comparable<Position> {

            @Override
            public int compareTo(Position that) {
                return Long.compare(this.value, that.value);
            }
        }

        private static class PositionTrack {
            List<Position> positions = new ArrayList<>();

            public void addAllPositions(List<Position> positions) {
                this.positions.addAll(positions);
            }

            public void addPosition(Position position) {
                positions.add(position);
            }
        }
    }
}