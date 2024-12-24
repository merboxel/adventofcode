package merboxel.codeofadvent.y2024.D24;

import merboxel.codeofadvent.AoC;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class D24P2_3 extends AoC {

    public D24P2_3(Scanner sc) {
        super(2024, 24, 2, sc);
    }

    public D24P2_3() throws IOException {
        super(2024, 24, 2);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("--------------- Part 2 ---------------");
        System.out.println(new D24P2_3().run());
        System.out.println("--------------------------------------");
    }

    // Used for manual looking in notepad
    public String run() {

        boolean inputInitWire = true;
        SortedMap<String, Integer> wires = new TreeMap<>();
        SortedSet<String> sortedZ = new TreeSet<>(Collections.reverseOrder());
        Set<Gate> gates = new HashSet<>();

        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            if(line.isEmpty())
                inputInitWire = false;
            else if(inputInitWire) {
                String[] tmp = line.split(": ");

                wires.put(tmp[0], Integer.valueOf(tmp[1]));
            } else {
                String[] tmp = line.split(" ");

                wires.putIfAbsent(tmp[0], -1);
                wires.putIfAbsent(tmp[2], -1);
                wires.putIfAbsent(tmp[4], -1);

                switch (tmp[1]) {
                    case "AND" -> gates.add(new Gate.AND(tmp[0],tmp[2], tmp[4]));
                    case "OR" -> gates.add(new Gate.OR(tmp[0],tmp[2], tmp[4]));
                    case "XOR" -> gates.add(new Gate.XOR(tmp[0],tmp[2], tmp[4]));
                }
            }
        }

        SortedSet<String> sorted = new TreeSet<>(sortedZ);

        gates.stream().map(Gate::toString).forEach(sorted::add);

        return String.join("\n", sorted);
    }

    private abstract static class Gate {
        protected final String wire1, wire2, output;
        protected int result;

        protected Gate(String wire1, String wire2, String output) {
            this.wire1 = wire1;
            this.wire2 = wire2;
            this.output = output;
        }
        public abstract void evaluate(Map<String, Integer> wires);
        public boolean canEvaluate(Map<String, Integer> wires) {
            if(wires.get(wire1) == -1 || wires.get(wire2) == -1)
                return false;
            return true;
        }

        public boolean isOutPut(String output) {
            return this.output.equals(output);
        }

        public String getWire1() {
            return (wire1.compareTo(wire2) < 0) ? wire1 : wire2;
        }

        public String getWire2() {
            return (wire1.compareTo(wire2) < 0) ? wire2 : wire1;
        }

        public abstract String toString();

        private static class AND extends Gate {

            protected AND(String wire1, String wire2, String output) {
                super(wire1, wire2, output);
            }

            public void evaluate(Map<String, Integer> wires) {
                Integer _wire1 = wires.get(wire1);
                Integer _wire2 = wires.get(wire2);
                result = _wire1 & _wire2;

                wires.put(output, result);
            }

            @Override
            public String toString() {
                return this.output + " = " + this.getWire1() + " AND " + this.getWire2();
            }
        }

        private static class OR extends Gate {

            protected OR(String wire1, String wire2, String output) {
                super(wire1, wire2, output);
            }

            public void evaluate(Map<String, Integer> wires) {
                Integer _wire1 = wires.get(wire1);
                Integer _wire2 = wires.get(wire2);
                result = _wire1 | _wire2;

                wires.put(output, result);
            }

            @Override
            public String toString() {
                return this.output + " = " + this.getWire1() + " OR " + this.getWire2();
            }
        }

        private static class XOR extends Gate {

            protected XOR(String wire1, String wire2, String output) {
                super(wire1, wire2, output);
            }

            public void evaluate(Map<String, Integer> wires) {
                Integer _wire1 = wires.get(wire1);
                Integer _wire2 = wires.get(wire2);
                result = _wire1 ^ _wire2;

                wires.put(output, result);
            }

            @Override
            public String toString() {
                return this.output + " = " + this.getWire1() + " XOR " + this.getWire2();
            }
        }
    }
}
