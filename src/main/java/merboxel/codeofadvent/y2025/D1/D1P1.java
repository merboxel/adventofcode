package merboxel.codeofadvent.y2025.D1;


import merboxel.codeofadvent.AoC;
import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class D1P1 extends AoC {

    private static final int year = 2025;
    private static final int day = 1;
    private static final int part = 1;

    public D1P1(Scanner sc) throws IOException {
        super(year, day, part, sc);
    }

    public D1P1() throws IOException {
        super(year, day, part);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("--------------- Part 2 ---------------");
        System.out.println(new D1P1().run());
        System.out.println("--------------------------------------");
    }

    public String run() throws IOException {

        int result = 0;
        int pointer = 50;

        List<String> lines = ScannerUtil.toList(sc);

        for(String line: lines) {
            String[] pair = line.split("(?<=.)", 2);

            if(pair[0].equals("L")) {
                pointer = (pointer - Integer.parseInt(pair[1])) % 100;
            } else if(pair[0].equals("R")) {
                pointer = (pointer + Integer.parseInt(pair[1])) % 100;
            }

            if (pointer == 0) {
                result ++;
            }
        }

        return Integer.toString(result);
    }
}