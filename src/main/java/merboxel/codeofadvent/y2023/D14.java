package merboxel.codeofadvent.y2023;


import java.io.IOException;
import java.util.*;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D14 {

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2023, 14);
    }

    static class P1 {

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 1 ---------------");
            long result = 0L;
            Scanner sc = readFile();

            List<String> _area = new ArrayList<>();

            while (sc.hasNext()) {
                _area.add(sc.nextLine());
            }

            char[][] area = _area.stream().map(String::toCharArray).toArray(char[][]::new);

            for(int y = 0; y < area[0].length; y++) {
                int boulders = 0;
                int square = 0;
                for(int x = 0; x < area.length; x++) {
                    if(area[x][y] == 'O') {
                        result += area.length - square - boulders;
                        boulders ++;
                        continue;
                    }
                    if(area[x][y] == '#') {
                        boulders = 0;
                        square = x+1;
                        continue;
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
            long result = 0L;
            Scanner sc = readFile();

            List<String> _area = new ArrayList<>();

            while (sc.hasNext()) {
                _area.add(sc.nextLine());
            }

            char[][] area = _area.stream().map(String::toCharArray).toArray(char[][]::new);
            Map<String,String> cycles = new HashMap<>();
            String prevArea = Arrays.deepToString(area);

            for(int i = 0; i < 1000000000; i++) {
                if(cycles.containsKey(prevArea)) {
                    break;
                }
                rollNorth(area);
                rollWest(area);
                rollSouth(area);
                rollEast(area);
                String currentArea = Arrays.deepToString(area);
                cycles.put(prevArea,currentArea);
                prevArea = currentArea;
            }
            int cycleLength = 0;
            String toCompare = prevArea;

            for(int i = 0; i < 1000000000; i++){
                cycleLength ++;
                prevArea = cycles.get(prevArea);
                if(prevArea.equals(toCompare))
                    break;
            }

            for(int i = 0; i < (1000000000 - cycles.size()) % cycleLength; i++) {
                rollNorth(area);
                rollWest(area);
                rollSouth(area);
                rollEast(area);
            }

            for(int x = 0; x < area[0].length; x++) {
                for(int y = 0; y < area.length; y++) {
                    if(area[y][x] == 'O') {
                        result += area.length - y;
                    }
                }
            }
            System.out.println(result);
            System.out.println("--------------------------------------");
        }

        public static void rollNorth(char[][] area) {
            for(int y = 0; y < area[0].length; y++) {
                int boulders = 0;
                int square = 0;
                for(int x = 0; x < area.length; x++) {
                    if(area[x][y] == 'O') {
                        area[x][y] = '.';
                        area[square + boulders][y] = 'O';
                        boulders ++;
                        continue;
                    }
                    if(area[x][y] == '#') {
                        boulders = 0;
                        square = x+1;
                        continue;
                    }
                }
            }
        }

        public static void rollSouth(char[][] area) {
            for(int y = area[0].length -1 ; y >= 0; y--) {
                int boulders = 0;
                int square = area[0].length -1;
                for(int x = area.length-1; x >= 0; x--) {
                    if(area[x][y] == 'O') {
                        area[x][y] = '.';
                        area[square - boulders][y] = 'O';
                        boulders ++;
                        continue;
                    }
                    if(area[x][y] == '#') {
                        boulders = 0;
                        square = x-1;
                        continue;
                    }
                }
            }
        }

        public static void rollWest(char[][] area) {
            for(int x = 0; x < area.length; x++) {
                int boulders = 0;
                int square = 0;
                for(int y = 0; y < area.length; y++) {
                    if(area[x][y] == 'O') {
                        area[x][y] = '.';
                        area[x][square + boulders] = 'O';
                        boulders ++;
                        continue;
                    }
                    if(area[x][y] == '#') {
                        boulders = 0;
                        square = y+1;
                        continue;
                    }
                }
            }
        }

        public static void rollEast(char[][] area) {
            for(int x = 0; x < area.length; x++) {
                int boulders = 0;
                int square = area.length-1;
                for(int y = area.length-1; y >= 0; y--) {
                    if(area[x][y] == 'O') {
                        area[x][y] = '.';
                        area[x][square - boulders] = 'O';
                        boulders ++;
                        continue;
                    }
                    if(area[x][y] == '#') {
                        boulders = 0;
                        square = y-1;
                        continue;
                    }
                }
            }
        }
    }
}