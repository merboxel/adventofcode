package merboxel.codeofadvent.y2023;


import java.io.IOException;
import java.util.*;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D18 {

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2023, 18);
    }

    static class P1 {

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 1 ---------------");
            long result = 0L;
            Scanner sc = readFile();

            List<String[]> lines = new ArrayList<>();

            while (sc.hasNext()) {
                lines.add(sc.nextLine().split(" "));
            }
            int[] pos = {0,0};
            int[] minPos = {0,0};
            int[] maxPos = {0,0};

            for(String[] line: lines) {
                switch(line[0]) {
                    case "R" -> {
                        pos[0] += Integer.parseInt(line[1]);
                        maxPos[0] = Math.max(maxPos[0],pos[0]);
                    }
                    case "L" -> {
                        pos[0] -= Integer.parseInt(line[1]);
                        minPos[0] = Math.min(minPos[0],pos[0]);
                    }
                    case "D" -> {
                        pos[1] += Integer.parseInt(line[1]);
                        maxPos[1] = Math.max(maxPos[1],pos[1]);
                    }
                    case "U" -> {
                        pos[1] -= Integer.parseInt(line[1]);
                        minPos[1] = Math.min(minPos[1],pos[1]);
                    }
                }
            }

            int[][] grid = new int[maxPos[1]-minPos[1]+1][maxPos[0]-minPos[0]+1];
            pos = new int[] {-minPos[0],-minPos[1]};
            for(int[] line : grid)
                Arrays.fill(line, -1);

            char prev = 'D';
            int dir = 1; //outside

            for(String[] line: lines) {
                switch(line[0]) {
                    case "R" -> {
                        if(prev == 'D')
                            dir = Math.floorMod(dir + 1, 4);
                        else
                            dir = Math.floorMod(dir - 1, 4);
                        for(int i = 1; i < Integer.parseInt(line[1]); i++) {
                            grid[pos[1]][pos[0]+i] = dir;
                        }
                        prev = 'R';
                        pos[0] += Integer.parseInt(line[1]);
                    }
                    case "L" -> {
                        if(prev == 'D')
                            dir = Math.floorMod(dir - 1, 4);
                        else
                            dir = Math.floorMod(dir + 1, 4);
                        for(int i = 1; i < Integer.parseInt(line[1]); i++) {
                            grid[pos[1]][pos[0]-i] = dir;
                        }
                        prev = 'L';
                        pos[0] -= Integer.parseInt(line[1]);
                    }
                    case "D" -> {
                        if(prev == 'R')
                            dir = Math.floorMod(dir - 1, 4);
                        else
                            dir = Math.floorMod(dir + 1, 4);
                        for(int i = 0; i <= Integer.parseInt(line[1]); i++) {
                            grid[pos[1]+i][pos[0]] = dir;
                        }
                        prev = 'D';
                        pos[1] += Integer.parseInt(line[1]);
                    }
                    case "U" -> {
                        if(prev == 'R')
                            dir = Math.floorMod(dir + 1, 4);
                        else
                            dir = Math.floorMod(dir - 1, 4);
                        for(int i = 0; i <= Integer.parseInt(line[1]); i++) {
                            grid[pos[1]-i][pos[0]] = dir;
                        }
                        prev = 'U';
                        pos[1] -= Integer.parseInt(line[1]);
                    }
                }
            }
            int row = 0;
            for(int[] line: grid) {
                boolean inside =false;
                for(int i = 0; i < line.length; i++) {
                    if(line[i] == 3) {
                        inside = true;
                    }
                    if(line[i] == 1) {
                        inside = false;
                    }
                    if(line[i] > -1) {
                        result ++;
                    }
                    if(line[i] == -1 && inside) {
                        line[i] += 8;
                        result ++;
                    }
                }
                System.out.println("Row :"+row + ", result:"+result);
                row ++;
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

            List<String[]> lines = new ArrayList<>();

            while (sc.hasNext()) {
                lines.add(sc.nextLine().split(" "));
            }
            long[] pos = {0,0};
            long[] minPos = {0,0};
            long[] maxPos = {0,0};

            for(String[] line: lines) {
                switch(line[2].charAt(7)) {
                    case '0' -> {
                        pos[0] += Long.decode(line[2].substring(1,7));
                        maxPos[0] = Math.max(maxPos[0],pos[0]);
                    }
                    case '2' -> {
                        pos[0] -= Long.decode(line[2].substring(1,7));
                        minPos[0] = Math.min(minPos[0],pos[0]);
                    }
                    case '1' -> {
                        pos[1] += Long.decode(line[2].substring(1,7));
                        maxPos[1] = Math.max(maxPos[1],pos[1]);
                    }
                    case '3' -> {
                        pos[1] -= Long.decode(line[2].substring(1,7));
                        minPos[1] = Math.min(minPos[1],pos[1]);
                    }
                }
            }
            pos = new long[] {-minPos[0],-minPos[1]};

            char prev = 'U';
            int dir = 1; //outside
            result = 0;

            for(String[] line: lines) {
                long trenchLength = Long.decode(line[2].substring(1,7));
                int instruction = Integer.parseInt(line[2].charAt(7)+"");
                switch(instruction) {
                    case 0 -> {//Right
                        if(prev == 'D') {
                            dir = Math.floorMod(dir + 1, 4);
                        }
                        else {
                            dir = Math.floorMod(dir - 1, 4);
                        }
                        if (dir == 0 && prev == 'D')
                            result --;
                        result -= pos[0];
                        prev = 'R';
                        pos[0] += trenchLength;
                    }
                    case 2 -> {//Left
                        pos[0] -= trenchLength;
                        if(prev == 'D') {
                            dir = Math.floorMod(dir - 1, 4);
                        } else {
                            dir = Math.floorMod(dir + 1, 4);
                        }
                        result -= pos[0];
                        prev = 'L';
                    }
                    case 1 -> {//Down
                        if(prev == 'R')
                            dir = Math.floorMod(dir - 1, 4);
                        else
                            dir = Math.floorMod(dir + 1, 4);

                        if(dir == 1) {
                            result -= (trenchLength-1)*pos[0];
                        } else {
                            result += (trenchLength+1)*(pos[0]+1);
                            if(prev == 'L')
                                result --;
                        }
                        prev = 'D';
                        pos[1] += trenchLength;
                    }
                    case 3 -> {//Up
                        if(prev == 'R')
                            dir = Math.floorMod(dir + 1, 4);
                        else
                            dir = Math.floorMod(dir - 1, 4);

                        if(dir == 3) {
                            result += (trenchLength+1)*(pos[0]+1);
                            if(prev == 'L')
                                result --;
                        } else {
                            result -= (trenchLength-1)*pos[0];
                        }
                        prev = 'U';
                        pos[1] -= trenchLength;
                    }
                }
//                System.out.println("Direction: "+ dir);
//                System.out.println("Operation: "+instruction);
//                System.out.println("Trench length: "+trenchLength);
//                System.out.println(result);
            }
            System.out.println(result);
            System.out.println("--------------------------------------");
        }
    }
}