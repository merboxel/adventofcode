package merboxel.codeofadvent.y2025.D8;


import merboxel.codeofadvent.AoC;
import merboxel.codeofadvent.structure.Point3D;
import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.*;

public class D8P1 extends AoC {

    private static final int year = 2025;
    private static final int day = 8;
    private static final int part = 1;

    public D8P1(Scanner sc) throws IOException {
        super(year, day, part, sc);
    }

    public D8P1() throws IOException {
        super(year, day, part);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("--------------- Part 1 ---------------");
        System.out.println(new D8P1().run());
        System.out.println("--------------------------------------");
    }

    public String run() throws IOException {

        List<String> input = ScannerUtil.toList(sc);

        Point[] points = input.stream().map(line -> line.split(","))
                .map(split -> Arrays.stream(split).mapToLong(Long::parseLong).toArray())
                .map(split -> new Point(split[0], split[1], split[2]))
                .toArray(Point[]::new);

        PriorityQueue<Distance> priorityQueue = new PriorityQueue<>();

        for(int i = 0; i < points.length; i++){
            Point point1 = points[i];
            for(int j = i+1; j < points.length; j++){
                Point point2 = points[j];
                double distance = Math.sqrt(
                        (point1.x - point2.x) * (point1.x - point2.x) +
                                (point1.y - point2.y) * (point1.y - point2.y) +
                                (point1.z - point2.z) * (point1.z - point2.z)
                );
                priorityQueue.add(new Distance(distance,point1, point2));
            }
        }

        for(int i = 0; i < 1000; i++){
            Distance dist = priorityQueue.poll();
            assert dist != null;
            dist.point1.addEdge(dist.point2);
            dist.point2.addEdge(dist.point1);
        }

        Set<Set<Point>> junctions = new HashSet<>();

        for(Point point : points){
            junctions.add(point.getNeighbors());
        }

        return junctions.stream()
                .map(Set::size)
                .sorted((o1, o2) -> -1*Integer.compare(o1, o2))
                .limit(3)
                .reduce(1, (a,b) -> a*b)
                .toString();
    }


    static class Point extends Point3D {

        private Set<Point> edges = new HashSet<>();
        boolean visited;

        public Point(long x, long y, long z) {
            super(x, y, z);
            this.visited = false;
        }

        public void addEdge(Point other) {
            edges.add(other);
        }

        public Set<Point> getNeighbors() {

            if(visited) {
                return new HashSet<>();
            }
            visited = true;

            Set<Point> neighbors = new HashSet<>(edges);

            for(Point p : edges) {
                neighbors.addAll(p.getNeighbors());
            }

            return neighbors;
        }
    }

    record Distance(double distance, Point point1, Point point2) implements Comparable<Distance> {
        @Override
        public int compareTo(Distance o) {
            if(this.distance > o.distance) {
                return 1;
            } else if(this.distance < o.distance) {
                return -1;
            }
            return 0;
        }
    }
}