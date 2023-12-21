package merboxel.codeofadvent.y2023;

import merboxel.codeofadvent.util.PatternMatching;

import java.io.IOException;
import java.util.*;
import java.util.stream.LongStream;

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

                boolean[][] curr;
                boolean[][] next;

                if(i % 2 == 0) {
                    curr = even;
                    next = odd;
                } else {
                    curr = odd;
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

            for(int i = 0; i < even.length; i++) {
                for (int j = 0; j < even[i].length; j++) {
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


            System.out.println(result);
            System.out.println("--------------------------------------");
        }

        static class gridElem {
            int x,y;

        }

        public static boolean isValidWalk(char[][] grid, int x, int y) {
            try {
                return grid[y][x] != '#';
            } catch(IndexOutOfBoundsException ignored) { }
            return false;
        }
    }
}