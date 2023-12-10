package merboxel.codeofadvent.y2023;

import merboxel.codeofadvent.util.PatternMatching;

import java.io.IOException;
import java.util.*;
import java.util.stream.LongStream;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D9 {

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2023, 9);
    }

    static class P1 {

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 1 ---------------");
            long result = 0;
            Scanner sc = readFile();

            while(sc.hasNext()) {
                long[] sequenceOrr = PatternMatching.getLongsWithNegativesAsArray(sc.nextLine());
                long[] tmp = Arrays.copyOf(sequenceOrr,sequenceOrr.length);
                List<Long> lastDigits = new ArrayList<>();

                lastDigits.add(tmp[tmp.length-1]);

                for(int i = 1; i < sequenceOrr.length; i++) {
                    tmp = Calc.calcDiff(tmp);
                    // System.out.println(Arrays.toString(tmp)); //Debug
                    lastDigits.add(tmp[tmp.length-1]);
                    if(Calc.allZero(tmp))
                        break;
                }

                long x = 0;
                for(long l: lastDigits.reversed()) {
                    //System.out.println(x + " " + l); //Debug
                    x += l;
                }
                result += x;
            }
            System.out.println(result);
            System.out.println("--------------------------------------");
        }
    }

    static class Calc {

        public static boolean allZero(long[] seq){
            for(long l: seq)
                if(l != 0L)
                    return false;
            return true;
        }

        public static long[] calcDiff(long[] seq) {
            return LongStream.range(0,seq.length-1)
                    .map(i -> seq[((int)i+1)]-seq[((int)i)])
                    .toArray();
        }
    }

    static class P2 {

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 2 ---------------");
            long result = 0L;
            Scanner sc = D9.readFile();

            while(sc.hasNext()) {
                long[] sequenceOrr = PatternMatching.getLongsWithNegativesAsArray(sc.nextLine());
                long[] tmp = Arrays.copyOf(sequenceOrr,sequenceOrr.length);
                List<Long> firstDigits = new ArrayList<>();

                firstDigits.add(tmp[0]);

                for(int i = 1; i < sequenceOrr.length; i++) {
                    tmp = Calc.calcDiff(tmp);
                    // System.out.println(Arrays.toString(tmp)); //Debug
                    firstDigits.add(tmp[0]);
                    if(Calc.allZero(tmp))
                        break;
                }
                long x = 0;
                for(long l: firstDigits.reversed()) {
                    x = l - x;
                    //System.out.println(x + " " + l); //Debug
                }
                result += x;
            }
            System.out.println(result);
            System.out.println("--------------------------------------");
        }
    }
}