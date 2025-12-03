package merboxel.codeofadvent.y2025.D3;


import merboxel.codeofadvent.AoC;
import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class D3P2 extends AoC {

    private static final int year = 2025;
    private static final int day = 3;
    private static final int part = 2;

    private long[][] dp;
    private boolean[][] visited;

    private static final long[] POWERS_OF_10 = {1L, 10L, 100L, 1000L, 10000L, 100000L, 1000000L, 10000000L, 100000000L, 1000000000L, 10000000000L, 100000000000L, 1000000000000L, 10000000000000L};
    private static final int BATTERY_LENGTH = 12;

    public D3P2(Scanner sc) throws IOException {
        super(year, day, part, sc);
    }

    public D3P2() throws IOException {
        super(year, day, part);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("--------------- Part 2 ---------------");
        System.out.println(new D3P2().run());
        System.out.println("--------------------------------------");
    }

    public String run() throws IOException {

        int[][] ranges = Arrays.stream(ScannerUtil.toArray(sc))
                .map(String::chars)
                .map(intStream -> intStream.map(i -> i - '0').toArray())
                .toArray(int[][]::new);

        long result = 0;

        for(int[] range : ranges){

            int[] reverseRange = new int[range.length];
            for(int i = 0; i < reverseRange.length; i++){
                reverseRange[i] = range[range.length - i - 1];
            }

            dp = new long[BATTERY_LENGTH+1][reverseRange.length+1];
            visited = new boolean[BATTERY_LENGTH+1][reverseRange.length+1];

            Arrays.fill(visited[BATTERY_LENGTH], true);

            for(int i = 0; i < visited.length; i++){
                visited[i][visited[i].length-1] = true;
            }

            long tmp = recursive(reverseRange, 0, 0);
            System.out.println(tmp);
            result += tmp;
        }

        return Long.toString(result);
    }

    private long recursive(int[] battery, int x, int y) {
        if(visited[x][y])
            return dp[x][y];

        long takeY = recursive(battery, x+1, y+1) + battery[y] * POWERS_OF_10[x];
        long skipY = recursive(battery, x, y+1);

        visited[x][y] = true;
        dp[x][y] = Math.max(takeY, skipY);

        return dp[x][y];
    }
}