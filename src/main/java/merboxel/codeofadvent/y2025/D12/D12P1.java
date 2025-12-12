package merboxel.codeofadvent.y2025.D12;


import merboxel.codeofadvent.AoC;
import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class D12P1 extends AoC {

    private static final int year = 2025;
    private static final int day = 12;
    private static final int part = 1;

    public D12P1(Scanner sc) throws IOException {
        super(year, day, part, sc);
    }

    public D12P1() throws IOException {
        super(year, day, part);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("--------------- Part 1 ---------------");
        System.out.println(new D12P1().run());
        System.out.println("--------------------------------------");
    }

    public String run() throws IOException {

        String[][] symbols = new String[6][3];

        for(int i = 0; i < 6; i++) {
            sc.nextLine();
            symbols[i][0] = sc.nextLine();
            symbols[i][1] = sc.nextLine();
            symbols[i][2] = sc.nextLine();
            sc.nextLine();
        }

        ChristmasTree[] trees = ScannerUtil.toList(sc).stream()
                .map(line -> line.split(": "))
                .map(split ->
                    new ChristmasTree(
                        Arrays.stream(split[0].split("x"))
                                .mapToLong(Long::parseLong)
                                .reduce(1L,
                                        (a, b) -> a * b)
                        ,
                        Arrays.stream(split[1].split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray()
                    )
                ).toArray(ChristmasTree[]::new);

        long result = Arrays.stream(trees).map(tree -> {
            long areaToCover = 0;
            for(int i = 0; i < 6; i++) {
                areaToCover += 9 * tree.present[i];
            }
            return tree.area - areaToCover;
        }).filter(l -> l >= 0)
                .mapToLong( l -> 1)
                .sum();

        return Long.toString(result);
    }

    record ChristmasTree(long area, int[] present) {}
}