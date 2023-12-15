package merboxel.codeofadvent.y2021;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D1 {

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2021, 1);
    }

    static class P1 {
        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 1 ---------------");
            Scanner sc = readFile();

            long result = 0;

            int previous = sc.nextInt();

            while (sc.hasNext()) {
                int current = sc.nextInt();
                if(current - previous > 0)
                    result ++;
                previous = current;
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

            long result = 0;
            Queue<Integer> queue = new LinkedList<>();
            int firstInt = sc.nextInt();
            queue.add(firstInt);
            int secondInt = sc.nextInt();
            queue.add(secondInt);
            int thirdInt = sc.nextInt();
            queue.add(thirdInt);

            int previous = firstInt+secondInt+thirdInt;

            while (sc.hasNext()) {
                int nextInt = sc.nextInt();
                queue.add(nextInt);
                int current = previous + nextInt - queue.poll();
                if(current - previous > 0)
                    result ++;
                previous = current;
            }
            System.out.println(result);
            System.out.println("--------------------------------------");
        }
    }
}
