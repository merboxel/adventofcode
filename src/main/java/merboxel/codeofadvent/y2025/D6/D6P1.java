package merboxel.codeofadvent.y2025.D6;


import merboxel.codeofadvent.AoC;
import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.*;

public class D6P1 extends AoC {

    private static final int year = 2025;
    private static final int day = 6;
    private static final int part = 1;

    public D6P1(Scanner sc) throws IOException {
        super(year, day, part, sc);
    }

    public D6P1() throws IOException {
        super(year, day, part);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("--------------- Part 1 ---------------");
        System.out.println(new D6P1().run());
        System.out.println("--------------------------------------");
    }

    public String run() throws IOException {

        String[] lines = ScannerUtil.toArray(sc);

        long[][] numbers = Arrays.stream(Arrays.copyOf(lines, lines.length - 1))
                .map(line -> line.trim().split("\\s+"))
                .map(arr -> Arrays.stream(arr).mapToLong(Long::parseLong).toArray())
                .toArray(long[][]::new);

        long result = 0;

        String[] operator = lines[lines.length-1].trim().split("\\s+");

        for(int i = 0; i < operator.length; i++) {
            long tmp =  numbers[0][i];
            for(int j = 1; j < numbers.length; j++) {
                if(operator[i].equals("*")) {
                    tmp *= numbers[j][i];
                } else if(operator[i].equals("+")) {
                    tmp += numbers[j][i];
                }
            }
            result += tmp;
        }

        return Long.toString(result);
    }
}