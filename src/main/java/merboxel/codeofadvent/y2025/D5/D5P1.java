package merboxel.codeofadvent.y2025.D5;


import merboxel.codeofadvent.AoC;
import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.*;

public class D5P1 extends AoC {

    private static final int year = 2025;
    private static final int day = 5;
    private static final int part = 1;

    public D5P1(Scanner sc) throws IOException {
        super(year, day, part, sc);
    }

    public D5P1() throws IOException {
        super(year, day, part);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("--------------- Part 1 ---------------");
        System.out.println(new D5P1().run());
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
        long[][] freshRanges = freshRangesAsLine.stream()
                .map(line -> line.split("-"))
                .map(pair -> Arrays.stream(pair).mapToLong(Long::parseLong).toArray())
                .toArray(long[][]::new);

        long[] ids = Arrays.stream(ScannerUtil.toArray(sc))
                .mapToLong(Long::parseLong)
                .toArray();

        long result = 0;

        outer : for(long id: ids) {
            for(long[] freshRange: freshRanges) {
                if(freshRange[0] <= id && id <= freshRange[1]) {
                    result ++;
                    continue outer;
                }
            }
        }

        return Long.toString(result);
    }
}