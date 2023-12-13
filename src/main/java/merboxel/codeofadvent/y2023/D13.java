package merboxel.codeofadvent.y2023;


import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D13 {

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2023, 13);
    }

    static class P1 {

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 1 ---------------");
            long result = 0L;
            Scanner sc = readFile();

            List<String> field = new ArrayList<>();

            int lineNr = 0;
            while (sc.hasNext()) {

                String line = sc.nextLine();
                if (line == null || line.isEmpty() || line.isBlank()) {
                    LavaField f = new LavaField(field.stream().map(String::toCharArray).toArray(char[][]::new));
                    long tmp = LavaField.calcMirror();
                    result += LavaField.calcMirror();
                    field = new ArrayList<>();
                    continue;
                }
                field.add(line);

            }


            System.out.println(result);
            System.out.println("--------------------------------------");
        }

        static class LavaField {
            public static Map<Integer, Set<Integer>> y = new HashMap<>();
            public static Map<Integer, Set<Integer>> x = new HashMap<>();
            public static char[][] field;

            public LavaField(char[][] field) {
                x = new HashMap<>();
                y = new HashMap<>();
                LavaField.field = field;
                for (int i = 0; i < field.length; i++) {
                    Set<Integer> set = new HashSet<>();
                    for (int j = 0; j < field[i].length; j++) {
                        if (field[i][j] == '#')
                            set.add(j);
                    }
                    y.put(i, set);
                }

                for (int j = 0; j < field[0].length; j++) {
                    Set<Integer> set = new HashSet<>();
                    for (int i = 0; i < field.length; i++) {
                        if (field[i][j] == '#')
                            set.add(i);
                    }
                    x.put(j, set);
                }
            }

            public static long calcMirror() {
                long x = calcMirrorX();
                long y = calcMirrorY();
                return Math.max(calcMirrorX(), calcMirrorY());
            }

            public static long calcMirrorY() {
                next:
                for (int i = 1; i < y.size(); i++) {
                    for (int j = 0; (j - 1) + i < y.size() && i - j >= 0; j++) {
                        if (!y.get((j - 1) + i).equals(y.get(i - j))) {
                            continue next;
                        }
                    }
                    return (long) i * 100;
                }
                return 0;
            }

            public static long calcMirrorX() {
                next:
                for (int i = 1; i < x.size(); i++) {
                    for (int j = 0; (j - 1) + i < x.size() && i - j >= 0; j++) {
                        if (!x.get((j - 1) + i).equals(x.get(i - j))) {
                            continue next;
                        }
                    }
                    return i;
                }
                return 0;
            }
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

            List<String> field = new ArrayList<>();

            int lineNr = 0;
            while (sc.hasNext()) {

                String line = sc.nextLine();
                if (line == null || line.isEmpty() || line.isBlank()) {
                    LavaField f = new LavaField(field.stream().map(String::toCharArray).toArray(char[][]::new));
                    result += LavaField.calcMirror();
                    field = new ArrayList<>();
                    continue;
                }
                field.add(line);

            }


            System.out.println(result);
            System.out.println("--------------------------------------");
        }

        static class LavaField {
            public static Map<Integer, Set<Integer>> y = new HashMap<>();
            public static Map<Integer, Set<Integer>> x = new HashMap<>();
            public static char[][] field;

            public LavaField(char[][] field) {
                x = new HashMap<>();
                y = new HashMap<>();
                LavaField.field = field;
                for (int i = 0; i < field.length; i++) {
                    Set<Integer> set = new HashSet<>();
                    for (int j = 0; j < field[i].length; j++) {
                        if (field[i][j] == '#')
                            set.add(j);
                    }
                    y.put(i, set);
                }

                for (int j = 0; j < field[0].length; j++) {
                    Set<Integer> set = new HashSet<>();
                    for (int i = 0; i < field.length; i++) {
                        if (field[i][j] == '#')
                            set.add(i);
                    }
                    x.put(j, set);
                }
            }

            public static long calcMirror() {
                long x = calcMirrorX();
                long y = calcMirrorY();
                return Math.max(calcMirrorX(), calcMirrorY());
            }

            public static long calcMirrorY() {
                next:
                for (int i = 1; i < y.size(); i++) {
                    long smudge = 0;

                    for (int j = 1; (j - 1) + i < y.size() && i - j >= 0; j++) {

                        Set<Integer> both = y.get((j - 1) + i).stream().filter(y.get(i-j)::contains).collect(Collectors.toSet());
                        smudge += y.get((j - 1) + i).stream().filter((elem) -> !both.contains(elem)).count();
                        smudge += y.get(i-j).stream().filter((elem) -> !both.contains(elem)).count();

                        if (smudge > 1L)
                            continue next;
                    }
                    if(smudge == 1L) {
                        return (long) i * 100;
                    }
                }
                return 0;
            }

            public static long calcMirrorX() {
                next:
                for (int i = 1; i < x.size(); i++) {
                    long smudge = 0;
                    for (int j = 1; (j - 1) + i < x.size() && i - j >= 0; j++) {

                        Set<Integer> both = x.get((j - 1) + i).stream().filter(x.get(i-j)::contains).collect(Collectors.toSet());
                        smudge += x.get((j - 1) + i).stream().filter((elem) -> !both.contains(elem)).count();
                        smudge += x.get(i-j).stream().filter((elem) -> !both.contains(elem)).count();

                        if (smudge > 1L)
                            continue next;
                    }
                    if(smudge == 1L)
                        return (long) i;
                }
                return 0;
            }
        }
    }
}