package merboxel.codeofadvent.y2024;


import merboxel.codeofadvent.util.PatternMatching;
import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D7 {

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2024, 7);
    }

    static class P1 {
        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 1 ---------------");
            Scanner sc = readFile();

            List<String> lines = ScannerUtil.toList(sc);

            long result = 0;

            result = lines.stream().map(Operator::new)
                    .mapToLong(op -> op.solve(0L,0))
                    .sum();


            System.out.println(result);
            System.out.println("--------------------------------------");
        }

        public static class Operator {
            long sum;
            long[] nbr;

            public Operator(String line) {
                String[] tmp = line.split(":");
                sum = Long.parseLong(tmp[0]);
                nbr = PatternMatching.getLongsAsArray(tmp[1]);
            }

            public Long solve(Long currSum, int index) {
                if(index == nbr.length && currSum == sum)
                    return sum;

                if(currSum > sum || index >= nbr.length)
                    return 0L;


                long plus = solve(currSum + nbr[index], index+1);
                if(plus > 0)
                    return plus;
                else {
                    return solve(currSum * nbr[index], index+1);
                }
            }
        }
    }

    static class P2 {

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 2 ---------------");
            Scanner sc = readFile();

            List<String> lines = ScannerUtil.toList(sc);

            long result = 0;

            result = lines.stream().map(Operator::new)
                    .mapToLong(op -> op.solve(0L, 0))
                    .sum();


            System.out.println(result);
            System.out.println("--------------------------------------");
        }

        public static class Operator {
            long sum;
            long[] nbr;

            public Operator(String line) {
                String[] tmp = line.split(":");
                sum = Long.parseLong(tmp[0]);
                nbr = PatternMatching.getLongsAsArray(tmp[1]);
            }

            public Long solve(Long currSum, int index) {
                if (index == nbr.length && currSum == sum)
                    return sum;

                if (currSum > sum || index >= nbr.length)
                    return 0L;


                long plus = solve(currSum + nbr[index], index + 1);
                if (plus > 0)
                    return plus;
                plus = solve(currSum * nbr[index], index + 1);
                if (plus > 0){
                    return solve(currSum * nbr[index], index + 1);
                } else {
                    return solve(Long.parseLong(currSum + "" + nbr[index]), index + 1);
                }
            }
        }
    }
}