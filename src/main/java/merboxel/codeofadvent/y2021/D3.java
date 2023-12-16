package merboxel.codeofadvent.y2021;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D3 {

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2021, 3);
    }

    static class P1 {
        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 1 ---------------");
            Scanner sc = readFile();

            long[] bucket = new long[12];
            int count = 0;
            while (sc.hasNext()) {
                char[] line = sc.nextLine().toCharArray();
                for(int i=0; i<line.length; i++) {
                    bucket[i] += line[i] - 48;
                }
                count ++;
            }

            long gamma = 0;
            long epsilon = 0;

            for(int i = 0; i<bucket.length; i++) {
                gamma = (gamma << 1) + bucket[i]*2 / count;
                epsilon = (epsilon << 1) + ((bucket[i]*2 / count) ^ 1);
            }

            System.out.println(gamma*epsilon);
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

            List<String> lines = new ArrayList<>();

            while (sc.hasNext()) {
                lines.add(sc.nextLine());
            }
            char[][] grid = lines.stream().map(String::toCharArray).toArray(char[][]::new);

            long oxygen = calcNumbersMost(grid,0);
            long carbon = calcNumbersLeast(grid,0);

            System.out.println(oxygen*carbon);
            System.out.println("--------------------------------------");
        }
    }
    public static long toLong(char[] bin) {
        long result = 0L;
        for(char c: bin)
            result = (result << 1) + c - 48;
        return result;
    }

    public static long calcNumbersMost(char[][] grid, int index) {

        if(grid.length == 1) {
            return toLong(grid[0]);
        }
        int count = Arrays.stream(grid).mapToInt(chars -> chars[index] - 48).sum();

        return calcNumbersMost(Arrays.stream(grid).filter(elem -> elem[index] == (count*2/grid.length)+48).toArray(char[][]::new),index+1);
    }
    public static long calcNumbersLeast(char[][] grid, int index) {

        if(grid.length == 1) {
            return toLong(grid[0]);
        }
        int count = Arrays.stream(grid).mapToInt(chars -> chars[index] - 48).sum();

        return calcNumbersLeast(Arrays.stream(grid).filter(elem -> elem[index] == ((count*2/grid.length)^1)+48).toArray(char[][]::new),index+1);
    }
}
