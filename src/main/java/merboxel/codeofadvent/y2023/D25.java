package merboxel.codeofadvent.y2023;

import merboxel.codeofadvent.util.PatternMatching;

import java.io.IOException;
import java.util.*;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D25 {

    public static void main(String[] args) throws IOException {
        P1.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2023, 25);
    }

    static class P1 {

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 1 ---------------");
            Scanner sc = readFile();

            Map<String,Node> map = new HashMap<>();

            while(sc.hasNext()) {
                String[] words = PatternMatching.getWordsAsArray(sc.nextLine());

                String id = words[0].substring(0,3);

                Node node = (map.containsKey(id)) ? map.get(id) : new Node(id);
                for(int i = 1; i < words.length; i++) {
                    Node neighbour = (map.containsKey(words[i])) ? map.get(words[i]) : new Node(words[i]);
                    node.edges.add(neighbour);
                    neighbour.edges.add(node);

                    map.put(neighbour.id,neighbour);
                }
                map.put(node.id,node);
            }

            Map<Node,Integer> maxDistance = new HashMap<>();

            for(Node node: map.values()) {

                Set<Node> visited = new HashSet<>();
                PriorityQueue<PriorityNode> queue = new PriorityQueue<>();

                queue.add(new PriorityNode(0,node));

                while(!queue.isEmpty()) {
                    PriorityNode cur = queue.poll();
                    if(visited.contains(cur.node))
                        continue;
                    visited.add(cur.node);
                    maxDistance.put(node,cur.value);
                    for(Node neighbour: cur.node.edges)
                        queue.add(new PriorityNode(cur.value+1,neighbour));
                }
            }


            // 6 nodes with most 8 maxDistances
            // BBG - HTB - HTJ - PCC - DLK - PJJ
            maxDistance.forEach((key,value) -> { System.out.println(key.id + " " + value); });

            List<Node> nodes = new ArrayList<>(map.values().stream().toList());

            Set<Node> part1 = new HashSet<>();
            Set<Node> part1Neighbours = new HashSet<>();

            Set<Node> part2 = new HashSet<>();
            Set<Node> part2Neighbours = new HashSet<>();

            Stack<Node> stack = new Stack<>();
            stack.add(nodes.get(0));

            while(!stack.isEmpty()) {
                Node node = stack.pop();
                if(maxDistance.get(node) == 8 || part1.contains(node))
                    continue;
                part1.add(node);
                part1Neighbours.addAll(node.edges);
                stack.addAll(node.edges);
            }

            stack.add(nodes.stream().filter((elem) -> !part1.contains(elem)).toList().get(0));

            while(!stack.isEmpty()) {
                Node node = stack.pop();
                if(maxDistance.get(node) == 8 || part2.contains(node))
                    continue;
                part2.add(node);
                part2Neighbours.addAll(node.edges);
                stack.addAll(node.edges);
            }

            // Set contains all nodes but the 6 with maxDistance 8. We add on both sides 3 of them and we find our answer.
            part1Neighbours.removeAll(part1);
            part2Neighbours.removeAll(part2);

            nodes.removeAll(part1);
            nodes.removeAll(part2);

            // Nodes to cut
            // BBG - HTB
            // HTJ - PCC
            // DLK - PJJ
            System.out.println( (part1.size() + 3) * (part2.size() + 3));
            System.out.println("--------------------------------------");
        }

        static class PriorityNode implements Comparable<PriorityNode> {
            int value;
            Node node;

            public PriorityNode(int value, Node node) {
                this.value = value;
                this.node = node;
            }


            @Override
            public int compareTo(PriorityNode that) {
                return Integer.compare(this.value,that.value);
            }
        }

        static class Node {
            String id;
            Set<Node> edges = new HashSet<>();

            public Node(String id) {
                this.id = id;
            }

            @Override
            public int hashCode() {
                return id.hashCode();
            }

            @Override
            public boolean equals(Object _that) {
                if(_that instanceof Node that) {
                    return Objects.equals(this.id, that.id);
                }
                return false;
            }
        }
    }
}