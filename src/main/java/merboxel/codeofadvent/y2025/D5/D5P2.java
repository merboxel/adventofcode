package merboxel.codeofadvent.y2025.D5;


import merboxel.codeofadvent.AoC;
import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.*;

public class D5P2 extends AoC {

    private static final int year = 2025;
    private static final int day = 5;
    private static final int part = 2;

    public D5P2(Scanner sc) throws IOException {
        super(year, day, part, sc);
    }

    public D5P2() throws IOException {
        super(year, day, part);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("--------------- Part 2 ---------------");
        System.out.println(new D5P2().run());
        System.out.println("--------------------------------------");
    }

    public String run() throws IOException {

        List<String> freshRangesAsLine = new ArrayList<>();

        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            if(Objects.equals(line, "")) {
                break;
            }
            freshRangesAsLine.add(line);
        }
        Pair[] freshRanges = freshRangesAsLine.stream()
                .map(line -> line.split("-"))
                .map(pair -> {
                    long[] tmp = Arrays.stream(pair).mapToLong(Long::parseLong).toArray();
                    return new Pair(tmp[0], tmp[1]);
                })
                .sorted()
                .toArray(Pair[]::new);

        long result = 0;

        long start = -1;
        long end = -2;

        List<Pair> compactFreshRanges =  new ArrayList<>();

        for(Pair freshRange: freshRanges) {
            if(freshRange.start > end) {
                compactFreshRanges.add(new Pair(start,end));

                start = freshRange.start;
                end =  freshRange.end;
                continue;
            }

            if(freshRange.end > end) {
                end = freshRange.end;
            }
        }
        compactFreshRanges.add(new Pair(start,end));

        result = compactFreshRanges.stream().mapToLong(Pair::getRange).sum();

        return Long.toString(result);
    }

    record Pair(long start, long end) implements Comparable<Pair> {
        @Override
        public int compareTo(Pair o) {
            if(this.start == o.start &&  this.end == o.end) {
                return 0;
            }

            if(this.start < o.start ) {
                return -1;
            }
            if(this.start > o.start) {
                return 1;
            }
            if(this.end < o.end) {
                return -1;
            }
            return 1;
        }

        public long getRange() {
            return this.end - this.start + 1;
        }
    }
}