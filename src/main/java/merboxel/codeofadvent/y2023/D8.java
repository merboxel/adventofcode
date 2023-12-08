package merboxel.codeofadvent.y2023;

import merboxel.codeofadvent.util.PatternMatching;

import java.io.IOException;
import java.util.*;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D8 {

    public static void main(String[] args) throws IOException {
        D8P1.run();
        D8P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2023, 8);
    }
}

class D8P1 {

    public static void main(String[] args) throws IOException {
        run();
    }

    public static void run() throws IOException {
        System.out.println("--------------- Part 1 ---------------");
        int result = 0;
        Scanner sc = D8.readFile();

        Map<String,Node> tree = new HashMap<>();

        char[] pattern = sc.nextLine().toCharArray();
        sc.nextLine();

        while(sc.hasNext()) {

            String[] words = PatternMatching.getWordsAsArray(sc.nextLine());
            tree.put(words[0],new Node(words[0],words[2].substring(1,4),words[3].substring(0,3)));
        }

        Node walk = tree.get("AAA");

        while(!walk.current.equals("ZZZ")){
            char direction = pattern[result % pattern.length];
            walk = tree.get(walk.direction(direction));
            result ++;
        }

        System.out.println(result);
        System.out.println("--------------------------------------");
    }
}

class D8P2 {

    public static void main(String[] args) throws IOException {
        run();
    }

    public static void run() throws IOException {
        System.out.println("--------------- Part 2 ---------------");
        long result;
        Scanner sc = D8.readFile();

        Map<String,Node> tree = new HashMap<>();

        char[] pattern = sc.nextLine().toCharArray();
        sc.nextLine();

        List<Node> _startWalk = new ArrayList<>();

        int indexStart = 0;

        while(sc.hasNext()) {
            String[] words = PatternMatching.getWordsAsArray(sc.nextLine());

            Node newNode = new Node(words[0],words[2].substring(1,4),words[3].substring(0,3));
            if(newNode.current.endsWith("A")) {
                _startWalk.add(newNode);
                indexStart++;
            }
            tree.put(words[0],newNode);
        }
        Node[] startWalk = _startWalk.toArray(new Node[0]);

        //Find cycles
        List<Long> cycles = new ArrayList<>();

        // Cycles of all start locations have the same length
        // Otherwise we had to do something else...
        for(int entry = 0; entry <indexStart; entry++) {
            long cycle = 0;
            do {
                char direction = pattern[(int) (cycle % pattern.length)];
                startWalk[entry] = tree.get(startWalk[entry].direction(direction));
                cycle++;
            } while (!startWalk[entry].current.endsWith("Z"));
            cycles.add(cycle);
        }
        Long[] arrCycles = cycles.toArray(new Long[0]);

        result = arrCycles[0];

        //Find cycle length where all end in 'Z'
        next : for (Long arrCycle : arrCycles) {
            long tmp = 0;
            for (int j = 0; j < arrCycle; j++) {
                tmp += result;
                if (tmp % arrCycle == 0) {
                    result = tmp;
                    continue next;
                }
            }
        }
        System.out.println(result);
        System.out.println("--------------------------------------");
    }
}

class Node {
    String current;
    String left;
    String right;

    public Node(String current, String left, String right) {
        this.current = current;
        this.left = left;
        this.right = right;
    }

    public String direction(char c) {
        if(c == 'R')
            return right;
        if(c == 'L')
            return left;
        throw new RuntimeException("direction unknown "+c);
    }
}