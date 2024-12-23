package merboxel.codeofadvent.y2024;


import merboxel.codeofadvent.structure.Point3D;
import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D8 {

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2024, 8);
    }

    static class P1 {
        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 1 ---------------");
            Scanner sc = readFile();

            long result = 0;

            char[][] grid = ScannerUtil.toList(sc).stream().map(String::toCharArray).toArray(char[][]::new);
            int[][] antiNode = new int[grid.length][grid[0].length];

            List<List<Point3D>>antennas = new ArrayList<>(255);
            for(int i = 0; i < 255; i++)
                antennas.add(new ArrayList<>());

            for(int i = 0; i < grid.length; i++){
                for(int j = 0; j < grid[0].length; j++) {
                    if(grid[i][j] != '.')
                        antennas.get(grid[i][j]).add(new Point3D(i,j));
                }
            }

            for(List<Point3D> charAntannas: antennas) {
                if(charAntannas.isEmpty())
                    continue;

                for(Point3D antannaThis: charAntannas) {
                    for(Point3D antannaThat: charAntannas) {
                        if(antannaThis.equals(antannaThat))
                            continue;
                        Point3D dir = antannaThis.distanceVector(antannaThat);
                        Point3D antiLoc = antannaThis.addVector(dir);
                        if(antiLoc.x < 0 || antiLoc.x >= grid.length | antiLoc.y < 0 || antiLoc.y >= grid[0].length)
                            continue;
                        antiNode[(int) antiLoc.x][(int) antiLoc.y] ++;
                    }
                }
            }

            for(int[] line : antiNode) {
                for (int location : line) {
                    if (location > 0) {
                        result++;
                    }
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
            Scanner sc = readFile();

            long result = 0;

            char[][] grid = ScannerUtil.toList(sc).stream().map(String::toCharArray).toArray(char[][]::new);
            int[][] antiNode = new int[grid.length][grid[0].length];

            List<List<Point3D>>antennas = new ArrayList<>(255);
            for(int i = 0; i < 255; i++)
                antennas.add(new ArrayList<>());

            for(int i = 0; i < grid.length; i++){
                for(int j = 0; j < grid[0].length; j++) {
                    if(grid[i][j] != '.') {
                        antennas.get(grid[i][j]).add(new Point3D(i, j));
                        antiNode[i][j] ++;
                    }
                }
            }

            for(List<Point3D> charAntannas: antennas) {
                if(charAntannas.isEmpty())
                    continue;

                for(Point3D antannaThis: charAntannas) {
                    for(Point3D antannaThat: charAntannas) {
                        if(antannaThis.equals(antannaThat))
                            continue;
                        Point3D dir = antannaThis.distanceVector(antannaThat);
                        Point3D antiLoc = antannaThis.addVector(dir);
                        while (antiLoc.x >= 0 && !(antiLoc.x >= grid.length | antiLoc.y < 0) && antiLoc.y < grid[0].length) {
                            antiNode[(int) antiLoc.x][(int) antiLoc.y]++;
                            antiLoc = antiLoc.addVector(dir);
                        }
                    }
                }
            }

            for(int[] line : antiNode) {
                for (int location : line) {
                    if (location > 0) {
                        result++;
                    }
                }
            }
            System.out.println(result);
            System.out.println("--------------------------------------");
        }
    }
}