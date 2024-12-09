package merboxel.codeofadvent.y2024;


import merboxel.codeofadvent.util.PatternMatching;
import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D9 {

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2024, 9);
    }

    static class P1 {
        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 1 ---------------");
            Scanner sc = readFile();

            int[] diskMap = sc.nextLine().chars().map(c -> c - '0').toArray();

            long result = 0L;
            long fileId = 0L;
            int start = 0;
            int end = diskMap.length-1;

            outer :while(true) {
                if(diskMap.length == start) {
                    break;
                }

                if(start % 2 == 0) {

                    while(diskMap[start] > 0) {
                        result += fileId * (start/2);
                        fileId++;
                        diskMap[start]--;
                    }
                } else {
                    while(diskMap[start] > 0) {
                        if(end <= start)
                            break outer;

                        if(diskMap[end] == 0) {
                            end -= 2;
                        } else {
                            result += fileId * (end/2);
                            fileId++;
                            diskMap[end]--;
                            diskMap[start]--;
                        }
                    }
                }

                start ++;
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

            List<String> lines = ScannerUtil.toList(sc);

            System.out.println("--------------------------------------");
        }
    }
}