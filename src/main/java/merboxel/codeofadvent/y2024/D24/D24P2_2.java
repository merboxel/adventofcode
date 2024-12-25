package merboxel.codeofadvent.y2024.D24;

import merboxel.codeofadvent.AoC;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class D24P2_2 extends AoC {

    private static final int year = 2024;
    private static final int day = 24;
    private static final int part = 2;

    public D24P2_2(Scanner sc) {
        super(year, day, part, sc);
    }

    public D24P2_2() throws IOException {
        super(year, day, part);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("--------------- Part 2 ---------------");
        System.out.println(new D24P2_2().run());
        System.out.println("--------------------------------------");
    }

    // Used for mermaid
    public String run() {

        boolean inputInitWire = true;
        List<String> mermaid = new ArrayList<>();

        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            if(line.isEmpty())
                inputInitWire = false;
            else if(!inputInitWire) {
                String[] tmp = line.split(" ");

                switch (tmp[1]) {
                    case "AND" -> {
                        mermaid.add(tmp[0] + " --> " + tmp[4] + ":::green");
                        mermaid.add(tmp[2] + " --> " + tmp[4] + ":::green");
                    }
                    case "OR" -> {
                        mermaid.add(tmp[0] + " --> " + tmp[4] + ":::orange");
                        mermaid.add(tmp[2] + " --> " + tmp[4] + ":::orange");
                    }
                    case "XOR" -> {
                        mermaid.add(tmp[0] + " --> " + tmp[4] + ":::blue");
                        mermaid.add(tmp[2] + " --> " + tmp[4] + ":::blue");
                    }
                }
            }
        }

        return String.join("\n", mermaid);
    }

    protected abstract class Gate {
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
    }

    public class AND extends Gate {

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

    private class OR extends Gate {

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

    private class XOR extends Gate {

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
