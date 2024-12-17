package merboxel.codeofadvent.y2024;


import merboxel.codeofadvent.util.BitUtils;
import merboxel.codeofadvent.util.PatternMatching;
import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.*;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D17 {

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2024, 17);
    }

    static class P1 {

        static long a;
        static long b;
        static long c;
        static int[] p;

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 1 ---------------");
            Scanner sc = readFile();

            a = Long.parseLong(sc.nextLine().substring(12));
            b = Long.parseLong(sc.nextLine().substring(12));
            c = Long.parseLong(sc.nextLine().substring(12));
            sc.nextLine();
            p = Arrays.stream(sc.nextLine().substring(9).split(",")).mapToInt(Integer::parseInt).toArray();

            int pointer = 0;
            StringBuilder stb = new StringBuilder();

            do {
                int opcode = p[pointer];
                int operand = p[pointer + 1];

                switch (opcode) {
                    case 0 -> {
                        long division = comboOperatnds(operand);
                        if (division == 0) {
                            throw new RuntimeException();
                        }
                        a = (long) (a / Math.pow(2, comboOperatnds(operand)));
                    }
                    case 1 -> {
                        b = BitUtils.XOR(b, operand);
                    }
                    case 2 -> {
                        b = BitUtils.AND(7, comboOperatnds(operand));
                    }
                    case 3 -> {
                        if (a != 0) {
                            pointer = operand;
                            continue;
                        }
                    }
                    case 4 -> {
                        b = BitUtils.XOR(b, c);
                    }
                    case 5 -> {
                        stb.append(BitUtils.AND(comboOperatnds(operand), 7));
                        stb.append(",");
                    }
                    case 6 -> {
                        b = (long) (a / Math.pow(2, comboOperatnds(operand)));
                    }
                    case 7 -> {
                        c = (long) (a / Math.pow(2, comboOperatnds(operand)));
                    }
                }
                pointer += 2;
            } while (pointer < p.length);

            if (!stb.isEmpty())
                stb.deleteCharAt(stb.length() - 1);
            System.out.println(stb);
            System.out.println("--------------------------------------");
        }

        private static long comboOperatnds(int operand) {
            switch (operand) {
                case 0, 1, 2, 3 -> {
                    return operand;
                }
                case 4 -> {
                    return a;
                }
                case 5 -> {
                    return b;
                }
                case 6 -> {
                    return c;
                }
                default -> {
                    throw new RuntimeException();
                }
            }
        }
    }

    static class P2 {

        static long a;
        static long b;
        static long c;
        static int[] p;

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 2 ---------------");
            Scanner sc = readFile();

            sc.nextLine();
            sc.nextLine();
            sc.nextLine();
            sc.nextLine();
            p = Arrays.stream(sc.nextLine().substring(9).split(",")).mapToInt(Integer::parseInt).toArray();

            Set<Long> result = new HashSet<>();
            result.add(0L);

            for (int i = 0; i < p.length; i++) {
                Set<Long> _result = new HashSet<>();
                for (long r : result) {
                    nextDigit:
                    for (long j = 0; j < 8; j++) {
                        int pointer = 0;
                        a = (r << 3) + j;
                        b = 0;
                        c = 0;
                        do {
                            int opcode = p[pointer];
                            int operand = p[pointer + 1];

                            switch (opcode) {
                                case 0 -> {
                                    long division = comboOperatnds(operand);
                                    if (division == 0) {
                                        throw new RuntimeException();
                                    }
                                    a = (long) (a / Math.pow(2, comboOperatnds(operand)));
                                }
                                case 1 -> {
                                    b = BitUtils.XOR(b, operand);
                                }
                                case 2 -> {
                                    b = BitUtils.AND(7, comboOperatnds(operand));
                                }
                                case 3 -> {
                                    if (a != 0) {
                                        pointer = operand;
                                        continue;
                                    }
                                }
                                case 4 -> {
                                    b = BitUtils.XOR(b, c);
                                }
                                case 5 -> {
                                    long tmp = BitUtils.AND(comboOperatnds(operand), 7);
                                    if (p[p.length - i - 1] == tmp) {
                                        _result.add((r << 3) + j);
                                    }
                                    continue nextDigit;//only need to check the first digit to match
                                }
                                case 6 -> {
                                    b = (long) (a / Math.pow(2, comboOperatnds(operand)));
                                }
                                case 7 -> {
                                    c = (long) (a / Math.pow(2, comboOperatnds(operand)));
                                }
                            }
                            pointer += 2;
                        } while (pointer < p.length);
                    }
                    result = _result;
                }
            }
            System.out.println(result); //all possible outcomes
            System.out.println(result.stream().min(Long::compareTo).get());
            System.out.println("--------------------------------------");
        }

        private static long comboOperatnds(int operand) {
            switch (operand) {
                case 0, 1, 2, 3 -> {
                    return operand;
                }
                case 4 -> {
                    return a;
                }
                case 5 -> {
                    return b;
                }
                case 6 -> {
                    return c;
                }
                default -> {
                    throw new RuntimeException();
                }
            }
        }
    }
}