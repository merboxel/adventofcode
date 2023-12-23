package merboxel.codeofadvent.y2023;

import merboxel.codeofadvent.structure.Cube;
import merboxel.codeofadvent.structure.Point;
import merboxel.codeofadvent.util.PatternMatching;

import java.io.IOException;
import java.sql.Array;
import java.util.*;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D23 {

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2023, 23);
    }

    static class P1 {

        public static void main(String[] args) throws IOException {
            run();
        }

        /**
         * Observation:
         * Every split has 3 points to stop going back
         */
        public static void run() throws IOException {
            System.out.println("--------------- Part 1 ---------------");
            long result = 0;
            Scanner sc = readFile();

            List<String> lines = new ArrayList<>();

            while(sc.hasNext()) {
                lines.add(sc.nextLine());
            }

            char[][] grid = lines.stream().map(String::toCharArray).toArray(char[][]::new);

            _Point start = new _Point(1,0,0,0,0);
            Queue<_Point> queue = new PriorityQueue<>();
            queue.add(start);

            while(!queue.isEmpty()) {
                _Point p = queue.poll();
                result = p.z;

                if(validPath(grid,p,new int[] {-1,0},'<')) {
                    queue.add(new _Point(p.x-1,p.y,p.z+1,p.x,p.y));
                }
                if(validPath(grid,p,new int[] {1,0},'>')) {
                    queue.add(new _Point(p.x+1,p.y,p.z+1,p.x,p.y));
                }
                if(validPath(grid,p,new int[] {0,-1},'^')) {
                    queue.add(new _Point(p.x,p.y-1,p.z+1,p.x,p.y));
                }
                if(validPath(grid,p,new int[] {0,1},'v')) {
                    queue.add(new _Point(p.x,p.y+1,p.z+1,p.x,p.y));
                }
            }

            System.out.println(result);
            System.out.println("--------------------------------------");
        }

        public static boolean validPath(char[][] grid, _Point p1, int[] dir, char c) {
            try {
                if(
                        (grid[(int)p1.y+dir[1]][(int)p1.x+dir[0]] == '.' ||  grid[(int)p1.y+dir[1]][(int)p1.x+dir[0]] == c)
                        &&  !(p1.x+dir[0] == p1.prevX && p1.y+dir[1] == p1.prevY)
                )
                    return true;
            } catch (ArrayIndexOutOfBoundsException ignored) { }
            return false;
        }

        static class _Point extends Point implements Comparable<_Point> {

            public long prevX;
            public long prevY;

            public _Point(long x,long y,long z,long prevX, long prevY) {
                super(x,y,z);
                this.prevX = prevX;
                this.prevY = prevY;
            }

            @Override
            public int compareTo(_Point that) {
                return Long.compare(this.z,that.z);
            }
        }
    }

    static class P2 {

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 2 ---------------");
            long result;
            Scanner sc = readFile();

            List<String> lines = new ArrayList<>();

            while(sc.hasNext()) {
                lines.add(sc.nextLine());
            }

            char[][] grid = lines.stream().map(String::toCharArray).toArray(char[][]::new);
            Queue<_Point> queue = new PriorityQueue<>();
            Node[][] nodes = new Node[grid.length][grid[0].length];

            Node startNode = new Node();
            Node endNode = new Node();
            nodes[1][0] = startNode;
            nodes[grid.length-1][grid[0].length-2] = endNode;

            _Point start = new _Point(1,0,0,0,0,startNode,1);
            queue.add(start);

            boolean[][] walkWay = new boolean[grid.length][grid[0].length];

            while(!queue.isEmpty()) {
                _Point p = queue.poll();

                //End node
                if(p.y == grid.length - 1) {
                    endNode.edges.add(new Edge(p.prevNode, (int)p.z));
                    p.prevNode.edges.add(new Edge(endNode, (int)p.z));
                    continue;
                }

                //Create node and edge
                if(p.special == 2) {
                    Node cur = nodes[(int)p.y][(int)p.x];
                    if(null == cur) {
                        cur = new Node();
                        nodes[(int)p.y][(int)p.x] = cur;
                    }
                    cur.edges.add(new Edge(p.prevNode,(int)p.z));
                    p.prevNode.edges.add(new Edge(cur,(int)p.z));
                    p.prevNode = cur;
                    p.special = 0;
                    p.z = 0;
                }

                if(walkWay[(int)p.y][(int)p.x]) {
                    continue;
                } else {
                    walkWay[(int)p.y][(int)p.x] = true;
                }

                boolean nextNode = grid[(int) p.y][(int) p.x] != '.';

                if(validPath(grid,p,new int[] {-1,0},'<')) {
                    queue.add(new _Point(p.x-1,p.y,p.z+1,p.x,p.y,p.prevNode,p.special + (nextNode?1:0)));
                }
                if(validPath(grid,p,new int[] {1,0},'>')) {
                    queue.add(new _Point(p.x+1,p.y,p.z+1,p.x,p.y,p.prevNode,p.special + (nextNode?1:0)));
                }
                if(validPath(grid,p,new int[] {0,-1},'^')) {
                    queue.add(new _Point(p.x,p.y-1,p.z+1,p.x,p.y,p.prevNode,p.special + (nextNode?1:0)));
                }
                if(validPath(grid,p,new int[] {0,1},'v')) {
                    queue.add(new _Point(p.x,p.y+1,p.z+1,p.x,p.y,p.prevNode,p.special + (nextNode?1:0)));
                }
            }

            // Calc the longest path from start to endPoint
            result = walkPath(startNode,0,endNode);

            System.out.println(result);
            System.out.println("--------------------------------------");
        }

        public static int walkPath(Node cur, int total, Node end) {
            if(cur == end)
                return total;
            if(cur.visited)
                return 0;
            cur.visited = true;
            int tmp = 0;
            for(Edge next: cur.edges) {
                tmp = Math.max(tmp,walkPath(next.node,total + next.value,end));
            }
            cur.visited = false;
            return tmp;
        }

        public static boolean validPath(char[][] grid, _Point p1, int[] dir, char c) {
            try {
                if((grid[(int)p1.y+dir[1]][(int)p1.x+dir[0]] == '.' ||  grid[(int)p1.y+dir[1]][(int)p1.x+dir[0]] == c)
                        &&  !(p1.x+dir[0] == p1.prevX && p1.y+dir[1] == p1.prevY)
                )
                    return true;
            } catch (ArrayIndexOutOfBoundsException ignored) { }
            return false;
        }

        static class Edge {
            int value;
            Node node;
            public Edge(Node node, int value) {
                this.node = node;
                this.value = value;
            }
        }

        static class Node {
            boolean visited = false;
            List<Edge> edges = new ArrayList<>();
        }

        static class _Point extends Point implements Comparable<_Point>{

            public long prevX;
            public long prevY;
            public Node prevNode;
            public int special;

            public _Point(long x,long y,long z,long prevX, long prevY, Node prevNode, int special) {
                super(x,y,z);
                this.prevX = prevX;
                this.prevY = prevY;
                this.prevNode = prevNode;
                this.special = special;
            }

            @Override
            public int compareTo(_Point that) {
                return Long.compare(this.z,that.z);
            }
        }
    }
}