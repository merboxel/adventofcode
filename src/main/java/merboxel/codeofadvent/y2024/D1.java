package merboxel.codeofadvent.y2024;


import merboxel.codeofadvent.util.PatternMatching;
import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.*;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D1 {

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2024, 1);
    }

    static class P1 {
        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 1 ---------------");
            Scanner sc = readFile();

            int result = 0;

            List<String> lines = ScannerUtil.toList(sc);
            List<Integer> left = new ArrayList<>();
            List<Integer> right = new ArrayList<>();

            lines.forEach(line -> {
                int[] tmp = PatternMatching.getIntegersAsArray(line);
                left.add(tmp[0]);
                right.add(tmp[1]);
            });

            left.sort(Integer::compareTo);
            right.sort(Integer::compareTo);

            for(int i = 0; i < left.size(); i ++){
                result += Math.abs(left.get(i) - right.get(i));
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

            int result = 0;

            List<String> lines = ScannerUtil.toList(sc);
            List<Integer> left = new ArrayList<>();
            Map<Integer,Integer> right = new HashMap<Integer, Integer>();

            lines.forEach(line -> {
                int[] tmp = PatternMatching.getIntegersAsArray(line);
                left.add(tmp[0]);
                right.put(tmp[1],right.getOrDefault(tmp[1],0)+1);
            });

            for (Integer integer : left) {
                result += Math.abs(integer * right.getOrDefault(integer, 0));
            }

            System.out.println(result);
            System.out.println("--------------------------------------");
        }
    }
}