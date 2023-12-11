package merboxel.codeofadvent.y2023;

import java.io.IOException;
import java.util.*;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D11 {

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2023, 11);
    }

    static class P1 {

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 1 ---------------");
            long result = 0L;
            Scanner sc = readFile();

            List<String> lines = new ArrayList<>();

            while(sc.hasNext()) {
                lines.add(sc.nextLine());
            }

            char[][] universe = lines.stream().map(String::toCharArray).toArray(char[][]::new);
            List<Integer> _galaxies = new ArrayList<>();
            Set<Integer> infiX = new HashSet<>();
            Set<Integer> infiY = new HashSet<>();

            for(int y = 0; y < universe.length; y++) {
                boolean isVoid = true;
                for (int x = 0; x < universe[y].length; x++) {
                    if (universe[y][x] == '#') {
                        _galaxies.add(x);
                        _galaxies.add(y);
                        isVoid = false;
                    }
                }
                if(isVoid)
                    infiY.add(y);
            }
            Integer[] galaxies = _galaxies.toArray(new Integer[0]);

            for(int x = 0; x < universe[0].length; x++) {
                boolean isVoid = true;
                for(int y = 0; y < universe.length; y++) {
                    if (universe[y][x] == '#') {
                        isVoid = false;
                        break;
                    }
                }
                if(isVoid)
                    infiX.add(x);
            }

            for(int i = 0; i+1 < galaxies.length; i += 2) {
                for(int j = i+2; j+1 < galaxies.length; j += 2) {
                    int minX = Math.min(galaxies[i],galaxies[j]);
                    int maxX = Math.max(galaxies[i],galaxies[j]);
                    int minY = Math.min(galaxies[i+1],galaxies[j+1]);
                    int maxY = Math.max(galaxies[i+1],galaxies[j+1]);
                    int voids = 0;
                    for(int x = minX + 1; x < maxX; x++)
                        voids += (infiX.contains(x))? 1:0;
                    for(int y = minY + 1; y < maxY; y++)
                        voids += (infiY.contains(y))? 1:0;
                    result += maxX-minX + maxY-minY + voids;
//                    System.out.println("---");
//                    System.out.println("Galaxies: "+(i/2+1)+","+(j/2+1));
//                    System.out.println("Galaxies coord: "+galaxies[i]+","+galaxies[i+1]+" "+galaxies[j]+","+galaxies[j+1]);
//                    System.out.println("Galaxies voids: "+voids);
//                    System.out.println("Galaxies dist: "+(maxX-minX + maxY-minY + voids));
//                    System.out.println("---");
                }
            }

            System.out.println(result);
            System.out.println("--------------------------------------");
        }
    }

    static class P2 {

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 2 ---------------");
            long result = 0L;
            Scanner sc = readFile();

            List<String> lines = new ArrayList<>();

            while(sc.hasNext()) {
                lines.add(sc.nextLine());
            }

            char[][] universe = lines.stream().map(String::toCharArray).toArray(char[][]::new);
            List<Integer> _galaxies = new ArrayList<>();
            Set<Integer> infiX = new HashSet<>();
            Set<Integer> infiY = new HashSet<>();

            for(int y = 0; y < universe.length; y++) {
                boolean isVoid = true;
                for (int x = 0; x < universe[y].length; x++) {
                    if (universe[y][x] == '#') {
                        _galaxies.add(x);
                        _galaxies.add(y);
                        isVoid = false;
                    }
                }
                if(isVoid)
                    infiY.add(y);
            }
            Integer[] galaxies = _galaxies.toArray(new Integer[0]);

            for(int x = 0; x < universe[0].length; x++) {
                boolean isVoid = true;
                for(int y = 0; y < universe.length; y++) {
                    if (universe[y][x] == '#') {
                        isVoid = false;
                        break;
                    }
                }
                if(isVoid)
                    infiX.add(x);
            }

            for(int i = 0; i+1 < galaxies.length; i += 2) {
                for(int j = i+2; j+1 < galaxies.length; j += 2) {
                    int minX = Math.min(galaxies[i],galaxies[j]);
                    int maxX = Math.max(galaxies[i],galaxies[j]);
                    int minY = Math.min(galaxies[i+1],galaxies[j+1]);
                    int maxY = Math.max(galaxies[i+1],galaxies[j+1]);
                    int voids = 0;
                    for(int x = minX + 1; x < maxX; x++)
                        voids += (infiX.contains(x))? 1:0;
                    for(int y = minY + 1; y < maxY; y++)
                        voids += (infiY.contains(y))? 1:0;
                    result += maxX-minX + maxY-minY + voids*999999L;
//                    System.out.println("---");
//                    System.out.println("Galaxies: "+(i/2+1)+","+(j/2+1));
//                    System.out.println("Galaxies coord: "+galaxies[i]+","+galaxies[i+1]+" "+galaxies[j]+","+galaxies[j+1]);
//                    System.out.println("Galaxies voids: "+voids);
//                    System.out.println("Galaxies dist: "+(maxX-minX + maxY-minY + voids*999999L));
//                    System.out.println("---");
                }
            }

            System.out.println(result);
            System.out.println("--------------------------------------");
        }
    }
}