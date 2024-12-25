package merboxel.codeofadvent.y2024.D23;

import merboxel.codeofadvent.AoC;
import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class D23P1 extends AoC {

    private static final int year = 2024;
    private static final int day = 23;
    private static final int part = 1;

    public D23P1(Scanner sc) {
        super(year, day, part, sc);
    }

    public D23P1() throws IOException {
        super(year, day, part);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("--------------- Part 1 ---------------");
        System.out.println(new D23P1().run());
        System.out.println("--------------------------------------");
    }

    public String run() {

        List<String> connections = ScannerUtil.toList(sc);
        Map<String, Node> computers = new HashMap<>();

        for (String connection : connections) {
            String[] pcs = connection.split("-");

            Node pc1 = computers.getOrDefault(pcs[0], new Node(pcs[0]));
            Node pc2 = computers.getOrDefault(pcs[1], new Node(pcs[1]));

            pc1.addEdge(pc2);
            pc2.addEdge(pc1);

            computers.put(pcs[0], pc1);
            computers.put(pcs[1], pc2);
        }

        SortedSet<String> cliques = new TreeSet<>();

        for (Node computer : computers.values()) {
            if (computer.startWithT()) {
                for (Node computer2 : computer.edges) {
                    Set<Node> edges = new HashSet<>(computer.edges);
                    edges.retainAll(computer2.edges);
                    for (Node computer3 : edges) {
                        cliques.add(Stream.of(computer.getName(), computer2.getName(), computer3.getName()).sorted(String::compareTo).collect(Collectors.joining(",")));
                    }
                }
            }
        }
        return Integer.toString(cliques.size());
    }

    private static class Node implements Comparable<Node> {
        String name;
        Set<Node> edges;

        public Node(String name) {
            this.name = name;
            this.edges = new HashSet<>();
        }

        public void addEdge(Node n) {
            edges.add(n);
        }

        public boolean startWithT() {
            return (name.charAt(0) == 't');
        }

        public String getName() {
            return name;
        }

        @Override
        public int compareTo(Node that) {
            return this.name.compareTo(that.name);
        }
    }
}
