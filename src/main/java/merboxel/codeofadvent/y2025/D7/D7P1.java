package merboxel.codeofadvent.y2025.D7;


import merboxel.codeofadvent.AoC;
import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class D7P1 extends AoC {

    private static final int year = 2025;
    private static final int day = 7;
    private static final int part = 1;

    public D7P1(Scanner sc) throws IOException {
        super(year, day, part, sc);
    }

    public D7P1() throws IOException {
        super(year, day, part);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("--------------- Part 1 ---------------");
        System.out.println(new D7P1().run());
        System.out.println("--------------------------------------");
    }

    public String run() throws IOException {

        char[][] grid = Arrays.stream(ScannerUtil.toArray(sc)).map(String::toCharArray).toArray(char[][]::new);

        long result = 0;

        Set<Integer> beams = new HashSet<>();
        for(int i = 0; i < grid[0].length; i++){
            if(grid[0][i] == 'S'){
                beams.add(i);
            }
        }

        for(char[] row : grid){
            Set<Integer> newBeams = new HashSet<>();
            for(Integer i : beams){
                if(row[i] == '^'){
                    result ++;
                    newBeams.add(i-1);
                    newBeams.add(i+1);
                } else {
                    newBeams.add(i);
                }
            }
            beams = newBeams;
        }

        return Long.toString(result);
    }
}