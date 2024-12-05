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
        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 1 ---------------");
            Scanner sc = readFile();

            int result = 0;

            Map<Integer, Set<Integer>> map = new HashMap<>();

            String[] lines = ScannerUtil.toList(sc).toArray(new String[0]);

            AtomicBoolean p1 = new AtomicBoolean(true);

            for (String line : lines) {
                if (line.isEmpty()) {
                    p1.set(false);
                } else if (p1.get()) {
                    int[] tmp = Arrays.stream(line.split("\\|")).mapToInt(Integer::parseInt).toArray();
                    int f = tmp[0];
                    int s = tmp[1];
                    Set<Integer> set = map.getOrDefault(s, new HashSet<>());
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

        public static boolean validSeq(Map<Integer, Set<Integer>> map, List<Integer> seq) {

            Set<Integer> _visit = new HashSet<>();

            for (int elem : seq) {
                Set<Integer> tmp = map.getOrDefault(elem, new HashSet<>());
                Set<Integer> copy = new HashSet<>(tmp);

                if(copy.stream().anyMatch(i -> !_visit.contains(i) && seq.contains(i)))
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

            Map<Integer, Set<Integer>> map = new HashMap<>();

            String[] lines = ScannerUtil.toList(sc).toArray(new String[0]);

            AtomicBoolean p1 = new AtomicBoolean(true);

            for (String line : lines) {
                if (line.isEmpty()) {
                    p1.set(false);
                } else if (p1.get()) {
                    int[] tmp = Arrays.stream(line.split("\\|")).mapToInt(Integer::parseInt).toArray();
                    int f = tmp[0];
                    int s = tmp[1];
                    Set<Integer> set = map.getOrDefault(s, new HashSet<>());
                    set.add(f);
                    map.put(s, set);
                } else {
                    List<Integer> seq = Arrays.stream(line.split(",")).map(Integer::parseInt).toList();

                    if (!validSeq(map, seq)) {
                        result += invalidSeq(map,seq);
                    }
                }
            }

            System.out.println(result);
            System.out.println("--------------------------------------");
        }

        public static int invalidSeq(Map<Integer, Set<Integer>> map, List<Integer> seq) {

            List<Integer> result = new ArrayList<>();

            Set<Integer> _visit = new HashSet<>();
            Map<Integer, Set<Integer>> _map = new HashMap<>();

            for(int i : seq) {
                Set<Integer> tmp = map.getOrDefault(i,new HashSet<>()).stream().filter(seq::contains).collect(Collectors.toSet());
                _map.put(i,tmp);
            }

            for(int i = 0; i < seq.size(); i++) {
                for(int j : seq)  {
                    if(_visit.contains(j))
                        continue;

                    if(_map.get(j).isEmpty()) {
                        _map.forEach((key, value) -> value.remove(j));
                        _visit.add(j);
                        result.add(j);
                        break;
                    }
                }
            }
            return result.get(result.size()/2);
        }

        public static boolean validSeq(Map<Integer, Set<Integer>> map, List<Integer> seq) {

            Set<Integer> _visit = new HashSet<>();

            for (int elem : seq) {
                Set<Integer> tmp = map.getOrDefault(elem,new HashSet<>());
                Set<Integer> copy = new HashSet<>(tmp);

                if (copy.stream().anyMatch(i -> !_visit.contains(i) && seq.contains(i)))
                    return false;
                _visit.add(elem);
            }

            return true;
        }
    }
}