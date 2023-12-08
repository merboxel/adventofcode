package merboxel.codeofadvent.y2023;

import merboxel.codeofadvent.util.PatternMatching;

import java.io.IOException;
import java.util.*;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D9 {

    public static void main(String[] args) throws IOException {
        D9P1.run();
        D9P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2023, 9);
    }
}

class D9P1 {

    public static void main(String[] args) throws IOException {
        run();
    }

    public static void run() throws IOException {
        System.out.println("--------------- Part 1 ---------------");
        long result = 0;
        Scanner sc = D9.readFile();

        while(sc.hasNext()) {

        }
        System.out.println(result);
        System.out.println("--------------------------------------");
    }
}

class D9P2 {

    public static void main(String[] args) throws IOException {
        run();
    }

    public static void run() throws IOException {
        System.out.println("--------------- Part 2 ---------------");
        long result = 0L;
        Scanner sc = D9.readFile();

        while(sc.hasNext()) {

        }
        System.out.println(result);
        System.out.println("--------------------------------------");
    }
}
