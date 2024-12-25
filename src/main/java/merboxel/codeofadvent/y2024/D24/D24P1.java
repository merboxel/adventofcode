package merboxel.codeofadvent.y2024.D24;

import merboxel.codeofadvent.AoC;

import javax.swing.*;
import java.io.IOException;
import java.util.*;

public class D24P1 extends AoC {

    private static final int year = 2024;
    private static final int day = 24;
    private static final int part = 1;

    public D24P1(Scanner sc) {
        super(year, day, part, sc);
    }

    public D24P1() throws IOException {
        super(year, day, part);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("--------------- Part 1 ---------------");
        System.out.println(new D24P1().run());
        System.out.println("--------------------------------------");
    }

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

                if(tmp[4].charAt(0) == 'z') {
                    sortedZ.add(tmp[4]);
                }

                switch (tmp[1]) {
                    case "AND" -> gates.add(new Gate.AND(tmp[0],tmp[2], tmp[4]));
                    case "OR" -> gates.add(new Gate.OR(tmp[0],tmp[2], tmp[4]));
                    case "XOR" -> gates.add(new Gate.XOR(tmp[0],tmp[2], tmp[4]));
                }
            }
        }
        Set<Gate> usedGates = new HashSet<>();

        while(!gates.isEmpty()) {
            gates.removeAll(usedGates);
            for(Gate gate : gates) {
                if(gate.canEvaluate(wires)) {
                    usedGates.add(gate);
                    gate.evaluate(wires);
                }
            }
        }

        long result = 0;

        for(String z : sortedZ) {
            result = (result << 1) + wires.get(z);
        }

        return Long.toString(result);
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
        }
    }
}
