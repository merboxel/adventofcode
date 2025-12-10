package merboxel.codeofadvent.y2025.D10;


import merboxel.codeofadvent.AoC;
import merboxel.codeofadvent.structure.Point2D;
import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class D10P1 extends AoC {

    private static final int year = 2025;
    private static final int day = 10;
    private static final int part = 1;

    public D10P1(Scanner sc) throws IOException {
        super(year, day, part, sc);
    }

    public D10P1() throws IOException {
        super(year, day, part);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("--------------- Part 1 ---------------");
        System.out.println(new D10P1().run());
        System.out.println("--------------------------------------");
    }

    public String run() throws IOException {

        List<String> input = ScannerUtil.toList(sc);

        long result= input.stream().map(line -> line.replaceAll("[\\[\\]\\\\()]", "").split(" "))
                .map(Machine::new)
                .mapToLong(machine -> machine.calculate(0,0L,0L))
                .sum();

        return Long.toString(result);
    }

    class Machine {

        long target;
        long[] buttons;

        Machine(String[] input) {
            this.target = parseMachine(input[0]);
            this.buttons = parseButtons(Arrays.copyOfRange(input, 1, input.length-1));
        }

        private long parseMachine(String machine) {
            long result = 0;
            for(char c : new StringBuilder(machine).reverse().toString().toCharArray()) {
                result = result << 1;
                if(c == '#') {
                    result ++;
                }
            }
            return result;
        }

        private long[] parseButtons(String[] buttons) {
            long[] result = new long[buttons.length];
            for(int i = 0; i < buttons.length; i++) {
                result[i] = Arrays.stream(buttons[i].split(","))
                        .mapToLong(Long::parseLong)
                        .map(nbr -> 1L << nbr)
                        .sum();
            }
            return result;
        }

        public long calculate(int index, long presses, long current) {
            if(current == target) {
                return presses;
            }

            if(index >= buttons.length) {
                return Long.MAX_VALUE;
            }

            return Math.min(this.calculate(index + 1, presses, current), this.calculate(index + 1, presses + 1, current ^ buttons[index]));
        }
    }
}