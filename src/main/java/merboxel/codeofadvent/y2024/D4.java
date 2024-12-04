package merboxel.codeofadvent.y2024;


import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;
import static merboxel.codeofadvent.util.PatternMatching.getIntegersAsArray;

public class D4 {

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2024, 4);
    }

    static class P1 {
        static char[] xmas = "XMAS".toCharArray();

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 1 ---------------");
            Scanner sc = readFile();

            int result = 0;

            List<String> _lines = ScannerUtil.toList(sc);

            char[][] lines = _lines.stream().map(String::toCharArray).toList().toArray(new char[0][]);

            for(int i = 0; i < lines.length; i++) {
                for (int j = 0; j < lines[0].length; j++) {
                    if(lines[i][j] == 'X') {
                        result += lookAround(lines,i,j);
                    }
                }
            }

            System.out.println(result);
            System.out.println("--------------------------------------");
        }

        public static int lookAround(char[][] lines, int x, int y) {
            int result = 0;

            // | backward
            try {
                if(lines[x-1][y] == xmas[1] && lines[x-2][y] == xmas[2] && lines[x-3][y] == xmas[3])
                    result ++;
            } catch (Exception ignored) {}

            // | forward
            try {
                if(lines[x+1][y] == xmas[1] && lines[x+2][y] == xmas[2] && lines[x+3][y] == xmas[3])
                    result ++;
            } catch (Exception ignored) {}

            // - backward
            try {
                if(lines[x][y-1] == xmas[1] && lines[x][y-2] == xmas[2] && lines[x][y-3] == xmas[3])
                    result ++;
            } catch (Exception ignored) {}

            // - forward
            try {
                if(lines[x][y+1] == xmas[1] && lines[x][y+2] == xmas[2] && lines[x][y+3] == xmas[3])
                    result ++;
            } catch (Exception ignored) {}

            // \ backward
            try {
                if(lines[x-1][y-1] == xmas[1] && lines[x-2][y-2] == xmas[2] && lines[x-3][y-3] == xmas[3])
                    result ++;
            } catch (Exception ignored) {}

            // \ forward
            try {
                if(lines[x+1][y+1] == xmas[1] && lines[x+2][y+2] == xmas[2] && lines[x+3][y+3] == xmas[3])
                    result ++;
            } catch (Exception ignored) {}

            // / backward
            try {
                if(lines[x+1][y-1] == xmas[1] && lines[x+2][y-2] == xmas[2] && lines[x+3][y-3] == xmas[3])
                    result ++;
            } catch (Exception ignored) {}

            // / forward
            try {
                if(lines[x-1][y+1] == xmas[1] && lines[x-2][y+2] == xmas[2] && lines[x-3][y+3] == xmas[3])
                    result ++;
            } catch (Exception ignored) {}

            return result;
        }
    }

    static class P2 {

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 2 ---------------");
            Scanner sc = readFile();

            int result = 0;

            List<String> _lines = ScannerUtil.toList(sc);

            char[][] lines = _lines.stream().map(String::toCharArray).toList().toArray(new char[0][]);

            for (int i = 0; i < lines.length; i++) {
                for (int j = 0; j < lines[0].length; j++) {
                    if (lines[i][j] == 'A') {
                        result += lookAround(lines, i, j);
                    }
                }
            }

            System.out.println(result);
            System.out.println("--------------------------------------");
        }

        public static int lookAround(char[][] lines, int x, int y) {
            int result = 0;

            // cross
            try {
                if ((lines[x-1][y-1] == 'S' && lines[x+1][y+1] == 'M') || lines[x-1][y-1] == 'M' && lines[x+1][y+1] == 'S')  {
                    if ((lines[x+1][y-1] == 'S' && lines[x-1][y+1] == 'M') || lines[x+1][y-1] == 'M' && lines[x-1][y+1] == 'S') {
                        result ++;
                    }
                }
            } catch (Exception ignored) {}

            return result;
        }
    }
}