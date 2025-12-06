package merboxel.codeofadvent.y2025.D4;


import merboxel.codeofadvent.AoC;
import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.*;

public class D4P1 extends AoC {

    private static final int year = 2025;
    private static final int day = 4;
    private static final int part = 1;

    public D4P1(Scanner sc) throws IOException {
        super(year, day, part, sc);
    }

    public D4P1() throws IOException {
        super(year, day, part);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("--------------- Part 1 ---------------");
        System.out.println(new D4P1().run());
        System.out.println("--------------------------------------");
    }

    public String run() throws IOException {

        char[][] grid = Arrays.stream(ScannerUtil.toArray(sc)).map(String::toCharArray).toArray(char[][]::new);

        long result = 0;

        for(int x = 0; x < grid.length; x++){
            for(int y = 0; y < grid[0].length; y++){
                if(grid[x][y] != '@'){
                    continue;
                }
                int tmp = 0;
                tmp += isAtChar(grid, x-1, y-1);
                tmp += isAtChar(grid, x-1, y);
                tmp += isAtChar(grid, x-1, y+1);
                tmp += isAtChar(grid, x, y-1);
                tmp += isAtChar(grid, x, y+1);
                tmp += isAtChar(grid, x+1, y-1);
                tmp += isAtChar(grid, x+1, y);
                tmp += isAtChar(grid, x+1, y+1);

                result += (tmp < 4) ? 1 : 0;
            }
        }

        return Long.toString(result);
    }

    private int isAtChar(char[][] grid, int x, int y) {
        try {
            return (grid[x][y] == '@') ? 1 : 0;
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0;
        }
    }
}