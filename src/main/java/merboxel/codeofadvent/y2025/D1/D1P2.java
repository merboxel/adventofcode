package merboxel.codeofadvent.y2025.D1;


import merboxel.codeofadvent.AoC;
import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class D1P2 extends AoC {

    private static final int year = 2025;
    private static final int day = 1;
    private static final int part = 2;

    public D1P2(Scanner sc) throws IOException {
        super(year, day, part, sc);
    }

    public D1P2() throws IOException {
        super(year, day, part);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("--------------- Part 2 ---------------");
        System.out.println(new D1P2().run());
        System.out.println("--------------------------------------");
    }

    public String run() throws IOException {

        int result = 0;
        int pointer = 50;

        List<String> lines = ScannerUtil.toList(sc);

        for(String line: lines) {
            String[] pair = line.split("(?<=.)", 2);

            int prev = pointer;

            if(pair[0].equals("L")) {
                pointer = pointer - Integer.parseInt(pair[1]);
            } else if(pair[0].equals("R")) {
                pointer = pointer + Integer.parseInt(pair[1]);
            }

            if (pointer <= 0) {
                result += pointer / -100;
                if(prev != 0) {
                    result ++;
                }
            } else if (pointer >= 100) {
                result += pointer / 100;
            }

            pointer = (((pointer % 100) + 100) % 100);
        }

        return Integer.toString(result);
    }
}