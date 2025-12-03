package merboxel.codeofadvent.y2024.D19;


import merboxel.codeofadvent.AoC;
import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D19P2 extends AoC {

    private static final int year = 2024;
    private static final int day = 19;
    private static final int part = 2;

    public D19P2(Scanner sc) throws IOException {
        super(year, day, part, sc);
    }

    public D19P2() throws IOException {
        super(year, day, part);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("--------------- Part 2 ---------------");
        System.out.println(new D19P2().run());
        System.out.println("--------------------------------------");
    }

    public String run() throws IOException {

        char[][] patterns = Arrays.stream(sc.nextLine().split(", ")).map(String::toCharArray).toArray(char[][]::new);
        sc.nextLine();

        long result = 0;

        String[] words = ScannerUtil.toArray(sc);

        for (String word : words) {
            char[] chrs = word.toCharArray();
            long[] dp = new long[chrs.length + 1];
            dp[0] = 1;
            for (int i = 0; i < chrs.length; i++) {
                if (dp[i] > 0) {
                    nextPattern:
                    for (char[] pattern : patterns) {
                        try {
                            for (int j = 0; j < pattern.length; j++) {
                                if (chrs[i + j] != pattern[j]) {
                                    continue nextPattern;
                                }
                            }
                            dp[i + pattern.length] += dp[i];
                        } catch (Exception ignored) {
                        }
                    }
                }
            }
            if (dp[chrs.length] > 0)
                result += dp[chrs.length];
        }

        return Long.toString(result);
    }
}