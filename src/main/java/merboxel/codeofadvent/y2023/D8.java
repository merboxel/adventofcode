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
        long result = 0;
        Scanner sc = D8.readFile();

        Map<String,Node> tree = new HashMap<>();

        char[] pattern = sc.nextLine().toCharArray();
        sc.nextLine();


        Node[] startWalk = new Node[26];
        Node[] endWalk = new Node[26];

        int indexStart = 0;
        int indexEnd = 0;

        while(sc.hasNext()) {

            String[] words = PatternMatching.getWordsAsArray(sc.nextLine());

            Node newNode = new Node(words[0],words[2].substring(1,4),words[3].substring(0,3));
            if(newNode.current.endsWith("A")) {
                startWalk[indexStart] = newNode;
                indexStart++;
            }
            if(newNode.current.endsWith("Z")) {
                endWalk[indexEnd] = newNode;
                indexEnd++;
            }

            tree.put(words[0],newNode);
        }


        //Find cycles
        List<Long> cycles = new ArrayList<>();

        long cycle;
        for(int entry = 0; entry <indexStart; entry++) {
            cycle = 0;
            do {
                char direction = pattern[(int) (cycle % pattern.length)];
                startWalk[entry] = tree.get(startWalk[entry].direction(direction));
                startWalk[entry].path++;
                cycle++;
            } while (!startWalk[entry].current.endsWith("Z"));

            long inter1 = cycle;

            do {
                char direction = pattern[(int) (cycle % pattern.length)];
                startWalk[entry] = tree.get(startWalk[entry].direction(direction));
                startWalk[entry].path++;
                cycle++;
            } while (!startWalk[entry].current.endsWith("Z"));
            long inter2 = cycle;
            cycles.add(inter2-inter1);
        }
        long total = 1L;
        for(Long i: cycles)
            total *= i;
        System.out.println(total);

        Long[] arrCycles = cycles.toArray(new Long[0]);

        next : for(int i = 0; ; i++) {
            result += arrCycles[0];
            for(Long j: arrCycles) {
                if(result % j != 0)
                    continue next;
            }
            break;
        }

        System.out.println(result);
        System.out.println("--------------------------------------");
    }
}

class Node {
    String current;
    String left;
    String right;
    int path = 0;

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