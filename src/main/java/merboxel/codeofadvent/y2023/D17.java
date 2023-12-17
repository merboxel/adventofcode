package merboxel.codeofadvent.y2023;


import java.io.IOException;
import java.util.*;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D17 {

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2023, 17);
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
            int[][] visited = new int[grid.length][grid[0].length];

            Queue<Position> queue = new PriorityQueue<>();
            queue.add(new Position(new int[] {0,0},true,0));
            queue.add(new Position(new int[] {0,0},false,0));

            while(!queue.isEmpty()) {
                Position curr = queue.poll();
                if(curr.reachedEnd(grid[0].length-1,grid.length-1)) {
                    result = curr.heatLoss;
                    break;
                }

                if(curr.horizontal) {
                    if((visited[curr.pos[0]][curr.pos[1]] & 1) == 1)
                        continue;
                    else
                        visited[curr.pos[0]][curr.pos[1]] += 1;
                }
                if(!curr.horizontal) {
                    if((visited[curr.pos[0]][curr.pos[1]] & 2) == 2)
                        continue;
                    else
                        visited[curr.pos[0]][curr.pos[1]] += 2;
                }

                if(curr.horizontal){
                    try{
                        int heatLoss = curr.heatLoss;
                        for(int i = 1; i<=3; i++) {
                            heatLoss += Integer.parseInt(grid[curr.pos[1]+i][curr.pos[0]]+"");
                            queue.add(new Position(new int[] {curr.pos[0],curr.pos[1]+i}, false,heatLoss));
                        }
                    } catch (ArrayIndexOutOfBoundsException ignored) { }
                    try{
                        int heatLoss = curr.heatLoss;
                        for(int i = -1; i>=-3; i--) {
                            heatLoss += Integer.parseInt(grid[curr.pos[1]+i][curr.pos[0]]+"");
                            queue.add(new Position(new int[] {curr.pos[0],curr.pos[1]+i}, false,heatLoss));
                        }
                    } catch (ArrayIndexOutOfBoundsException ignored) { }
                } else {
                    try{
                        int heatLoss = curr.heatLoss;
                        for(int i = 1; i<=3; i++) {
                            heatLoss += Integer.parseInt(grid[curr.pos[1]][curr.pos[0]+i]+"");
                            queue.add(new Position(new int[] {curr.pos[0]+i,curr.pos[1]}, true,heatLoss));
                        }
                    } catch (ArrayIndexOutOfBoundsException ignored) { }
                    try{
                        int heatLoss = curr.heatLoss;
                        for(int i = -1; i>=-3; i--) {
                            heatLoss += Integer.parseInt(grid[curr.pos[1]][curr.pos[0]+i]+"");
                            queue.add(new Position(new int[] {curr.pos[0]+i,curr.pos[1]}, true,heatLoss));
                        }
                    } catch (ArrayIndexOutOfBoundsException ignored) { }
                }
            }

            System.out.println(result);
            System.out.println("--------------------------------------");
        }

        static class Position implements Comparable<Position> {
            int heatLoss;
            int[] pos;
            boolean horizontal;

            public Position(int[] pos, boolean horizontal, int heatLoss) {
                this.pos = pos;
                this.horizontal = horizontal;
                this.heatLoss = heatLoss;
            }

            public boolean reachedEnd(int endX, int endY) {
                return pos[0] == endX && pos[1] == endY;
            }

            @Override
            public int compareTo(Position that) {
                return Integer.compare(this.heatLoss,that.heatLoss);
            }
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

            List<String> lines = new ArrayList<>();

            while (sc.hasNext()) {
                lines.add(sc.nextLine());
            }
            char[][] grid = lines.stream().map(String::toCharArray).toArray(char[][]::new);
            int[][] visited = new int[grid.length][grid[0].length];

            Queue<Position> queue = new PriorityQueue<>();
            queue.add(new Position(new int[] {0,0},true,0));
            queue.add(new Position(new int[] {0,0},false,0));

            while(!queue.isEmpty()) {
                Position curr = queue.poll();
                if(curr.reachedEnd(grid[0].length-1,grid.length-1)) {
                    result = curr.heatLoss;
                    break;
                }
                try {
                    if (curr.horizontal) {
                        if ((visited[curr.pos[1]][curr.pos[0]] & 1) == 1)
                            continue;
                        else
                            visited[curr.pos[1]][curr.pos[0]] += 1;
                    }
                    if (!curr.horizontal) {
                        if ((visited[curr.pos[1]][curr.pos[0]] & 2) == 2)
                            continue;
                        else
                            visited[curr.pos[1]][curr.pos[0]] += 2;
                    }
                } catch (IndexOutOfBoundsException ignored) { continue; }
                if(curr.horizontal){
                    try{
                        int heatLoss = curr.heatLoss;
                        for(int i = 1; i<=3; i++)
                            heatLoss += Integer.parseInt(grid[curr.pos[1]+i][curr.pos[0]]+"");
                        for(int i = 4; i<=10; i++) {
                            heatLoss += Integer.parseInt(grid[curr.pos[1]+i][curr.pos[0]]+"");
                            queue.add(new Position(new int[] {curr.pos[0],curr.pos[1]+i}, false,heatLoss));
                        }
                    } catch (ArrayIndexOutOfBoundsException ignored) { }
                    try{
                        int heatLoss = curr.heatLoss;
                        for(int i = -1; i>=-3; i--)
                            heatLoss += Integer.parseInt(grid[curr.pos[1]+i][curr.pos[0]]+"");
                        for(int i = -4; i>=-10; i--) {
                            heatLoss += Integer.parseInt(grid[curr.pos[1]+i][curr.pos[0]]+"");
                            queue.add(new Position(new int[] {curr.pos[0],curr.pos[1]+i}, false,heatLoss));
                        }
                    } catch (ArrayIndexOutOfBoundsException ignored) { }
                } else {
                    try{
                        int heatLoss = curr.heatLoss;
                        for(int i = 1; i<=3; i++)
                            heatLoss += Integer.parseInt(grid[curr.pos[1]][curr.pos[0]+i]+"");
                        for(int i = 4; i<=10; i++) {
                            heatLoss += Integer.parseInt(grid[curr.pos[1]][curr.pos[0]+i]+"");
                            queue.add(new Position(new int[] {curr.pos[0]+i,curr.pos[1]}, true,heatLoss));
                        }
                    } catch (ArrayIndexOutOfBoundsException ignored) { }
                    try{
                        int heatLoss = curr.heatLoss;
                        for(int i = -1; i>=-3; i--)
                            heatLoss += Integer.parseInt(grid[curr.pos[1]][curr.pos[0]+i]+"");
                        for(int i = -4; i>=-10; i--) {
                            heatLoss += Integer.parseInt(grid[curr.pos[1]][curr.pos[0]+i]+"");
                            queue.add(new Position(new int[] {curr.pos[0]+i,curr.pos[1]}, true,heatLoss));
                        }
                    } catch (ArrayIndexOutOfBoundsException ignored) { }
                }
            }

            System.out.println(result);
            System.out.println("--------------------------------------");
        }

        static class Position implements Comparable<Position> {
            int heatLoss;
            int[] pos;
            boolean horizontal;

            public Position(int[] pos, boolean horizontal, int heatLoss) {
                this.pos = pos;
                this.horizontal = horizontal;
                this.heatLoss = heatLoss;
            }

            public boolean reachedEnd(int endX, int endY) {
                return pos[0] == endX && pos[1] == endY;
            }

            @Override
            public int compareTo(Position that) {
                return Integer.compare(this.heatLoss,that.heatLoss);
            }
        }
    }
}