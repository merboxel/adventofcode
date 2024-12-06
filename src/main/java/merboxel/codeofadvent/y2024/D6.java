package merboxel.codeofadvent.y2024;


import merboxel.codeofadvent.util.PatternMatching;
import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.*;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D6 {

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2024, 6);
    }

    static class P1 {
        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 1 ---------------");
            Scanner sc = readFile();

            int[][] dir = {{-1,0}, {0,1}, {1,0}, {0,-1}};
            int x = 0,y = 0;
            int face = 0;
            char[][] grid = ScannerUtil.toList(sc).stream().map(String::toCharArray).toList().toArray(new char[0][]);

            int result = 0;

            for(int i = 0; i < grid.length; i++) {
                for(int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == '^') {
                        x = i;
                        y = j;
                        grid[i][j] = 'X';
                    }
                }
            }

            while(true) {
                try {
                    if(grid[x + dir[face][0]][y + dir[face][1]] == '#') {
                        face = (face + 1) % 4;
                    } else {
                        x += dir[face][0];
                        y += dir[face][1];
                        grid[x][y] = 'X';
                    }
                } catch (Exception ignore) {
                    break;
                }
            }

            for(char[] line: grid) {
                for(char cel : line) {
                    if (cel == 'X') {
                        result ++;
                    }
                }
            }

            System.out.println(result);
            System.out.println("--------------------------------------");
        }
    }

    static class P2 {

        static int[][] dir = {{-1,0}, {0,1}, {1,0}, {0,-1}};
        static int x = 0,y = 0;
        static int face = 0;
        static char[][] grid;

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 2 ---------------");
            Scanner sc = readFile();

            grid = ScannerUtil.toList(sc).stream().map(String::toCharArray).toList().toArray(new char[0][]);

            int result = 0;

            for(int i = 0; i < grid.length; i++) {
                for(int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == '^') {
                        x = i;
                        y = j;
                        grid[i][j] = 'X';
                    }
                }
            }

            while(true) {
                try {
                    if(grid[x + dir[face][0]][y + dir[face][1]] == '#') {
                        face = (face + 1) % 4;
                    } else {
                        x += dir[face][0];
                        y += dir[face][1];
                        grid[x][y] = 'X';
                    }

                    if(foundAlternativeLoop())
                        result ++;
                } catch (Exception ignore) {
                    break;
                }
            }

            System.out.println(result);
            System.out.println("--------------------------------------");
        }

        public static boolean foundAlternativeLoop() {
            int _face = (face + 1) % 4, _x = x, _y = y;

            try {
                while (true) {
                    if (grid[_x + dir[_face][0]][_y + dir[_face][1]] == 'X') {
                        if(grid[_x][_y] == '#') {
                            System.out.println("x,y := [" + (x + dir[face][0]) + "," + (y + dir[face][1] + "]"));
                            return true;
                        } else {
                            _x += dir[_face][0];
                            _y += dir[_face][1];
                        }
                    } else {
                        _x += dir[_face][0];
                        _y += dir[_face][1];
                    }
                }
            } catch (Exception ignored) {}

            return false;
        }
    }
}