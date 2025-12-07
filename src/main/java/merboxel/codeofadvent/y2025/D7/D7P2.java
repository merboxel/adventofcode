package merboxel.codeofadvent.y2025.D7;


import merboxel.codeofadvent.AoC;
import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.*;

public class D7P2 extends AoC {

    private static final int year = 2025;
    private static final int day = 7;
    private static final int part = 2;

    public D7P2(Scanner sc) throws IOException {
        super(year, day, part, sc);
    }

    public D7P2() throws IOException {
        super(year, day, part);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("--------------- Part 2 ---------------");
        System.out.println(new D7P2().run());
        System.out.println("--------------------------------------");
    }

    public String run() throws IOException {

        char[][] grid = Arrays.stream(ScannerUtil.toArray(sc)).map(String::toCharArray).toArray(char[][]::new);

        HashMap<Integer,Long> beams = new HashMap<>();
        for(int i = 0; i < grid[0].length; i++){
            if(grid[0][i] == 'S'){
                beams.put(i,1L);
            }
        }

        for(char[] row : grid){
            HashMap<Integer,Long> newBeams = new HashMap<>();
            beams.forEach((k,v)->{
                if(row[k] == '^'){
                    long left = newBeams.getOrDefault(k-1,0L);
                    newBeams.put(k-1,left+v);
                    long right = newBeams.getOrDefault(k+1,0L);
                    newBeams.put(k+1,right+v);
                } else {
                    long middle =  newBeams.getOrDefault(k,0L);
                    newBeams.put(k,middle+v);
                }
            });
            beams = newBeams;
        }
        long result = beams.values().stream().mapToLong(Long::longValue).sum();

        return Long.toString(result);
    }
}