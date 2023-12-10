package merboxel.codeofadvent.y2023;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D11 {

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2023, 11);
    }

    static class P1 {

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 1 ---------------");
            long result = 0L;
            Scanner sc = readFile();

            while(sc.hasNext()) {


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
            long result = 0L;
            Scanner sc = readFile();

            while(sc.hasNext()) {


            }

            System.out.println(result);
            System.out.println("--------------------------------------");
        }
    }


}