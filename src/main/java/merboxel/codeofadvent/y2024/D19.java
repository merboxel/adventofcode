package merboxel.codeofadvent.y2024;


import merboxel.codeofadvent.util.PatternMatching;
import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.*;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D19 {

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2024, 19);
    }

    static class P1 {

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 1 ---------------");
            Scanner sc = readFile();

            char[][] patterns = Arrays.stream(sc.nextLine().split(", ")).map(String::toCharArray).toArray(char[][]::new);
            sc.nextLine();

            int result = 0;

            String[] words = ScannerUtil.toArray(sc);

            for(String word : words) {
                char[] chrs = word.toCharArray();
                boolean[] dp = new boolean[chrs.length+1];
                dp[0] = true;
                for(int i = 0; i < chrs.length; i++) {
                    if(dp[i]) {
                        nextPattern: for(char[] pattern: patterns){
                            try {
                                for(int j = 0; j < pattern.length; j++) {
                                    if(chrs[i+j] != pattern[j]) {
                                        continue nextPattern;
                                    }
                                }
                                dp[i+pattern.length] = true;
                            } catch (Exception ignored) {}
                        }
                    }
                }
                if(dp[chrs.length])
                    result ++;
            }

            System.out.println(result);
            System.out.println("--------------------------------------");
        }
    }

    static class P2 {
        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 2 ---------------");
            Scanner sc = readFile();

            char[][] patterns = Arrays.stream(sc.nextLine().split(", ")).map(String::toCharArray).toArray(char[][]::new);
            sc.nextLine();

            long result = 0;

            String[] words = ScannerUtil.toArray(sc);

            for(String word : words) {
                char[] chrs = word.toCharArray();
                long[] dp = new long[chrs.length+1];
                dp[0] = 1;
                for(int i = 0; i < chrs.length; i++) {
                    if(dp[i] > 0) {
                        nextPattern: for(char[] pattern: patterns){
                            try {
                                for(int j = 0; j < pattern.length; j++) {
                                    if(chrs[i+j] != pattern[j]) {
                                        continue nextPattern;
                                    }
                                }
                                dp[i+pattern.length] += dp[i];
                            } catch (Exception ignored) {}
                        }
                    }
                }
                if(dp[chrs.length] > 0)
                    result += dp[chrs.length];
            }

            System.out.println(result);
            System.out.println("--------------------------------------");
        }
    }
}