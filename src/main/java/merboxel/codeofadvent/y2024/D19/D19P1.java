package merboxel.codeofadvent.y2024.D19;


import merboxel.codeofadvent.AoC;
import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class D19P1 extends AoC {

    private static final int year = 2024;
    private static final int day = 19;
    private static final int part = 1;

    public D19P1(Scanner sc) throws IOException {
        super(year, day, part, sc);
    }

    public D19P1() throws IOException {
        super(year, day, part);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("--------------- Part 2 ---------------");
        System.out.println(new D19P1().run());
        System.out.println("--------------------------------------");
    }

    public String run() throws IOException {

        char[][] patterns = Arrays.stream(sc.nextLine().split(", ")).map(String::toCharArray).toArray(char[][]::new);

        int result = 0;

        String[] words = ScannerUtil.toArray(sc);

        for (String word : words) {
            char[] chrs = word.toCharArray();
            boolean[] dp = new boolean[chrs.length + 1];
            dp[0] = true;
            for (int i = 0; i < chrs.length; i++) {
                if (dp[i]) {
                    nextPattern:
                    for (char[] pattern : patterns) {
                        try {
                            for (int j = 0; j < pattern.length; j++) {
                                if (chrs[i + j] != pattern[j]) {
                                    continue nextPattern;
                                }
                            }
                            dp[i + pattern.length] = true;
                        } catch (Exception ignored) {
                        }
                    }
                }
            }
            if (dp[chrs.length])
                result++;
        }

        return Integer.toString(result);
    }
}