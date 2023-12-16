package merboxel.codeofadvent.y2021;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D2 {

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2021, 2);
    }

    static class P1 {
        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 1 ---------------");
            Scanner sc = readFile();

            long[] pos = new long[] {0,0};
            while (sc.hasNext()) {
                String[] line = sc.nextLine().split(" ");

                switch(line[0]) {
                    case "forward" -> {
                        pos[0] += Long.parseLong(line[1]);
                    }
                    case "down" -> {
                        pos[1] += Long.parseLong(line[1]);
                    }
                    case "up" -> {
                        pos[1] -= Long.parseLong(line[1]);
                    }
                }
            }

            System.out.println(pos[0]*pos[1]);
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

            long[] pos = new long[] {0,0,0};
            while (sc.hasNext()) {
                String[] line = sc.nextLine().split(" ");

                switch(line[0]) {
                    case "forward" -> {
                        pos[0] += Long.parseLong(line[1]);
                        pos[1] += Long.parseLong(line[1]) * pos[2];
                    }
                    case "down" -> {
                        pos[2] += Long.parseLong(line[1]);
                    }
                    case "up" -> {
                        pos[2] -= Long.parseLong(line[1]);
                    }
                }
            }

            System.out.println(pos[0]*pos[1]);
            System.out.println("--------------------------------------");
        }
    }
}
