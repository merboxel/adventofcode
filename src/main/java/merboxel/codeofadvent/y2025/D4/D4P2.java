package merboxel.codeofadvent.y2025.D4;


import merboxel.codeofadvent.AoC;
import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class D4P2 extends AoC {

    private static final int year = 2025;
    private static final int day = 4;
    private static final int part = 2;

    public D4P2(Scanner sc) throws IOException {
        super(year, day, part, sc);
    }

    public D4P2() throws IOException {
        super(year, day, part);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("--------------- Part 2 ---------------");
        System.out.println(new D4P2().run());
        System.out.println("--------------------------------------");
    }

    public String run() throws IOException {

        char[][] chars = Arrays.stream(ScannerUtil.toArray(sc)).map(String::toCharArray).toArray(char[][]::new);
        Boolean[][] grid = Arrays.stream(chars)
                .map(line -> new String(line).chars().mapToObj(c -> c == '@').toArray(Boolean[]::new)).toArray(Boolean[][]::new);

        long result = 0;
        long prevResult = 0;
        do {
            prevResult = result;
            Boolean[][] gridCopy = Arrays.stream(grid)
                    .map(Boolean[]::clone)
                    .toArray(Boolean[][]::new);

            for (int x = 0; x < grid.length; x++) {
                for (int y = 0; y < grid[0].length; y++) {
                    if (Boolean.FALSE.equals(grid[x][y])) {
                        continue;
                    }
                    int tmp = 0;
                    tmp += isAtChar(grid, x - 1, y - 1);
                    tmp += isAtChar(grid, x - 1, y);
                    tmp += isAtChar(grid, x - 1, y + 1);
                    tmp += isAtChar(grid, x, y - 1);
                    tmp += isAtChar(grid, x, y + 1);
                    tmp += isAtChar(grid, x + 1, y - 1);
                    tmp += isAtChar(grid, x + 1, y);
                    tmp += isAtChar(grid, x + 1, y + 1);


                    if(tmp < 4) {
                        gridCopy[x][y] = Boolean.FALSE;
                        result += 1;
                    }
                }
            }
            grid = gridCopy;
        } while(prevResult != result);

        return Long.toString(result);
    }

    private int isAtChar(Boolean[][] grid, int x, int y) {
        try {
            return Boolean.TRUE.equals((grid[x][y])) ? 1 : 0;
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0;
        }
    }
}