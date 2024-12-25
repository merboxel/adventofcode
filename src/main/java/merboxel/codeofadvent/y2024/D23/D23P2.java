package merboxel.codeofadvent.y2024.D23;

import merboxel.codeofadvent.AoC;
import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class D23P2 extends AoC {

    private static final int year = 2024;
    private static final int day = 23;
    private static final int part = 2;

    public D23P2(Scanner sc) {
        super(year, day, part, sc);
    }

    public D23P2() throws IOException {
        super(year, day, part);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("--------------- Part 2 ---------------");
        System.out.println(new D23P2().run());
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

        Set<Node> dfs = dfs(new HashSet<>(computers.values()), new HashSet<>());

        return dfs.stream().sorted(Node::compareTo).map(Node::getName).collect(Collectors.joining(","));
    }

    private Set<Node> dfs(Set<Node> edges, Set<Node> visited) {

        Set<Node> result = visited;
        Set<Node> seen = new HashSet<>();

        for (Node computer : edges) {
            Set<Node> copyEdges = new HashSet<>(edges);
            copyEdges.removeAll(seen);
            Set<Node> copyVisited = new HashSet<>(visited);
            copyVisited.add(computer);
            copyEdges.retainAll(computer.edges);
            Set<Node> dfs = dfs(copyEdges, copyVisited);
            if (result.size() < dfs.size()) {
                result = dfs;
            }
            seen.add(computer);
        }

        return result;
    }

    private static class Node implements Comparable<Node> {
        final String name;
        final Set<Node> edges;

        public Node(String name) {
            this.name = name;
            this.edges = new HashSet<>();
        }

        public void addEdge(Node n) {
            edges.add(n);
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
