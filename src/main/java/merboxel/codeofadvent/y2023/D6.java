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
        D6P1.run();
        D6P2.run();
    }
}

class D6P1 {

    public static void main(String[] args) throws IOException {
        run();
    }

    public static void run() throws IOException {
        System.out.println("--------------- Part 1 ---------------");
        Scanner sc = readFileAsScanner(2023, "6.1");

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

class D6P2 {

    public static void main(String[] args) throws IOException {
        run();
    }

    public static void run() throws IOException {
        System.out.println("--------------- Part 2 ---------------");
        Scanner sc = readFileAsScanner(2023, "6.2");

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