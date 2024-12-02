package merboxel.codeofadvent.y2024;


import merboxel.codeofadvent.util.ArrayUtils;
import merboxel.codeofadvent.util.PatternMatching;
import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D2 {

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2024, 2);
    }

    static class P1 {
        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 1 ---------------");
            Scanner sc = readFile();

            AtomicInteger result = new AtomicInteger(0);

            List<String> lines = ScannerUtil.toList(sc);

            lines.forEach(line -> {
                int[] report = PatternMatching.getIntegersAsArray(line);
                if(decrease(report) || increase(report)) {
                    result.addAndGet(1);
                }
            });

            System.out.println(result.get());
            System.out.println("--------------------------------------");
        }

        private static boolean decrease(int[] sequence) {
            int previous = sequence[0];

            for(int i = 1; i < sequence.length; i++) {
                int diff = sequence[i] - previous;
                if(diff < 1 || diff > 3) {
                    return false;
                }
                previous = sequence[i];
            }
            return true;
        }

        private static boolean increase(int[] sequence) {
            int previous = sequence[0];

            for(int i = 1; i < sequence.length; i++) {
                int diff = previous - sequence[i];
                if(diff < 1 || diff > 3) {
                    return false;
                }
                previous = sequence[i];
            }
            return true;
        }
    }

    static class P2 {
        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 2 ---------------");
            Scanner sc = readFile();

            AtomicInteger result = new AtomicInteger(0);

            List<String> lines = ScannerUtil.toList(sc);

            lines.forEach(line -> {
                int[] report = PatternMatching.getIntegersAsArray(line);
                for (int i = 0; i < report.length; i++) {
                    int[] reportWithRemovedElement = ArrayUtils.removeElement(report, i);
                    if (decrease(reportWithRemovedElement) || increase(reportWithRemovedElement)) {
                        result.addAndGet(1);
                        break;
                    }
                }
            });

            System.out.println(result.get());
            System.out.println("--------------------------------------");
        }

        private static boolean decrease(int[] sequence) {
            int previous = sequence[0];

            for (int i = 1; i < sequence.length; i++) {
                int diff = sequence[i] - previous;
                if (diff < 1 || diff > 3) {
                    return false;
                }
                previous = sequence[i];
            }
            return true;
        }

        private static boolean increase(int[] sequence) {
            int previous = sequence[0];

            for (int i = 1; i < sequence.length; i++) {
                int diff = previous - sequence[i];
                if (diff < 1 || diff > 3) {
                    return false;
                }
                previous = sequence[i];
            }
            return true;
        }
    }
}