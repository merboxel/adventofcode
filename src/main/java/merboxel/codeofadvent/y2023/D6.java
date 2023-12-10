package merboxel.codeofadvent.y2023;

import merboxel.codeofadvent.util.PatternMatching;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D6 {

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile_1() throws IOException {
        return readFileAsScanner(2023, "6.1");
    }

    static Scanner readFile_2() throws IOException {
        return readFileAsScanner(2023, "6.2");
    }

    class P1 {

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 1 ---------------");
            Scanner sc = readFile_1();

            long[] time = PatternMatching.getLongsAsArray(sc.nextLine());
            long[] distance = PatternMatching.getLongsAsArray(sc.nextLine());

            long totalValue = 1;

            next: for(int i =0; i<time.length; i++) {
                for(int j = 0; j < time[i]; j++)
                {
                    if(j * (time[i]-j) > distance[i]) {
                        totalValue *= (time[i] - 2L * j + 1);
                        continue next;
                    }
                }
            }

            System.out.println(totalValue);
            System.out.println("--------------------------------------");
        }
    }

    class P2 {

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 2 ---------------");
            Scanner sc = readFile_2();

            long[] time = PatternMatching.getLongsAsArray(sc.nextLine());
            long[] distance = PatternMatching.getLongsAsArray(sc.nextLine());

            long totalValue = 1;

            next: for(int i =0; i<time.length; i++) {
                for(int j = 0; j < time[i]; j++)
                {
                    if(j * (time[i]-j) > distance[i]) {
                        totalValue *= (time[i] - 2L * j + 1);
                        continue next;
                    }
                }
            }

            System.out.println(totalValue);
            System.out.println("--------------------------------------");
        }
    }
}