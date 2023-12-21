package merboxel.codeofadvent.y2023;

import java.io.IOException;
import java.util.*;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D21 {

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2023, 21);
    }

    static class P1 {

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 1 ---------------");
            long result = 0;
            Scanner sc = readFile();

            List<String> lines = new ArrayList<>();

            while(sc.hasNext()) {
                lines.add(sc.nextLine());
            }

            char[][] grid = lines.stream().map(String::toCharArray).toArray(char[][]::new);

            boolean[][] even = new boolean[grid.length][grid[0].length];
            boolean[][] odd = new boolean[grid.length][grid[0].length];

            Stack<Integer> xs = new Stack<>();
            Stack<Integer> ys = new Stack<>();

            outer: for(int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == 'S') {
                        ys.add(i);
                        xs.add(j);
                        break outer;
                    }
                }
            }

            for(int i = 1; i <= 64; i ++) {
                Stack<Integer> _xs = xs;
                Stack<Integer> _ys = ys;

                xs = new Stack<>();
                ys = new Stack<>();

                boolean[][] next;

                if(i % 2 == 0) {
                    next = odd;
                } else {
                    next = even;
                }

                while(!_xs.isEmpty()) {
                    int x = _xs.pop();
                    int y = _ys.pop();

                    if(isValidWalk(grid,x-1,y)) {
                        if(!next[y][x-1]) {
                            xs.add(x-1);
                            ys.add(y);
                        }
                        next[y][x-1] = true;
                    }
                    if(isValidWalk(grid,x+1,y)) {
                        if(!next[y][x+1]) {
                            xs.add(x+1);
                            ys.add(y);
                        }
                        next[y][x+1] = true;
                    }
                    if(isValidWalk(grid,x,y-1)) {
                        if(!next[y-1][x]) {
                            xs.add(x);
                            ys.add(y-1);
                        }
                        next[y-1][x] = true;
                    }
                    if(isValidWalk(grid,x,y+1)) {
                        if(!next[y+1][x]) {
                            xs.add(x);
                            ys.add(y+1);
                        }
                        next[y+1][x] = true;
                    }
                }
            }

            for(int i = 0; i < odd.length; i++) {
                for (int j = 0; j < odd[i].length; j++) {
                    if(odd[i][j])
                        result ++;
                }
            }

            System.out.println(result);
            System.out.println("--------------------------------------");
        }

        public static boolean isValidWalk(char[][] grid, int x, int y) {
            try {
                return grid[y][x] != '#';
            } catch(IndexOutOfBoundsException ignored) { }
            return false;
        }
    }


    /**
     * Observation of 'test' data:
     * - Grid is 33x10
     * - 16x5 is middle
     * - Going left/right/up/down will result in hitting a wall!
     *
     * Observations of 'real' data:
     * - Grid is 131x131
     * - 65,65 is middle of grid.
     * - Going either left/right/up/down will not result in hitting a wall (from start S)
     *
     * 'Test' data is way more difficult than 'real' data!!!! So we skip 'test' data?
     *
     *  Walking left/right/up/down makes you reach 202300 additional grids in each direction exactly.
     *  You reach the next grid (left/right/up/down) after 66 steps (from start point)
     *  - You reach the next grid after (going same direction) after 132 steps
     *  You reach the next grid (top-right/top-left/bot-right/bot-left) after 131 steps (from start point)
     *  - You reach the next grid after (going same direction) after 131 steps
     *
     *  Tactic:
     *  - Starting in each corner how long does it take to fill the whole map
     *  -- Create a layout with all possible steps after i iterations
     *  - Starting on each edge middle point (65) how long does it take to fill the whole map
     *  -- Create a layout with all possible steps after i iterations
     *
     */
    static class P2 {

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 2 ---------------");
            long result = 0;
            Scanner sc = readFile();

            List<String> lines = new ArrayList<>();

            while(sc.hasNext()) {
                lines.add(sc.nextLine());
            }

            char[][] grid = lines.stream().map(String::toCharArray).toArray(char[][]::new);
            int[][] state = new int[grid.length][grid[0].length];

            Stack<Integer> xs = new Stack<>();
            Stack<Integer> ys = new Stack<>();

            outer: for(int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == 'S') {
                        ys.add(i);
                        xs.add(j);
                        break outer;
                    }
                }
            }

            for(int i = 1; i < 200; i ++) {
                Stack<Integer> _xs = xs;
                Stack<Integer> _ys = ys;

                xs = new Stack<>();
                ys = new Stack<>();

                if(_xs.isEmpty()) {
                    break;
                }

                while(!_xs.isEmpty()) {
                    int x = _xs.pop();
                    int y = _ys.pop();

                    if(isValidWalk(grid,x-1,y) && state[y][x-1] == 0) {
                        xs.add(x-1);
                        ys.add(y);
                        state[y][x-1] = i;
                    }
                    if(isValidWalk(grid,x+1,y) && state[y][x+1] == 0) {
                        xs.add(x+1);
                        ys.add(y);
                        state[y][x+1] = i;
                    }
                    if(isValidWalk(grid,x,y-1) && state[y-1][x] == 0) {
                        xs.add(x);
                        ys.add(y-1);
                        state[y-1][x] = i;
                    }
                    if(isValidWalk(grid,x,y+1) && state[y+1][x] == 0) {
                        xs.add(x);
                        ys.add(y+1);
                        state[y+1][x] = i;
                    }
                }
            }
            long n = 26501365L/131;

            long even = Arrays.stream(state).flatMapToInt(elem -> Arrays.stream(elem).filter(i-> i % 2 == 0 && i != 0)).count();
            long odd = Arrays.stream(state).flatMapToInt(elem -> Arrays.stream(elem).filter(i-> i % 2 == 1)).count();

            long evenCorners = Arrays.stream(state).flatMapToInt(elem -> Arrays.stream(elem).filter(i-> i % 2 == 0 && i > 65)).count();
            long oddCorners = Arrays.stream(state).flatMapToInt(elem -> Arrays.stream(elem).filter(i-> i % 2 == 1 && i > 65)).count();

            result += ((n+1)*(n+1L))    * odd;
            result += (n*n)             * even;
            result -= (n+1L)            * oddCorners;
            result += (n)               * evenCorners;

            System.out.println(result);
            System.out.println("--------------------------------------");
        }

        public static boolean isValidWalk(char[][] grid, int x, int y) {
            try {
                return grid[y][x] != '#';
            } catch(IndexOutOfBoundsException ignored) { }
            return false;
        }
    }
}