package merboxel.codeofadvent.y2023;

import merboxel.codeofadvent.structure.Cube;
import merboxel.codeofadvent.structure.Point;
import merboxel.codeofadvent.util.PatternMatching;

import java.io.IOException;
import java.util.*;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D22 {

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2023, 22);
    }

    static class P1 {

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 1 ---------------");
            long result = 0;
            Scanner sc = readFile();

            PriorityQueue<_Cube> queue = new PriorityQueue<>();
            List<_Cube> allCubes = new ArrayList<>();

            int maxX=0;
            int maxY=0;
            int maxZ=0;

            int number = 1;

            while(sc.hasNext()) {
                int[] points = PatternMatching.getIntegersAsArray(sc.nextLine());
                _Cube cube = new _Cube(number, new Point(points[0],points[1],points[2]),new Point(points[3],points[4],points[5]));
                queue.add(cube);
                allCubes.add(cube);

                maxX = Math.max(maxX,points[3]+1);
                maxY = Math.max(maxY,points[4]+1);
                maxZ = Math.max(maxZ,points[5]+1);
                number ++;
            }

            _Cube[][][] stack = new _Cube[maxZ][maxY][maxX];

            next: while(!queue.isEmpty()) {
                _Cube cube = queue.poll();
                for(int z = (int)cube.z; z > 0;z--) {
                    for(int x = (int)cube.x; x <= (int)cube.x+cube.lengthX; x++) {
                        for (int y = (int)cube.y; y <= (int)cube.y+cube.lengthY; y++) {
                            _Cube that = stack[z-1][y][x];
                            if(null != that) {
                                cube.addBot(that);
                                that.addTop(cube);
                                if(!cube.placed) {
                                    cube.placed = true;
                                    for(int _x = (int)cube.x; _x <= (int)cube.x+cube.lengthX; _x++) {
                                        for (int _y = (int)cube.y; _y <= (int)cube.y+cube.lengthY; _y++) {
                                            for(int _z = z; _z <= z+cube.lengthZ; _z++)
                                                stack[_z][_y][_x] = cube;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if(cube.placed)
                        continue next;
                }
                cube.placed = true;
                for(int _x = (int)cube.x; _x <= (int)cube.x+cube.lengthX; _x++) {
                    for (int _y = (int)cube.y; _y <= (int)cube.y+cube.lengthY; _y++) {
                        for(int _z = 1; _z <= 1+cube.lengthZ; _z++)
                            stack[_z][_y][_x] = cube;
                    }
                }
            }

            next: for(_Cube cube : allCubes) {
                for(_Cube top: cube.top) {
                    if(top.bot.size() == 1)
                        continue next;
                }
                result ++;
            }

            System.out.println(result);
            System.out.println("--------------------------------------");
        }

        static class _Cube extends Cube implements Comparable<_Cube> {

            int number;
            public Set<_Cube> top = new HashSet<>();
            public Set<_Cube> bot = new HashSet<>();
            public boolean placed = false;

            public _Cube(int nb, Point p1, Point p2) {
                super(p1,p2);
                this.number =nb;
            }

            public boolean validateHit(_Cube that) {
                boolean rangeX = this.x > that.x + that.lengthX && this.x + this.lengthX < that.x;
                boolean rangeY = this.y > that.y + that.lengthY && this.y + this.lengthY < that.y;
                return rangeX && rangeY;
            }

            public void addTop(_Cube that) {
                this.top.add(that);
            }
            public void addBot(_Cube that) {this.bot.add(that); }

            @Override
            public int compareTo(_Cube that) {
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
            long result = 0;
            Scanner sc = readFile();

            PriorityQueue<_Cube> queue = new PriorityQueue<>();
            List<_Cube> allCubes = new ArrayList<>();

            int maxX = 0;
            int maxY = 0;
            int maxZ = 0;

            int number = 1;

            while (sc.hasNext()) {
                int[] points = PatternMatching.getIntegersAsArray(sc.nextLine());
                _Cube cube = new _Cube(number, new Point(points[0], points[1], points[2]), new Point(points[3], points[4], points[5]));
                queue.add(cube);
                allCubes.add(cube);

                maxX = Math.max(maxX, points[3] + 1);
                maxY = Math.max(maxY, points[4] + 1);
                maxZ = Math.max(maxZ, points[5] + 1);
                number++;
            }

            _Cube[][][] stack = new _Cube[maxZ][maxY][maxX];

            next:
            while (!queue.isEmpty()) {
                _Cube cube = queue.poll();
                for (int z = (int) cube.z; z > 0; z--) {
                    for (int x = (int) cube.x; x <= (int) cube.x + cube.lengthX; x++) {
                        for (int y = (int) cube.y; y <= (int) cube.y + cube.lengthY; y++) {
                            _Cube that = stack[z - 1][y][x];
                            if (null != that) {
                                cube.addBot(that);
                                that.addTop(cube);
                                if (!cube.placed) {
                                    cube.placed = true;
                                    for (int _x = (int) cube.x; _x <= (int) cube.x + cube.lengthX; _x++) {
                                        for (int _y = (int) cube.y; _y <= (int) cube.y + cube.lengthY; _y++) {
                                            for (int _z = z; _z <= z + cube.lengthZ; _z++)
                                                stack[_z][_y][_x] = cube;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (cube.placed)
                        continue next;
                }
                cube.placed = true;
                for (int _x = (int) cube.x; _x <= (int) cube.x + cube.lengthX; _x++) {
                    for (int _y = (int) cube.y; _y <= (int) cube.y + cube.lengthY; _y++) {
                        for (int _z = 1; _z <= 1 + cube.lengthZ; _z++)
                            stack[_z][_y][_x] = cube;
                    }
                }
            }

            next:
            for (_Cube cube : allCubes) {
                Queue<_Cube> queueCubes = new LinkedList<>();
                Set<_Cube> fallingCubes = new HashSet<>();
                for(_Cube top: cube.top) {
                    if(top.bot.size() == 1) {
                        fallingCubes.add(top);
                        queueCubes.add(top);
                    }
                }

                while(!queueCubes.isEmpty()) {
                    result ++;
                    _Cube that = queueCubes.poll();
                    for(_Cube top: that.top) {
                        if(top.wouldFall(fallingCubes) && !fallingCubes.contains(top)) {
                            fallingCubes.add(top);
                            queueCubes.add(top);
                        }
                    }
                }
            }

            System.out.println(result);
            System.out.println("--------------------------------------");
        }

        static class _Cube extends Cube implements Comparable<_Cube> {

            int number;
            public Set<_Cube> top = new HashSet<>();
            public Set<_Cube> bot = new HashSet<>();
            public boolean placed = false;
            public long bricksOnTop = -1;

            public _Cube(int nb, Point p1, Point p2) {
                super(p1, p2);
                this.number = nb;
            }

            public boolean validateHit(_Cube that) {
                boolean rangeX = this.x > that.x + that.lengthX && this.x + this.lengthX < that.x;
                boolean rangeY = this.y > that.y + that.lengthY && this.y + this.lengthY < that.y;
                return rangeX && rangeY;
            }

            public void addTop(_Cube that) {
                this.top.add(that);
            }

            public void addBot(_Cube that) {
                this.bot.add(that);
            }

            public boolean wouldFall(Set<_Cube> that) {
                return that.containsAll(bot);
            }

            @Override
            public int compareTo(_Cube that) {
                return Long.compare(this.z, that.z);
            }
        }
    }
}