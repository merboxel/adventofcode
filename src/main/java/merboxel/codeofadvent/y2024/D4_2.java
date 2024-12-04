package merboxel.codeofadvent.y2024;


import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D4_2 {

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2024, 4);
    }

    static class P1 {
        static int[][] directions = { {1,0}, {0,1}, {-1,0}, {0,-1}, {1,1}, {-1,-1}, {-1,1}, {1,-1}};

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 1 ---------------");
            Scanner sc = readFile();

            int result = 0;

            List<String> _lines = ScannerUtil.toList(sc);

            char[][] lines = _lines.stream().map(String::toCharArray).toList().toArray(new char[0][]);

            for(int x = 0; x < lines.length; x++) {
                for (int y = 0; y < lines[0].length; y++) {
                    for(int[] dir : directions) {
                        int dx = dir[0];
                        int dy = dir[1];

                        StringBuilder tmp = new StringBuilder();
                        try {
                            for (int i = 0; i < 4; i++) {
                                tmp.append(lines[x + dx * i][y + dy * i]);
                            }
                        } catch (Exception ignored) {}
                        if(tmp.toString().equals("XMAS"))
                            result ++;
                    }
                }
            }

            System.out.println(result);
            System.out.println("--------------------------------------");
        }
    }

    static class P2 {
        static int[][] directions = {{1,1}, {-1,-1}, {-1,1}, {1,-1}};

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 2 ---------------");
            Scanner sc = readFile();

            int result = 0;

            List<String> _lines = ScannerUtil.toList(sc);

            char[][] lines = _lines.stream().map(String::toCharArray).toList().toArray(new char[0][]);

            for(int x = 0; x < lines.length; x++) {
                for (int y = 0; y < lines[0].length; y++) {
                    int seenSam = 0;
                    for(int[] dir : directions) {
                        int dx = dir[0];
                        int dy = dir[1];

                        StringBuilder tmp = new StringBuilder();
                        try {
                            for (int i = -1; i < 2; i++) {
                                tmp.append(lines[x + dx * i][y + dy * i]);
                            }
                        } catch (Exception ignored) {}
                        if(tmp.toString().equals("SAM"))
                            seenSam ++;
                    }
                    if(seenSam == 2) {
                        result++;
                    }
                }
            }

            System.out.println(result);
            System.out.println("--------------------------------------");
        }
    }
}