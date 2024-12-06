package merboxel.codeofadvent.y2024;


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

            char[][] _grid = ScannerUtil.toList(sc).stream().map(String::toCharArray).toList().toArray(new char[0][]);

            int result = 0;

            for(int i = 0; i < _grid.length; i++) {
                for(int j = 0; j < _grid[i].length; j++) {
                    if (_grid[i][j] == '^') {
                        x = i;
                        y = j;
                        _grid[i][j] = 'X';
                    }
                }
            }

            for(int i = 0; i < _grid.length; i++) {
                for(int j = 0; j < _grid[i].length; j++) {
                    if (_grid[i][j] == '^' || _grid[i][j] == '#') {
                        continue;
                    }
                    copy(_grid);
                    grid[i][j] = '#';
                    result += findLoop();
                }
            }

            System.out.println(result);
            System.out.println("--------------------------------------");
        }

        public static void copy(char[][] _grid) {
            grid = new char[_grid.length][_grid[0].length];
            for(int i = 0; i < _grid.length; i++) {
                System.arraycopy(_grid[i], 0, grid[i], 0, _grid[i].length);
            }
        }

        public static int findLoop() {

            Set<List<Integer>> visited = new HashSet<>();
            int _x = x, _y = y, _face = face;

            while(true) {
                try {
                    if(visited.contains(List.of(_x,_y,_face))){
                        return 1;
                    }

                    if(grid[_x + dir[_face][0]][_y + dir[_face][1]] == '#') {
                        _face = (_face + 1) % 4;
                    } else {
                        visited.add(List.of(_x,_y,_face));
                        _x += dir[_face][0];
                        _y += dir[_face][1];
                        grid[_x][_y] = 'X';
                    }
                } catch (Exception ignore) {
                    return 0;
                }
            }
        }
    }
}