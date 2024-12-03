package merboxel.codeofadvent.y2024;


import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;
import static merboxel.codeofadvent.util.PatternMatching.getIntegersAsArray;

public class D3 {

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2024, 3);
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

                Pattern patternMul = Pattern.compile("mul\\(\\d+,\\d+\\)");
                Matcher matchedMul = patternMul.matcher(line);

                while(matchedMul.find()) {

                    int[] tmp = getIntegersAsArray(matchedMul.group());
                    result.updateAndGet(v -> v + tmp[0] * tmp[1]);
                }
            });

            System.out.println(result.get());
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

            AtomicInteger result = new AtomicInteger(0);

            List<String> lines = ScannerUtil.toList(sc);

            AtomicBoolean process = new AtomicBoolean(true);

            lines.forEach(line -> {

                Pattern patternMul = Pattern.compile("mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\)");
                Matcher matchedMul = patternMul.matcher(line);

                while(matchedMul.find()) {

                    String entry = matchedMul.group();

                    if(Objects.equals(entry, "don't()")) {
                        process.set(false);
                    } else if(entry.equals("do()")) {
                        process.set(true);
                    } else if(process.get()) {
                            int[] tmp = getIntegersAsArray(entry);
                            result.updateAndGet(v -> v + tmp[0] * tmp[1]);
                    }
                }
            });

            System.out.println(result.get());
            System.out.println("--------------------------------------");
        }
    }
}