package merboxel.codeofadvent.y2025.D11;


import merboxel.codeofadvent.AoC;
import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class D11P2 extends AoC {

    private static final int year = 2025;
    private static final int day = 11;
    private static final int part = 1;

    public D11P2(Scanner sc) throws IOException {
        super(year, day, part, sc);
    }

    public D11P2() throws IOException {
        super(year, day, part);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("--------------- Part 1 ---------------");
        System.out.println(new D11P2().run());
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

        HashMap<String, Route> routes = new HashMap<>();
        routes.put("out", new Route(new long[]{0, 0, 0, 1}));

        long result = recursive(routes, devices, "svr").route[0];

        return Long.toString(result);
    }

    private Route recursive(Map<String, Route> routes, Map<String, String[]> devices, String input) {
        if (routes.containsKey(input)) {
            return routes.get(input);
        }

        Route newRoute = Arrays.stream(devices.get(input)).map(route -> recursive(routes, devices, route))
                .reduce(new Route(new long[]{0L, 0L, 0L, 0L}), Route::merge);

        if (input.equals("fft")) {
            newRoute = newRoute.seenFFT();
        }

        if (input.equals("dac")) {
            newRoute = newRoute.seenDAC();
        }

        routes.put(input, newRoute);

        return routes.get(input);
    }

    // pos 0: visited dac & fft
    // pos 1: visited fft
    // pos 2: visited dac
    // pos 3: visited -
    record Route(long[] route) {

        public Route seenFFT() {
            return new Route(new long[]{route[0] + route[2], route[3], 0, 0});
        }

        public Route seenDAC() {
            return new Route(new long[]{route[0] + route[1], 0, route[3], 0});
        }

        public Route merge(Route that) {
            return new Route(new long[]{
                    this.route[0] + that.route[0],
                    this.route[1] + that.route[1],
                    this.route[2] + that.route[2],
                    this.route[3] + that.route[3]
            });
        }
    }
}