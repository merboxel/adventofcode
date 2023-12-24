package merboxel.codeofadvent.y2023;

import merboxel.codeofadvent.util.PatternMatching;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D24_2 {

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2023, 24);
    }

    static class P1 {

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 1 ---------------");
            long result = 0;
            Scanner sc = readFile();

            List<Vector> _vectors = new ArrayList<>();

            while(sc.hasNext()) {
                long[] numbers = PatternMatching.getLongsWithNegativesAsArray(sc.nextLine());
                _vectors.add(new Vector(numbers));
            }

            Vector[] vectors = _vectors.toArray(new Vector[0]);

            double min = 200000000000000.0d;
            double max = 400000000000000.0d;

            for(int i = 0; i < vectors.length; i++) {
                for(int j = i+1; j < vectors.length; j++) {
                    Vector v1 = vectors[i];
                    Vector v2 = vectors[j];
                    double denominator = v1.denominator(v2);
                    if(denominator != 0) {
                        double[] intersection = v1.intersectionTime(v2);
                        if(intersection[0] > 0 && intersection[1] < 0) {
                            double x = v1.getPositionX(intersection[0]);
                            double y = v1.getPositionY(intersection[0]);
                            if (min <= x && x <= max && min <= y && y <= max)
                                result++;
                        }
                    }
                }
            }
            System.out.println(result);
            System.out.println("--------------------------------------");
        }

        static class Vector {
            long x, y, z;
            long vx,vy,vz;
            public Vector(long[] numbers) {
                this.x = numbers[0];
                this.y = numbers[1];
                this.z = numbers[2];
                this.vx = numbers[3];
                this.vy = numbers[4];
                this.vz = numbers[5];
            }

            public double denominator(Vector v2) {

                return (this.vx * v2.vy - this.vy * v2.vx) ;
            }

            public double[] intersectionTime(Vector v2) {

                double denominator = denominator(v2);

                double s = (((v2.x - this.x) * v2.vy) - ((v2.y - this.y) * v2.vx)) / denominator;
                double t = (((this.x - v2.x) * this.vy) - ((this.y - v2.y) * this.vx)) / denominator;

                return new double[] {s, t};
            }

            public double getPositionX(double time) {
                return x + time*vx;
            }

            public double getPositionY(double time) {
                return y + time*vy;
            }
        }
    }

    /**
     * Linear equation inspiration received from colleague, he couldn't finish the equation due to time constraints '<a href="https://github.com/ferencballa/aoc/blob/main/src/year2023/code/Question24.java">code</a>'.
     */
    static class P2 {

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 2 ---------------");
            long result;
            Scanner sc = readFile();

            List<Vector> _vectors = new ArrayList<>();

            while(sc.hasNext()) {
                long[] numbers = PatternMatching.getLongsWithNegativesAsArray(sc.nextLine());
                _vectors.add(new Vector(numbers));
            }

            Vector[] vectors = _vectors.toArray(new Vector[0]);
            //check same x velocity
            ArrayList<Set<Double>> possibleXRockSpeeds = new ArrayList<>();
            for (int i = 0; i < vectors.length; i++) {
                for (int j = i + 1; j < vectors.length; j++) {
                    if (vectors[i].vx == vectors[j].vx) {
                        Set<Double> possibleSpeeds = new HashSet<>();
                        for (int s = -1000; s < 1000; s++) {
                            if ((s - vectors[i].vx) != 0 && (vectors[i].x - vectors[j].x) % (s - vectors[i].vx) == 0) {
                                possibleSpeeds.add((double) s);
                            }
                        }
                        possibleXRockSpeeds.add(possibleSpeeds);
                    }
                }
            }
            Set<Double> xSpeedSet = possibleXRockSpeeds.get(0);
            for (int i = 1; i < possibleXRockSpeeds.size(); i++) {
                xSpeedSet.retainAll(possibleXRockSpeeds.get(i));
            }
            double vx = (double) xSpeedSet.toArray()[0];
            //check same y velocity
            ArrayList<Set<Double>> possibleYRockSpeeds = new ArrayList<>();
            for (int i = 0; i < vectors.length; i++) {
                for (int j = i + 1; j < vectors.length; j++) {
                    if (vectors[i].vy == vectors[j].vy) {
                        Set<Double> possibleSpeeds = new HashSet<>();
                        for (int s = -1000; s < 1000; s++) {
                            if ((s - vectors[i].vy) != 0 && (vectors[i].y - vectors[j].y) % (s - vectors[i].vy) == 0) {
                                possibleSpeeds.add((double) s);
                            }
                        }
                        possibleYRockSpeeds.add(possibleSpeeds);
                    }
                }
            }
            Set<Double> ySpeedSet = possibleYRockSpeeds.get(0);
            for (int i = 1; i < possibleYRockSpeeds.size(); i++) {
                ySpeedSet.retainAll(possibleYRockSpeeds.get(i));
            }
            double vy = (double) ySpeedSet.toArray()[0];
            //check same z velocity
            ArrayList<Set<Double>> possibleZRockSpeeds = new ArrayList<>();
            for (int i = 0; i < vectors.length; i++) {
                for (int j = i + 1; j < vectors.length; j++) {
                    if (vectors[i].vz == vectors[j].vz) {
                        Set<Double> possibleSpeeds = new HashSet<>();
                        for (int s = -1000; s < 1000; s++) {
                            if ((s - vectors[i].vz) != 0 && (vectors[i].z - vectors[j].z) % (s - vectors[i].vz) == 0) {
                                possibleSpeeds.add((double) s);
                            }
                        }
                        possibleZRockSpeeds.add(possibleSpeeds);
                    }
                }
            }
            Set<Double> zSpeedSet = possibleZRockSpeeds.get(0);
            for (int i = 1; i < possibleZRockSpeeds.size(); i++) {
                zSpeedSet.retainAll(possibleZRockSpeeds.get(i));
            }
            double vz = (double) zSpeedSet.toArray()[0];

            Vector v1 = vectors[0];
            Vector v2 = vectors[1];

            // Linear calculations provide the following two equations for t1 and t2 after direction vector has been found
            // Only two vectors are needed for the full equation and finding the starting positions.
            // Only 1 time stamp is needed to find the answer but to verify we can use two
            double t2 = (((v2.x-v1.x) * (v1.vy - vy)) - ((v2.y-v1.y) * (v1.vx - vx))) / (((v1.vx - vx) * (v2.vy - vy)) - ((v1.vy - vy) * (v2.vx - vx)));
            double t1 = ((v2.x - v1.x) + (v2.vx-vx) * t2) / (v1.vx - vx);

            double x1 = v1.x + v1.vx * t1 - vx*t1;
            double y1 = v1.y + v1.vy * t1 - vy*t1;
            double z1 = v1.z + v1.vz * t1 - vz*t1;

            double x2 = v2.x + v2.vx * t2 - vx*t2;
            double y2 = v2.y + v2.vy * t2 - vy*t2;
            double z2 = v2.z + v2.vz * t2 - vz*t2;

            assert(x1 == x2);
            assert(y1 == y2);
            assert(z1 == z2);

            System.out.println(new BigDecimal(x1+y1+z1).toPlainString());
            System.out.println("--------------------------------------");
        }

        static class Vector {
            long x, y, z;
            long vx, vy, vz;

            public Vector(long[] numbers) {
                this.x = numbers[0];
                this.y = numbers[1];
                this.z = numbers[2];
                this.vx = numbers[3];
                this.vy = numbers[4];
                this.vz = numbers[5];
            }
        }
    }
}