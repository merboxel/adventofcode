package merboxel.codeofadvent.y2024;


import merboxel.codeofadvent.structure.Point;
import merboxel.codeofadvent.util.PatternMatching;
import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.*;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D18 {

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2024, 18);
    }

    static class P1 {
        static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 1 ---------------");
            Scanner sc = readFile();

            int result = 0;

            char[][] grid = new char[71][71];

            for(int i = 0; i < 1024; i++) {
                String[] c = sc.nextLine().split(",");
                grid[Integer.parseInt(c[0])][Integer.parseInt(c[1])] = '#';
            }

            Set<_Point> r = new HashSet<>();
            Set<_Point> _r = new HashSet<>();
            r.add(new _Point(0,0));
            outer : while(true) {
                for (_Point p : r) {
                    if (p.x == grid.length - 1 && p.y == grid[0].length - 1) {
                        break outer;
                    }

                    for(int[] dir : dir) {
                        try {
                            if(grid[(int) (p.x + dir[0])][(int) (p.y + dir[1])] == 0)
                                _r.add(new _Point(p.x + dir[0], p.y + dir[1]));
                        } catch (Exception ignored) {}
                    }
                }
                result ++;
                r = _r;
                _r = new HashSet<>();
            }

            System.out.println(result);
            System.out.println("--------------------------------------");
        }

        public static class _Point extends Point implements Comparable<_Point> {

            _Point(long x, long y) {
                super(x, y);
            }

            @Override
            public int compareTo(_Point o) {
                return this.hashCode() - o.hashCode();
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

            int result = 0;

            char[][] grid = new char[71][71];

            for(int i = 0; i < 1024; i++) {
                String[] c = sc.nextLine().split(",");
                grid[Integer.parseInt(c[0])][Integer.parseInt(c[1])] = '#';
            }

            outer2 : for(int i = 0; i < 2426; i++) {

                result = 0;
                String[] c = sc.nextLine().split(",");
                grid[Integer.parseInt(c[0])][Integer.parseInt(c[1])] = '#';
                boolean[][] visited = new boolean[grid.length][grid[0].length];

                Set<_Point> r = new HashSet<>();
                Set<_Point> _r = new HashSet<>();
                r.add(new _Point(0,0));
                outer : while(true) {
                    for (_Point p : r) {
                        if(visited[(int) p.x][(int) p.y]) {
                            continue;
                        }
                        visited[(int) p.x][(int) p.y] = true;
                        if (p.x == grid.length - 1 && p.y == grid[0].length - 1) {
                            break outer;
                        }

                        for(int[] dir : dir) {
                            try {
                                if(grid[(int) (p.x + dir[0])][(int) (p.y + dir[1])] == 0)
                                    _r.add(new _Point(p.x + dir[0], p.y + dir[1]));
                            } catch (Exception ignored) {}
                        }
                    }
                    result ++;
                    r = _r;
                    _r = new HashSet<>();

                    if (result > 4900) {
                        result = 1024 + i + 1;
                        System.out.println(Arrays.toString(c));
                        break outer2;
                    }
                }
            }

            System.out.println(result);
            System.out.println(result);
            System.out.println("--------------------------------------");
        }

        public static class _Point extends Point implements Comparable<P1._Point> {

            _Point(long x, long y) {
                super(x, y);
            }

            @Override
            public int compareTo(P1._Point o) {
                return this.hashCode() - o.hashCode();
            }
        }
    }
}