package merboxel.codeofadvent.y2023;


import merboxel.codeofadvent.util.PatternMatching;

import java.io.IOException;
import java.util.*;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D20 {

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2023, 20);
    }

    static class P1 {

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 1 ---------------");
            long pulseHigh = 0L;
            long pulseLow = 0L;
            Scanner sc = readFile();

            Queue<Signal> queue = new LinkedList<>();
            Map<String,Module> map = new HashMap<>();
            Module first = null;

            while (sc.hasNext()) {
                String[] line = PatternMatching.getWordsAsArray(sc.nextLine());

                if(line[0].equals("broadcaster")) {
                    first = new Module("broadcaster",Arrays.copyOfRange(line,2,line.length));
                    map.put("broadcaster",first);
                } else if(line[0].charAt(0) == '%'){
                    map.put(line[0].substring(1),new FlipFlop(line[0].substring(1),Arrays.copyOfRange(line,2,line.length)));
                } else if(line[0].charAt(0) == '&'){
                    map.put(line[0].substring(1),new Conjuction(line[0].substring(1),Arrays.copyOfRange(line,2,line.length)));
                }
            }

            for(Module mod: map.values()) {
                for(Signal signal: mod.run()) {
                    if(map.containsKey(signal.currMod))
                        map.get(signal.currMod).init(signal.prevMod);
                }
            }

            assert(null != first);

            for(int i = 0; i < 1000; i++) {
                queue.addAll(first.run());
                pulseLow ++;
                while(!queue.isEmpty()) {
                    Signal signal = queue.poll();
                    if(signal.pulse)
                        pulseHigh ++;
                    else
                        pulseLow ++;

                    if(map.containsKey(signal.currMod))
                        queue.addAll(map.get(signal.currMod).run(signal));
                }
            }

            System.out.println(pulseHigh * pulseLow);
            System.out.println("--------------------------------------");
        }

        static class Signal {
            boolean pulse;
            String prevMod;
            String currMod;
            public Signal(boolean pulse, String prevMod, String currMod) {
                this.pulse = pulse;
                this.prevMod = prevMod;
                this.currMod = currMod;
            }
        }

        static class Module {
            Set<String> destMods = new HashSet<>();
            String currMod;

            public Module(String currMod, String[] dests) {
                this.currMod = currMod;
                for(String dest: dests)
                    this.destMods.add(dest.replace(",",""));
            }
            public List<Signal> run() {
                List<Signal> next = new ArrayList<>();
                for(String destMod: destMods){
                    next.add(new Signal(false,currMod,destMod));
                }
                return next;
            }
            public List<Signal> run(Signal signal) { throw new RuntimeException("Not implemented");}
            public void init(String remember) {}
        }
        static class FlipFlop extends Module {
            boolean on = false;

            public FlipFlop(String currMod, String[] dests) {
                super(currMod, dests);
            }

            public List<Signal> run(Signal signal) {
                if(signal.pulse)
                    return List.of();

                List<Signal> next = new ArrayList<>();
                for(String destMod: destMods){
                    next.add(new Signal(!on,currMod,destMod));
                }
                on = !on;
                return next;
            }
        }

        static class Conjuction extends Module {
            Map<String,Boolean> remember = new HashMap<>();

            public Conjuction(String currMod, String[] dests) {
                super(currMod, dests);
            }

            public List<Signal> run(Signal signal) {
                remember.put(signal.prevMod,signal.pulse);
                for(Boolean pulse: remember.values()) {
                    if(!pulse) {
                        return createDest(true);
                    }
                }
                return createDest(false);
            }

            private List<Signal> createDest(boolean pulse) {
                List<Signal> next = new ArrayList<>();
                for(String destMod: destMods){
                    next.add(new Signal(pulse,currMod,destMod));
                }
                return next;
            }

            public void init(String remember) {
                this.remember.put(remember,false);
            }
        }
    }

    static class P2 {


        /**
         *   Data analyse provides the following info
         *
         *                 $RX
         *          /     |    \     \
         *       $FH    $MF   $FZ   $SS
         *        |      |     |     |
         *       $SN    $HL   $TF   $LR
         *
         *   Find loops for FH & MF & FZ & SS
         */

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 2 ---------------");
            long result = 0L;
            Scanner sc = readFile();

            Queue<Signal> queue = new LinkedList<>();
            Map<String, Module> map = new HashMap<>();
            Module first = null;

            while (sc.hasNext()) {
                String[] line = PatternMatching.getWordsAsArray(sc.nextLine());

                if(line[0].equals("broadcaster")) {
                    first = new Module("broadcaster",Arrays.copyOfRange(line,2,line.length));
                    map.put("broadcaster",first);
                } else if(line[0].charAt(0) == '%'){
                    map.put(line[0].substring(1),new FlipFlop(line[0].substring(1),Arrays.copyOfRange(line,2,line.length)));
                } else if(line[0].charAt(0) == '&'){
                    map.put(line[0].substring(1),new Conjuction(line[0].substring(1),Arrays.copyOfRange(line,2,line.length)));
                }
            }

            for(Module mod: map.values()) {
                for(Signal signal: mod.run()) {
                    if(map.containsKey(signal.currMod))
                        map.get(signal.currMod).init(signal.prevMod);
                }
            }

            assert(null != first);

            int[] cycles = new int[] {0,0,0,0};

            next: for(int i = 1; ; i++) {
                queue.addAll(first.run());
                while(!queue.isEmpty()) {
                    Signal signal = queue.poll();

                    if(cycles[0] != 0 && cycles[1] != 0 &&cycles[2] != 0 && cycles[3] != 0)
                        break next;

                    if(signal.currMod.equals("fh") && !signal.pulse && cycles[0] == 0) {
                        cycles[0] = i;
//                        System.out.println("fh loop found at i: '"+i+"'");
                    }
                    if(signal.currMod.equals("mf") && !signal.pulse && cycles[1] == 0) {
                        cycles[1] = i;
//                        System.out.println("mf loop found at i: '"+i+"'");
                    }
                    if(signal.currMod.equals("fz") && !signal.pulse && cycles[2] == 0) {
                        cycles[2] = i;
//                        System.out.println("fz loop found at i: '"+i+"'");
                    }
                    if(signal.currMod.equals("ss") && !signal.pulse && cycles[3] == 0) {
                        cycles[3] = i;
//                        System.out.println("ss loop found at i: '"+i+"'");
                    }

                    if(map.containsKey(signal.currMod))
                        queue.addAll(map.get(signal.currMod).run(signal));
                }
            }

            result = cycles[0];

            // Find cycle length where all send high pulse
            next : for (int loop : cycles) {
                long tmp = 0;
                for (int j = 0; j < loop; j++) {
                    tmp += result;
                    if (tmp % loop == 0) {
                        result = tmp;
                        continue next;
                    }
                }
            }

            System.out.println(result);
            System.out.println("--------------------------------------");
        }

        static class Signal {
            boolean pulse;
            String prevMod;
            String currMod;
            public Signal(boolean pulse, String prevMod, String currMod) {
                this.pulse = pulse;
                this.prevMod = prevMod;
                this.currMod = currMod;
            }
        }

        static class Module {
            Set<String> destMods = new HashSet<>();
            String currMod;

            public Module(String currMod, String[] dests) {
                this.currMod = currMod;
                for(String dest: dests)
                    this.destMods.add(dest.replace(",",""));
            }
            public List<Signal> run() {
                List<Signal> next = new ArrayList<>();
                for(String destMod: destMods){
                    next.add(new Signal(false,currMod,destMod));
                }
                return next;
            }
            public List<Signal> run(Signal signal) { throw new RuntimeException("Not implemented");}
            public void init(String remember) {}
        }

        static class FlipFlop extends Module {
            boolean on = false;

            public FlipFlop(String currMod, String[] dests) {
                super(currMod, dests);
            }

            public List<Signal> run(Signal signal) {
                if(signal.pulse)
                    return List.of();

                List<Signal> next = new ArrayList<>();
                for(String destMod: destMods){
                    next.add(new Signal(!on,currMod,destMod));
                }
                on = !on;
                return next;
            }
        }

        static class Conjuction extends Module {
            Map<String,Boolean> remember = new HashMap<>();

            public Conjuction(String currMod, String[] dests) {
                super(currMod, dests);
            }

            public List<Signal> run(Signal signal) {
                remember.put(signal.prevMod,signal.pulse);
                for(Boolean pulse: remember.values()) {
                    if(!pulse) {
                        return createDest(true);
                    }
                }
                return createDest(false);
            }

            private List<Signal> createDest(boolean pulse) {
                List<Signal> next = new ArrayList<>();
                for(String destMod: destMods){
                    next.add(new Signal(pulse,currMod,destMod));
                }
                return next;
            }

            public void init(String remember) {
                this.remember.put(remember,false);
            }
        }
    }
}