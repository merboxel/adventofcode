package merboxel.codeofadvent.y2024;


import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D6_2 {

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
            int d = 0;
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
                    if(grid[x + dir[d][0]][y + dir[d][1]] == '#') {
                        d = (d + 1) % 4;
                    } else {
                        x += dir[d][0];
                        y += dir[d][1];
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
        static int x = 0,y = 0, d = 0;
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
                    }
                }
            }

            for(int i = 0; i < grid.length; i++) {
                for(int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == '^' || grid[i][j] == '#') {
                        continue;
                    }
                    grid[i][j] = '#';
                    result += findLoop();
                    grid[i][j] = '.';
                }
            }

            System.out.println(result);
            System.out.println("--------------------------------------");
        }

        public static int findLoop() {

            Set<List<Integer>> visited = new HashSet<>();
            int _x = x, _y = y, _d = d;

            while(true) {
                try {
                    if(visited.contains(List.of(_x,_y,_d))){
                        return 1;
                    }

                    if(grid[_x + dir[_d][0]][_y + dir[_d][1]] == '#') {
                        _d = (_d + 1) % 4;
                    } else {
                        visited.add(List.of(_x,_y,_d));
                        _x += dir[_d][0];
                        _y += dir[_d][1];
                    }
                } catch (Exception ignore) {
                    return 0;
                }
            }
        }
    }
}