package merboxel.codeofadvent.y2025.D7;


import merboxel.codeofadvent.AoC;
import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class D7P2_2 extends AoC {

    private static final int year = 2025;
    private static final int day = 7;
    private static final int part = 2;

    public D7P2_2(Scanner sc) throws IOException {
        super(year, day, part, sc);
    }

    public D7P2_2() throws IOException {
        super(year, day, part);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("--------------- Part 2 ---------------");
        System.out.println(new D7P2_2().run());
        System.out.println("--------------------------------------");
    }

    public String run() throws IOException {

        char[][] grid = Arrays.stream(ScannerUtil.toArray(sc)).map(String::toCharArray).toArray(char[][]::new);

        long[] beams = new long[grid[0].length];
        for(int i = 0; i < grid[0].length; i++){
            if(grid[0][i] == 'S'){
                beams[i] = 1L;
            }
        }

        for(char[] row : grid){
            long[] newBeams = new long[row.length];
            for(int i = 0; i < row.length; i++){
                if(row[i] == '^'){
                    newBeams[i-1] += beams[i];
                    newBeams[i+1] += beams[i];
                } else {
                    newBeams[i] += beams[i];
                }
            }
            beams = newBeams;
        }
        long result = Arrays.stream(beams).sum();

        return Long.toString(result);
    }
}