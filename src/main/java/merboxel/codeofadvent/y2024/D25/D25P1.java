package merboxel.codeofadvent.y2024.D25;

import merboxel.codeofadvent.AoC;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class D25P1 extends AoC {

    public D25P1(Scanner sc) {
        super(2024, 25, 1, sc);
    }

    public D25P1() throws IOException {
        super(2024, 25, 1);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("--------------- Part 1 ---------------");
        System.out.println(new D25P1().run());
        System.out.println("--------------------------------------");
    }

    public String run() {

        List<Lock.Key> keys = new ArrayList<>();
        List<Lock.KeyHole> keyHoles = new ArrayList<>();

        while (sc.hasNextLine()) {
            List<String> input = new ArrayList<>();
            String line = sc.nextLine();
            if (line.isBlank()) {
                continue;
            } else {
                input.add(line);
                for (int i = 0; i < 6; i++) {
                    input.add(sc.nextLine());
                }
            }
            if (Lock.Key.isKey(input)) {
                keys.add(Lock.Key.createKey(input));
            }
            if (Lock.KeyHole.isKeyHole(input)) {
                keyHoles.add(Lock.KeyHole.createKeyHole(input));
            }
        }

        int result = 0;

        for (Lock.Key key : keys) {
            for (Lock.KeyHole keyHole : keyHoles) {
                result += (key.fitLock(keyHole)) ? 1 : 0;
            }
        }

        return Integer.toString(result);
    }

    private abstract static class Lock {
        private final int[] pins;

        protected Lock(int[] pins) {
            this.pins = pins;
        }

        protected boolean fitLock(Lock that) {
            for (int i = 0; i < pins.length; i++) {
                if (this.pins[i] + that.pins[i] > 5) return false;
            }
            return true;
        }

        protected static class Key extends Lock {

            private Key(int[] pins) {
                super(pins);
            }

            protected static Key createKey(List<String> _input) {
                char[][] input = _input.stream().map(String::toCharArray).toArray(char[][]::new);

                int[] pins = new int[input[0].length];

                for (int i = 1; i < input.length; i++) {
                    for (int j = 0; j < input[i].length; j++) {
                        if (input[i][j] == '#') {
                            pins[j]++;
                        }
                    }
                }

                return new Key(pins);
            }

            protected static boolean isKey(List<String> input) {
                return input.getFirst().equals("#####");
            }
        }

        protected static class KeyHole extends Lock {

            private KeyHole(int[] pins) {
                super(pins);
            }

            protected static KeyHole createKeyHole(List<String> _input) {
                char[][] input = _input.stream().map(String::toCharArray).toArray(char[][]::new);

                int[] pins = new int[input[0].length];

                for (int i = 0; i < input.length - 1; i++) {
                    for (int j = 0; j < input[i].length; j++) {
                        if (input[i][j] == '#') {
                            pins[j]++;
                        }
                    }
                }

                return new KeyHole(pins);
            }

            protected static boolean isKeyHole(List<String> input) {
                return input.getFirst().equals(".....");
            }
        }
    }
}
