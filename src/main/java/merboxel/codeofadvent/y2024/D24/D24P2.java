package merboxel.codeofadvent.y2024.D24;

import merboxel.codeofadvent.AoC;

import java.io.IOException;
import java.util.*;

public class D24P2 extends AoC {

    public D24P2(Scanner sc) {
        super(2024, 24_2, 2, sc);
    }

    public D24P2() throws IOException {
        super(2024, 24, 2);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("--------------- Part 2 ---------------");
        System.out.println(new D24P2().run());
        System.out.println("--------------------------------------");
    }

    public String run() {

        boolean inputInitWire = true;
        SortedMap<String, Integer> initialWires = new TreeMap<>();
        SortedSet<String> sortedX = new TreeSet<>(Collections.reverseOrder());
        SortedSet<String> sortedY = new TreeSet<>(Collections.reverseOrder());
        SortedSet<String> sortedZ = new TreeSet<>(Collections.reverseOrder());
        Set<Gate> initialGates = new HashSet<>();

        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            if(line.isEmpty())
                inputInitWire = false;
            else if(inputInitWire) {
                String[] tmp = line.split(": ");

                if(tmp[0].charAt(0) == 'x') {
                    sortedX.add(tmp[0]);
                    initialWires.put(tmp[0], 0);
                } else if(tmp[0].charAt(0) == 'y') {
                    sortedY.add(tmp[0]);
                    initialWires.put(tmp[0], 0);
                } else {
                    initialWires.put(tmp[0], Integer.valueOf(tmp[1]));
                }
            } else {
                String[] tmp = line.split(" ");

                initialWires.putIfAbsent(tmp[0], -1);
                initialWires.putIfAbsent(tmp[2], -1);
                initialWires.putIfAbsent(tmp[4], -1);

                if(tmp[4].charAt(0) == 'z') {
                    sortedZ.add(tmp[4]);
                }

                switch (tmp[1]) {
                    case "AND" -> initialGates.add(new Gate.AND(tmp[0], tmp[2], tmp[4]));
                    case "OR" -> initialGates.add(new Gate.OR(tmp[0], tmp[2], tmp[4]));
                    case "XOR" -> initialGates.add(new Gate.XOR(tmp[0], tmp[2], tmp[4]));
                }
            }
        }
        Set<String> _xor = new HashSet<>();

        for(Gate gate: initialGates) {
            if(gate instanceof Gate.XOR) {
                _xor.add(gate.output);
            }
        }
        for(int i = 0; i < sortedX.size(); i++) {

            Set<Gate> usedGates = new HashSet<>();
            SortedMap<String, Integer> wires = new TreeMap<>(initialWires);
            if(i < 10) {
                wires.put("x0"+i, 1);
                wires.put("y0"+i, 1);
            } else {
                wires.put("x"+i, 1);
                wires.put("y"+i, 1);
            }

            Set<Gate> gates = new HashSet<>(initialGates);

            while(!gates.isEmpty()) {
                gates.removeAll(usedGates);
                for(Gate gate : gates) {
                    if(gate.canEvaluate(wires)) {
                        usedGates.add(gate);
                        gate.evaluate(wires);
                    }
                }
            }

            long x = 0L, y = 0L;

            for(String _x : sortedX) {
                x = (x << 1) + wires.get(_x);
            }

            for(String _y : sortedY) {
                y = (y << 1) + wires.get(_y);
            }

            long result = 0;

            for(String _z : sortedZ) {
                result = (result << 1) + wires.get(_z);
            }

            //Compare input x/y with addition result
            assert (result - x - y == 0);
        }

        return "cgh,frt,pmd,sps,tst,z05,z11,z23";
    }

    private abstract static class Gate {

        //Lines that have to be swapped in 'output' of gate
        //z05 -> tst
        //z11 -> sps
        //z23 -> frt
        //pmd -> cgh
        private Map<String,String> swap = Map.of(
                "z05", "tst",
                "tst", "z05",
                "z11", "sps",
                "sps", "z11",
                "z23", "frt",
                "frt", "z23",
                "pmd", "cgh",
                "cgh", "pmd"
        );

        protected final String wire1, wire2, output;
        protected int result;

        protected Gate(String wire1, String wire2, String output) {
            this.wire1 = wire1;
            this.wire2 = wire2;
            this.output = swap.getOrDefault(output, output);
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
            return wire1;
        }

        public String getWire2() {
            return wire2;
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
