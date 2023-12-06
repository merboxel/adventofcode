package merboxel.codeofadvent.y2023;

import java.io.IOException;
import java.util.Scanner;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D7 {

    public static void main(String[] args) throws IOException {
        D7P1.run();
        D7P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2023, 7);
    }
}

class D7P1 {

    public static void main(String[] args) throws IOException {
        run();
    }

    public static void run() throws IOException {

        System.out.println("--------------- Part 1 ---------------");
        long result = 0L;
        Scanner sc = D7.readFile();

        while(sc.hasNext()) {

        }

        System.out.println(result);
        System.out.println("--------------------------------------");
    }
}

class D7P2 {

    public static void main(String[] args) throws IOException {
        run();
    }

    public static void run() throws IOException {
        System.out.println("--------------- Part 2 ---------------");
        long result = 0L;
        Scanner sc = D7.readFile();

        while(sc.hasNext()) {
            
        }

        System.out.println(result);
        System.out.println("--------------------------------------");
    }
}