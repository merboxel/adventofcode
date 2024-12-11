package merboxel.codeofadvent.y2024;


import merboxel.codeofadvent.util.PatternMatching;
import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D11 {

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2024, 11);
    }

    static class P1 {
        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 1 ---------------");
            Scanner sc = readFile();

            List<Long> sequence = PatternMatching.getRationNumbersAsLongs(sc.nextLine());
            List<Long> _sequence = new ArrayList<>();
            HashMap<Long, List<Long>> mapping = new HashMap<>();

            mapping.put(0L, List.of(1L));

            while(sc.hasNextLong()) {
                sequence.add(sc.nextLong());
            }

            for(int i = 0; i < 25; i++) {
                for(Long stone: sequence) {
                    if(!mapping.containsKey(stone)) {
                        if(stone == 0) {

                        } else if ((stone + "").length() % 2 == 0) {
                            String _stone = stone + "";

                            mapping.put(stone, List.of(
                                    Long.parseLong(_stone.substring(0,_stone.length()/2)),
                                    Long.parseLong(_stone.substring(_stone.length()/2))
                            ))
                            ;
                        } else {
                            mapping.put(stone, List.of(stone*2024));
                        }
                    }

                    _sequence.addAll(mapping.get(stone));
                }
                sequence = _sequence;
                _sequence = new ArrayList<>();
            }

            System.out.println(sequence.size());
            System.out.println("--------------------------------------");
        }
    }

    static class P2 {

        static HashMap<Long, Long[]> mapping = new HashMap<>();

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 2 ---------------");
            Scanner sc = readFile();

            List<Long> sequence = PatternMatching.getRationNumbersAsLongs(sc.nextLine());

            while(sc.hasNextLong()) {
                sequence.add(sc.nextLong());
            }

            Long result = 0L;

            for(Long stone: sequence) {
                result += recursive(stone,0);
            }

            System.out.println(result);
            System.out.println("--------------------------------------");
        }

        public static long recursive(Long stone, int depth) {
            if(depth >= 75)
                return 1L;

            if(mapping.containsKey(stone) && mapping.get(stone)[depth] != null) {
                return mapping.get(stone)[depth];
            }

            int nextDepth = depth + 1;
            long result = 0L;

            if(stone == 0) {
                result += recursive(1L, nextDepth);
            } else if ((stone + "").length() % 2 == 0) {
                String _stone = stone + "";

                result += recursive(Long.parseLong(_stone.substring(0,_stone.length()/2)), nextDepth);
                result += recursive(Long.parseLong(_stone.substring(_stone.length()/2)), nextDepth);
            } else {
                result += recursive(stone*2024,nextDepth);
            }

            Long[] map = mapping.getOrDefault(stone, new Long[75]);
            map[depth] = result;
            mapping.put(stone, map);

            return result;
        }
    }
}