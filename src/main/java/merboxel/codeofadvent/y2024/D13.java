package merboxel.codeofadvent.y2024;


import merboxel.codeofadvent.util.PatternMatching;
import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D13 {

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2024, 13);
    }

    static class P1 {
        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 1 ---------------");
            Scanner sc = readFile();

            long result = 0;

            while(sc.hasNextLine()) {
                String tmp = sc.nextLine();
                if (Objects.equals(tmp, ""))
                    continue;

                String[] _a = tmp.split(" ");
                String[] _b = sc.nextLine().split(" ");
                String[] _ans = sc.nextLine().split(" ");

                long[] a = { PatternMatching.getIntegersAsArray(_a[2])[0], PatternMatching.getIntegersAsArray(_a[3])[0] };
                long[] b = { PatternMatching.getIntegersAsArray(_b[2])[0], PatternMatching.getIntegersAsArray(_b[3])[0] };
                long[] ans = { PatternMatching.getIntegersAsArray(_ans[1])[0], PatternMatching.getIntegersAsArray(_ans[2])[0] };

                long[][] resA = new long[100][2];
                long[][] resB = new long[100][2];

                for(int i = 0; i < resA.length; i++) {
                    resA[i][0] = i * a[0];
                    resA[i][1] = i * a[1];
                }

                for(int i = 0; i < resB.length; i++) {
                    resB[i][0] = i * b[0];
                    resB[i][1] = i * b[1];
                }

                int resAns = 1000;

                for(int i = 0; i < resA.length; i++) {
                    for(int j = 0; j < resB.length; j++) {
                        if(ans[0] == resA[i][0] + resB[j][0] && ans[1] == resA[i][1] + resB[j][1]) {
                            resAns = Math.min(resAns, 3*i + j);

                            System.out.println(resAns);
                        }
                    }
                }
                if(resAns != 1000) {
                    result += resAns;
                }
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

            long result = 0L;

            while(sc.hasNextLine()) {
                String tmp = sc.nextLine();
                if (Objects.equals(tmp, ""))
                    continue;

                String[] _a = tmp.split(" ");
                String[] _b = sc.nextLine().split(" ");
                String[] _ans = sc.nextLine().split(" ");

                long[] a = { PatternMatching.getIntegersAsArray(_a[2])[0], PatternMatching.getIntegersAsArray(_a[3])[0] };
                long[] b = { PatternMatching.getIntegersAsArray(_b[2])[0], PatternMatching.getIntegersAsArray(_b[3])[0] };
                long[] ans = { PatternMatching.getIntegersAsArray(_ans[1])[0] + 10000000000000L, PatternMatching.getIntegersAsArray(_ans[2])[0] + 10000000000000L };

                System.out.println("-------------------------");

                long det = (a[0]*b[1] - b[0]*a[1]);

                long tokenA = (b[1]*ans[0] - b[0]*ans[1]) / det;
                System.out.println("tokenA := "+tokenA);

                long tokenB = (a[0]*ans[1] - a[1]*ans[0]) / det;
                System.out.println("tokenB := "+tokenB);

                if(tokenA >= 0 && tokenB >= 0 && tokenA * a[0] + tokenB * b[0] == ans[0] && tokenA * a[1] + tokenB * b[1] == ans[1]) {
                    result += (3*tokenA + tokenB);
                }

                System.out.println("-------------------------");
            }

            System.out.println(result);
            System.out.println("--------------------------------------");

        }
    }
}