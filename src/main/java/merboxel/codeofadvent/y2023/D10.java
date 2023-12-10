package merboxel.codeofadvent.y2023;

import merboxel.codeofadvent.util.PatternMatching;

import java.io.IOException;
import java.util.*;
import java.util.stream.LongStream;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D10 {

    public static void main(String[] args) throws IOException {
        D10P1.run();
        D10P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2023, 10);
    }
}

class D10P1 {

    public static void main(String[] args) throws IOException {
        run();
    }

    public static void run() throws IOException {
        System.out.println("--------------- Part 1 ---------------");
        long result = 0;
        Scanner sc = D10.readFile();
        List<String> inputFile = new ArrayList<>();

        while(sc.hasNext()) {
            inputFile.add(sc.nextLine());

        }
        int endX=-1,endY=-1;
        int opX=-1,opY=-1;
        int currX=-1,currY=-1;

        char[][] area = new char[inputFile.size()][];
        for(int y = 0; y < area.length; y++) {
            area[y] = inputFile.get(y).toCharArray();
            for(int x = 0; x < area[y].length; x++) {
                if('S' == area[y][x]) {
                    endX = x;
                    opX = 0;//Manual starting position (change on test case)
                    currX = x+opX;
                    endY = y;
                    opY = 1;//Manual starting position (change on test case)
                    currY = y+opY;
                    result++;
                }
            }
        }

        while(!(endX == currX && endY == currY))
        {
            int[] next = Pipe.calcNextPipe(area[currY][currX],opX,opY);
            opX = next[0];
            opY = next[1];
            currX += next[0];
            currY += next[1];
            result ++;
        }

        System.out.println(result/2);
        System.out.println("--------------------------------------");
    }
}

class Pipe {

    public static int[] calcNextPipe(char c, int x, int y) {

        if (c == '-' || c == '|')
            return new int[]{x, y};
        if (c == 'J')
            if (x == 1)
                return new int[]{0, -1};
            else
                return new int[]{-1, 0};
        if (c == 'F')
            if (x == -1)
                return new int[]{0, 1};
            else
                return new int[]{1, 0};
        if (c == 'L')
            if (x == -1)
                return new int[]{0, -1};
            else
                return new int[]{1, 0};
        if (c == '7')
            if (x == 1)
                return new int[]{0, 1};
            else
                return new int[]{-1, 0};
        throw new RuntimeException("Impossible '"+c+"'");
    }
}


class D10P2 {

    public static void main(String[] args) throws IOException {
        run();
    }

    public static void run() throws IOException {
        System.out.println("--------------- Part 2 ---------------");
        long result = 0L;
        Scanner sc = D10.readFile();
        List<String> inputFile = new ArrayList<>();

        while(sc.hasNext()) {
            inputFile.add(sc.nextLine());
        }
        int endX=-1,endY=-1;
        int opX=-1,opY=-1;
        int currX=-1,currY=-1;

        char[][] area = new char[inputFile.size()][];
        boolean[][] pipe = new boolean[inputFile.size()][];
        for(int y = 0; y < area.length; y++) {
            area[y] = inputFile.get(y).toCharArray();
            pipe[y] = new boolean[area[y].length];
            for(int x = 0; x < area[y].length; x++) {
                if('S' == area[y][x]) {
                    endX = x;
                    opX = 0;//Manual starting position (change on test case)
                    currX = x+opX;
                    endY = y;
                    opY = 1;//Manual starting position (change on test case)
                    currY = y+opY;
                }
            }
        }

        pipe[endY][endX] = true;

        while(!(endX == currX && endY == currY))
        {
            pipe[currY][currX] = true;
            int[] next = Pipe.calcNextPipe(area[currY][currX],opX,opY);
            opX = next[0];
            opY = next[1];
            currX += next[0];
            currY += next[1];
        }

        for(int y = 0; y < pipe.length; y++) {
            boolean withIn = false;
            char prevC = '.';
            for(int x = 0; x < pipe[y].length; x++) {
                if(!pipe[y][x]) {
                    if(withIn)
                        result ++;
                    else
                        continue;
                } else {
                    if(area[y][x] == '|') {
                        withIn = !withIn;
                        continue;
                    }
                    if(area[y][x] == 'F' || area[y][x] == 'L') {
                        prevC = area[y][x];
                        continue;
                    }
                    if(area[y][x] == 'J') {
                        if(prevC == 'F')
                            withIn = !withIn;
                        prevC = area[y][x];
                        continue;
                    }
                    if(area[y][x] == '7') {
                        if(prevC == 'L')
                            withIn = !withIn;
                        prevC = area[y][x];
                        continue;
                    }
                }
            }
        }

        System.out.println(result);
        System.out.println("--------------------------------------");
    }
}

