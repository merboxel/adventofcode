package merboxel.codeofadvent.y2025.D2;


import merboxel.codeofadvent.AoC;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class D2P1 extends AoC {

    private static final int year = 2025;
    private static final int day = 2;
    private static final int part = 1;

    static final long[] POWERS_OF_10 = {1L, 10L, 100L, 1000L, 10000L, 100000L, 1000000L, 10000000L, 100000000L, 1000000000L, 10000000000L, 100000000000L, 1000000000000L, 10000000000000L};

    public D2P1(Scanner sc) throws IOException {
        super(year, day, part, sc);
    }

    public D2P1() throws IOException {
        super(year, day, part);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("--------------- Part 2 ---------------");
        System.out.println(new D2P1().run());
        System.out.println("--------------------------------------");
    }

    public String run() throws IOException {

        String[][] ranges = Arrays.stream(sc.nextLine().split(","))
                .map(range -> range.split("-"))
                .toArray(String[][]::new);

        long result = 0;

        for(String[] range : ranges){
            long start = Long.parseLong(range[0]);
            long end = Long.parseLong(range[1]);

            int middle = range[0].length()/2;
            middle += (range[0].length() % 2 == 1) ? 1 : 0;

            long increment = POWERS_OF_10[middle] + 1;
            long total = increment;

            while(total <= end) {
                if(Long.toString(total).length() % 2 == 0 && total >= start) {
                    result += total;
                }
                total += increment;
            }
        }

        return Long.toString(result);
    }
}