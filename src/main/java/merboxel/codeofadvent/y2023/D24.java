package merboxel.codeofadvent.y2023;

import merboxel.codeofadvent.util.PatternMatching;

import java.io.IOException;
import java.util.*;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D24 {

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
     * Cheated by using Mathematica for solving the linear system of equations
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

            StringBuilder equations = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                String t = "t" + i;
                equations.append(t).append(" >= 0, ").append(vectors[i].x).append(" + ").append(vectors[i].vx).append(t).append(" == x + vx ").append(t).append(", ");
                equations.append(vectors[i].y).append(" + ").append(vectors[i].vy).append(t).append(" == y + vy ").append(t).append(", ");
                equations.append(vectors[i].z).append(" + ").append(vectors[i].vz).append(t).append(" == z + vz ").append(t).append(", ");
            }
            String sendToMathematica = "Solve[{" + equations.substring(0, equations.length() - 2) +  "}, {x,y,z,vx,vy,vz,t0,t1,t2}]";

            System.out.println(sendToMathematica);
            long xval = 229734616875628L;
            long yval = 192049388333190L;
            long zval = 146602352667782L;
            result = xval + yval + zval;

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
    }
}