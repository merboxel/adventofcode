package merboxel.codeofadvent.y2024;


import merboxel.codeofadvent.structure.Point;
import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D21 {

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2024, 21);
    }

    static class P1 {

        String[][] dSequence = new String[5][5];
        String[][] nSequence = new String[11][11];

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 1 ---------------");
            Scanner sc = readFile();

            long result = 0L;

            List<String> codes = ScannerUtil.toList(sc);

            for (String code : codes) {
                nKeyPad currN = nKeyPad.A;
                StringBuilder sb = new StringBuilder();

                for (char c : code.toCharArray()) {
                    nKeyPad next = getNKeyPad(c);
                    sb.append(currN.sequence(next));
                    sb.append("A");
                    currN = next;
                }
                String s1 = sb.toString();
                sb = new StringBuilder();

                dKeyPad currD = dKeyPad.A;
                for (char c : s1.toCharArray()) {
                    dKeyPad next = getDKeyPad(c);
                    sb.append(currD.sequence(next));
                    sb.append("A");
                    currD = next;
                }
                String s2 = sb.toString();
                sb = new StringBuilder();

                currD = dKeyPad.A;
                for (char c : s2.toCharArray()) {
                    dKeyPad next = getDKeyPad(c);
                    sb.append(currD.sequence(next));
                    sb.append("A");
                    currD = next;
                }
                String s3 = sb.toString();


                System.out.println(code);
                System.out.println(s1);
                System.out.println(s2);
                System.out.println(s3);

                System.out.println(s3.length());
                System.out.println(Long.parseLong(code.split("A")[0]));

                result += s3.length() * Long.parseLong(code.split("A")[0]);
            }

            System.out.println(result); // wrong 213458
            System.out.println("--------------------------------------");

//
//
//
        }

        static nKeyPad getNKeyPad(char c) {
            switch (c) {
                case '0' -> {
                    return nKeyPad.ZERO;
                }
                case '1' -> {
                    return nKeyPad.ONE;
                }
                case '2' -> {
                    return nKeyPad.TWO;
                }
                case '3' -> {
                    return nKeyPad.THREE;
                }
                case '4' -> {
                    return nKeyPad.FOUR;
                }
                case '5' -> {
                    return nKeyPad.FIVE;
                }
                case '6' -> {
                    return nKeyPad.SIX;
                }
                case '7' -> {
                    return nKeyPad.SEVEN;
                }
                case '8' -> {
                    return nKeyPad.EIGHT;
                }
                case '9' -> {
                    return nKeyPad.NINE;
                }
                case 'A' -> {
                    return nKeyPad.A;
                }
                default -> throw new IllegalStateException("Unexpected value: " + c);
            }
        }

        static dKeyPad getDKeyPad(char c) {
            switch (c) {
                case '^' -> {
                    return dKeyPad.UP;
                }
                case 'v' -> {
                    return dKeyPad.DOWN;
                }
                case '<' -> {
                    return dKeyPad.LEFT;
                }
                case '>' -> {
                    return dKeyPad.RIGHT;
                }
                case 'A' -> {
                    return dKeyPad.A;
                }
                default -> throw new IllegalStateException("Unexpected value: " + c);
            }
        }
    }

    static class P2 {

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 2 ---------------");
            Scanner sc = readFile();

            char[][] grid = Arrays.stream(ScannerUtil.toArray(sc)).map(String::toCharArray).toArray(char[][]::new);

            System.out.println();
            System.out.println("--------------------------------------");
        }
    }

    static String moveDx(int dx) {
        return
                switch (dx) {
                    case 0 -> "";
                    case 1, 2, 3 -> "v".repeat(dx);
                    case -1, -2, -3 -> "^".repeat(dx * -1);
                    default -> throw new IllegalStateException("Unexpected value: " + dx);
                };
    }

    static String moveDy(int dy) {
        return
                switch (dy) {
                    case 0 -> "";
                    case 1, 2, 3 -> ">".repeat(dy);
                    case -1, -2, -3 -> "<".repeat(dy * -1);
                    default -> throw new IllegalStateException("Unexpected value: " + dy);
                };
    }

    enum dKeyPad {
        UP(0, 1),
        DOWN(1, 1),
        LEFT(1, 0),
        RIGHT(1, 2),
        A(0, 2);

        private final Point p;

        dKeyPad(int x, int y) {
            p = new Point(x, y);
        }

        String sequence(dKeyPad that) {
            int dx = (int) (that.p.x - this.p.x);
            int dy = (int) (that.p.y - this.p.y);

            StringBuilder sb = new StringBuilder();

            if(dy < 0) {
                 if(dy == -2) {
                     sb.append(moveDx(dx));
                     sb.append(moveDy(dy));
                 } else {
                     sb.append(moveDy(dy));
                     sb.append(moveDx(dx));
                 }
            } else {
                sb.append(moveDy(dy));
                sb.append(moveDx(dx));
            }
            return sb.toString();
        }
    }

    enum nKeyPad {
        ONE(2, 0),
        TWO(2, 1),
        THREE(2, 2),
        FOUR(1, 0),
        FIVE(1, 1),
        SIX(1, 2),
        SEVEN(0, 0),
        EIGHT(0, 1),
        NINE(0, 2),
        ZERO(3, 1),
        A(3, 2);

        private final Point p;

        nKeyPad(int x, int y) {
            p = new Point(x, y);
        }

        String sequence(nKeyPad that) {
            int dx = (int) (that.p.x - this.p.x);
            int dy = (int) (that.p.y - this.p.y);

            StringBuilder sb = new StringBuilder();

            if(dy < 0) {
                if(this.p.x == 3 && that.p.y == 0) {
                    sb.append(moveDx(dx));
                    sb.append(moveDy(dy));
                } else {
                    sb.append(moveDy(dy));
                    sb.append(moveDx(dx));
                }
            } else {
                if(that.p.x == 3 && this.p.y == 0) {
                    sb.append(moveDy(dy));
                    sb.append(moveDx(dx));
                } else {
                    sb.append(moveDx(dx));
                    sb.append(moveDy(dy));
                }
            }
            return sb.toString();
        }
    }
}