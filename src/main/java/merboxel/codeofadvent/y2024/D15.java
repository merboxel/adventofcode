package merboxel.codeofadvent.y2024;


import java.io.IOException;
import java.util.*;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D15 {

    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2024, 15);
    }

    static class P1 {
        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 1 ---------------");
            Scanner sc = readFile();

            long result = 0L;

            List<String> _grid = new ArrayList<>();
            List<String> movements = new ArrayList<>();

            String tmp = sc.nextLine();;
            do {
                _grid.add(tmp);
            }while(!Objects.equals(tmp = sc.nextLine(), ""));

            while(sc.hasNextLine()) {
                movements.add(sc.nextLine());
            }

            char[][] grid = _grid.stream().map(String::toCharArray).toArray(char[][]::new);
            int[] loc = new int[2];
            for(int i = 0; i < grid.length; i++) {
                for(int j = 0; j < grid[i].length; j++) {
                    if(grid[i][j] == '@') {
                        loc[0] = i;
                        loc[1] = j;
                        grid[i][j] = '.';
                    }
                }
            }

            for(String movement : movements) {
                for(char c: movement.toCharArray()) {
                    switch (c) {
                        case '^' -> {
                            move(grid, loc, dir[0]);
                        }
                        case '>' -> {
                            move(grid, loc, dir[1]);
                        }
                        case 'v' -> {
                            move(grid, loc, dir[2]);
                        }
                        case '<' -> {
                            move(grid, loc, dir[3]);
                        }
                    }
                }
            }

            for(int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if(grid[i][j] == 'O') {
                        result += i * 100L + j;
                    }
                }
            }

            System.out.println(result);
            System.out.println("--------------------------------------");
        }

        public static void move(char[][] grid, int[] loc, int[] dir) {
            int nrBoxes = 1;
            boolean loop = true;
            while(loop) {
                char c = grid[loc[0] + nrBoxes * dir[0]][loc[1] + nrBoxes * dir[1]];
                switch (c) {
                    case 'O' ->
                        nrBoxes++;
                    case '.' -> {
                        grid[loc[0] + nrBoxes * dir[0]][loc[1] + nrBoxes * dir[1]] = grid[loc[0] + dir[0]][loc[1] + dir[1]];
                        grid[loc[0] + dir[0]][loc[1] + dir[1]] = '.';
                        loc[0] += dir[0];
                        loc[1] += dir[1];
                        loop = false;
                    }
                    case '#' -> {
                        loop = false;
                    }
                }
            }
        }
    }

    static class P2 {

        static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 2 ---------------");
            Scanner sc = readFile();

            long result = 0L;

            List<String> _grid = new ArrayList<>();
            List<String> movements = new ArrayList<>();

            String tmp = sc.nextLine();;
            do {
                StringBuilder _tmp = new StringBuilder();
                for(char c : tmp.toCharArray()) {
                    switch (c) {
                        case '#' -> _tmp.append("##");
                        case '.' -> _tmp.append("..");
                        case '@' -> _tmp.append("@.");
                        case 'O' -> _tmp.append("[]");
                    }
                }
                _grid.add(_tmp.toString());
            } while(!Objects.equals(tmp = sc.nextLine(), ""));

            while(sc.hasNextLine()) {
                movements.add(sc.nextLine());
            }

            char[][] grid = _grid.stream().map(String::toCharArray).toArray(char[][]::new);
            int[] loc = new int[2];
            for(int i = 0; i < grid.length; i++) {
                for(int j = 0; j < grid[i].length; j++) {
                    if(grid[i][j] == '@') {
                        loc[0] = i;
                        loc[1] = j;
                        grid[i][j] = '.';
                    }
                }
            }

            for(String movement : movements) {
                for(char c: movement.toCharArray()) {
                    switch (c) {
                        case '^' -> {
                            if(canMoveVertical(grid, loc, dir[0])) {
                                moveVertical(grid, loc, dir[0]);
                                loc[0] += dir[0][0];
                                loc[1] += dir[0][1];
                            }
                        }
                        case '>' -> {
                            moveHorizontal(grid, loc, dir[1]);
                        }
                        case 'v' -> {
                            if(canMoveVertical(grid, loc, dir[2])) {
                                moveVertical(grid, loc, dir[2]);
                                loc[0] += dir[2][0];
                                loc[1] += dir[2][1];
                            }
                        }
                        case '<' -> {
                            moveHorizontal(grid, loc, dir[3]);
                        }
                    }
                }
            }

            for(int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if(grid[i][j] == '[') {
                        result += i * 100L + j;
                    }
                }
            }

            System.out.println(result);
            System.out.println("--------------------------------------");
        }

        public static boolean moveHorizontal(char[][] grid, int[] loc, int[] dir) {
            boolean result = false;
            char c = grid[loc[0] + dir[0]][loc[1] + dir[1]];
            switch (c) {
                case '[',']' -> {
                    if(moveHorizontal(grid, new int[]{(loc[0] + dir[0]), (loc[1] + dir[1])}, dir)) {
                        grid[loc[0] + dir[0]][loc[1] + dir[1]] = grid[loc[0]][loc[1]];
                        grid[loc[0]][loc[1]] = '.';
                        loc[0] += dir[0];
                        loc[1] += dir[1];
                        result = true;
                    }
                }
                case '.' -> {
                    grid[loc[0] + dir[0]][loc[1] + dir[1]] = grid[loc[0]][loc[1]];
                    grid[loc[0]][loc[1]] = '.';
                    loc[0] += dir[0];
                    loc[1] += dir[1];
                    result = true;
                }
                case '#' -> {}
            }
            return result;
        }

        public static void moveVertical(char[][] grid, int[] loc, int[] dir) {

            char c = grid[loc[0] + dir[0]][loc[1] + dir[1]];
            switch (c) {
                case '[' -> {
                    int[] newLoc1 = new int[] {loc[0]+dir[0], loc[1]+dir[1]};
                    int[] newLoc2 = new int[] {loc[0]+dir[0], loc[1]+dir[1]+1};
                    moveVertical(grid, newLoc1, dir);
                    moveVertical(grid, newLoc2, dir);
                }
                case ']' -> {
                    int[] newLoc1 = new int[] {loc[0]+dir[0], loc[1]+dir[1]-1};
                    int[] newLoc2 = new int[] {loc[0]+dir[0], loc[1]+dir[1]};
                    moveVertical(grid, newLoc1, dir);
                    moveVertical(grid, newLoc2, dir);
                }
            }
            grid[loc[0] + dir[0]][loc[1] + dir[1]] = grid[loc[0]][loc[1]];
            grid[loc[0]][loc[1]] = '.';
        }

        public static boolean canMoveVertical(char[][] grid, int[] loc, int[] dir) {
            boolean result = false;
            char c = grid[loc[0] + dir[0]][loc[1] + dir[1]];
            switch (c) {
                case '[' -> {
                    int[] newLoc1 = new int[] {loc[0]+dir[0], loc[1]+dir[1]};
                    int[] newLoc2 = new int[] {loc[0]+dir[0], loc[1]+dir[1]+1};
                    result = (canMoveVertical(grid, newLoc1, dir) && canMoveVertical(grid, newLoc2, dir));
                }
                case ']' -> {
                    int[] newLoc1 = new int[] {loc[0]+dir[0], loc[1]+dir[1]-1};
                    int[] newLoc2 = new int[] {loc[0]+dir[0], loc[1]+dir[1]};
                    result = (canMoveVertical(grid, newLoc1, dir) && canMoveVertical(grid, newLoc2, dir));
                }
                case '.' -> {
                    result = true;
                }
                case '#' -> {}
            }
            return result;
        }
    }
}