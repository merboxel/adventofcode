package merboxel.codeofadvent.y2025.D3;


import merboxel.codeofadvent.AoC;
import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class D3P1 extends AoC {

    private static final int year = 2025;
    private static final int day = 3;
    private static final int part = 1;

    public D3P1(Scanner sc) throws IOException {
        super(year, day, part, sc);
    }

    public D3P1() throws IOException {
        super(year, day, part);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("--------------- Part 1 ---------------");
        System.out.println(new D3P1().run());
        System.out.println("--------------------------------------");
    }

    public String run() throws IOException {

        char[][] ranges = Arrays.stream(ScannerUtil.toArray(sc)).map(String::toCharArray).toArray(char[][]::new);

        long result = 0;

        for(char[] range : ranges){
            int first = 0;
            int second = 0;

            for(int i = 0; i < range.length; i++){
                int c = range[i] - '0';

                if(c > first && i + 1 < range.length){
                    second = range[i + 1] - '0';
                    first = c;
                    continue;
                }
                if(c > second){
                    second = c;
                }
            }

            System.out.println(first * 10L + second);

            result += first * 10L + second;
        }

        return Long.toString(result);
    }
}