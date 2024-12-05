package merboxel.codeofadvent.y2024;


import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D5 {

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2024, 5);
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

            HashMap<Integer, HashSet<Integer>> map = new HashMap<>();

            String[] lines = ScannerUtil.toList(sc).toArray(new String[0]);

            AtomicBoolean p1 = new AtomicBoolean(true);

            for (String line : lines) {
                if (line.isEmpty()) {
                    p1.set(false);
                    continue;
                }

                if (p1.get()) {
                    String[] tmp = line.split("\\|");
                    Integer f = Integer.parseInt(tmp[0]);
                    Integer s = Integer.parseInt(tmp[1]);
                    HashSet<Integer> set = map.getOrDefault(s, new HashSet<>());
                    set.add(f);
                    map.put(s, set);
                } else {
                    List<Integer> seq = Arrays.stream(line.split(",")).map(Integer::parseInt).toList();

                    if (validSeq(map, seq)) {
                        result += seq.get(seq.size() / 2);
                    }
                }
            }

            System.out.println(result);
            System.out.println("--------------------------------------");
        }

        public static boolean validSeq(HashMap<Integer, HashSet<Integer>> map, List<Integer> seq) {

            HashSet<Integer> _seq = new HashSet<>(seq);
            HashSet<Integer> _visit = new HashSet<>();


            for (int elem : seq) {

                HashSet<Integer> tmp = map.get(elem);

                if(tmp == null)
                    tmp = new HashSet<>();
                HashSet<Integer> copy = new HashSet<>(tmp);


                Set<Integer> _copy = copy.stream().filter(i -> !_visit.contains(i) && _seq.contains(i)).collect(Collectors.toSet());
                if(!_copy.isEmpty())
                    return false;
                _visit.add(elem);
            }

            return true;
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

            HashMap<Integer, HashSet<Integer>> map = new HashMap<>();

            String[] lines = ScannerUtil.toList(sc).toArray(new String[0]);

            AtomicBoolean p1 = new AtomicBoolean(true);

            for (String line : lines) {
                if (line.isEmpty()) {
                    p1.set(false);
                    continue;
                }

                if (p1.get()) {
                    String[] tmp = line.split("\\|");
                    Integer f = Integer.parseInt(tmp[0]);
                    Integer s = Integer.parseInt(tmp[1]);
                    HashSet<Integer> set = map.getOrDefault(s, new HashSet<>());
                    set.add(f);
                    map.put(s, set);
                } else {
                    List<Integer> seq = Arrays.stream(line.split(",")).map(Integer::parseInt).toList();

                    if (!validSeq(map, seq)) {

                    }
                }
            }

            System.out.println(result);
            System.out.println("--------------------------------------");
        }

        public static void invalidSeq(HashMap<Integer, HashSet<Integer>> map, List<Integer> seq) {

            HashSet<Integer> _seq = new HashSet<>(seq);
            HashSet<Integer> _visit = new HashSet<>();
//
//            for(int i = 0; i < seq.size(); i ++) {
//
//            }
        }

        public static boolean validSeq(HashMap<Integer, HashSet<Integer>> map, List<Integer> seq) {

            HashSet<Integer> _seq = new HashSet<>(seq);
            HashSet<Integer> _visit = new HashSet<>();


            for (int elem : seq) {

                HashSet<Integer> tmp = map.get(elem);

                if (tmp == null)
                    tmp = new HashSet<>();
                HashSet<Integer> copy = new HashSet<>(tmp);


                Set<Integer> _copy = copy.stream().filter(i -> !_visit.contains(i) && _seq.contains(i)).collect(Collectors.toSet());
                if (!_copy.isEmpty())
                    return false;
                _visit.add(elem);
            }

            return true;
        }
    }
}