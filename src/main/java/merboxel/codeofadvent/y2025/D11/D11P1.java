package merboxel.codeofadvent.y2025.D11;


import merboxel.codeofadvent.AoC;
import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.*;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

public class D11P1 extends AoC {

    private static final int year = 2025;
    private static final int day = 11;
    private static final int part = 1;

    public D11P1(Scanner sc) throws IOException {
        super(year, day, part, sc);
    }

    public D11P1() throws IOException {
        super(year, day, part);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("--------------- Part 1 ---------------");
        System.out.println(new D11P1().run());
        System.out.println("--------------------------------------");
    }

    public String run() throws IOException {

        List<String> input = ScannerUtil.toList(sc);

        Map<String, String[]> devices = input.stream().map(line -> line.split(": "))
                .collect(Collectors.toMap(
                        split -> split[0],
                        split -> split[1].split(" "),
                        (a, b) -> a,
                        HashMap::new
                ));

        HashMap<String, Long> routes = new HashMap<>();
        routes.put("out", 1L);

        long result = recursive(routes, devices, "you");

        return Long.toString(result);
    }

    public long recursive(HashMap<String, Long> routes, Map<String, String[]> devices, String input) {
        if(routes.containsKey(input)) {
            return routes.get(input);
        }

        routes.put(input, Arrays.stream(devices.get(input)).mapToLong(device -> recursive(routes, devices, device))
                .sum());
        return routes.get(input);
    }
}