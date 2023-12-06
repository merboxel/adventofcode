package merboxel.codeofadvent.y2021;

import java.io.IOException;
import java.util.*;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D23 {

    public static void main(String[] args) throws IOException {
        D23P1.run();
        D23P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2021, 23);
    }
}

class D23P1 {

    public static void main(String[] args) throws IOException {
        run();
    }

    public static void run() throws IOException {

        System.out.println("--------------- Part 1 ---------------");
        int energy = 0;
        //#############
        //#...........#
        //###D#C#A#B###
        //  #C#D#A#B#
        //  #########
        Permutation start = new Permutation(0,"EEEEEEEEEEEDCABCDAB");
        Set<String> permutations = new HashSet<>();

        PriorityQueue<Permutation> bfs = new PriorityQueue<>();
        bfs.add(start);

        Maze maze = new Maze();

        while(!bfs.isEmpty()) {

            Permutation current = bfs.poll();

            if(permutations.contains(current.permutation))
                continue;
            permutations.add(current.permutation);

            if (current.isEndSituation()) {
                energy = current.energy;
                break;
            }

            List<Permutation> perms = maze.calcPermutations(current);

            for(Permutation newPerm: perms) {
                if(!permutations.contains(newPerm.permutation))
                    bfs.add(newPerm);
            }
        }

        System.out.println(energy);
        System.out.println("--------------------------------------");
    }
}

class D23P2 {

    public static void main(String[] args) throws IOException {
        run();
    }

    public static void run() throws IOException {
        System.out.println("--------------- Part 2 ---------------");


        System.out.println("--------------------------------------");
    }
}

class Permutation implements Comparable<Permutation> {
    int energy;
    String permutation;

    public Permutation(int energy, String permutation) {
        this.energy = energy;
        this.permutation = permutation;
    }

    public boolean isEndSituation() {
        return permutation.equals("EEEEEEEEEEEABCDABCD");
    }

    @Override
    public int compareTo(Permutation that) {
        return Integer.compare(this.energy,that.energy);
    }
}

class Maze {

    final Map<String,Integer> value = Map.of(
            "A",1,
            "B",10,
            "C",100,
            "D",1000
    );
    Chamber[] chambers = new Chamber[20];

    public Maze() {
        chambers[1] = new Chamber();
        chambers[2] = new Chamber();
        chambers[3] = new Chamber();
        chambers[4] = new Chamber();
        chambers[5] = new Chamber();
        chambers[6] = new Chamber();
        chambers[7] = new Chamber();
        chambers[8] = new Chamber();
        chambers[9] = new Chamber();
        chambers[10] = new Chamber();
        chambers[11] = new Chamber();
        chambers[12] = new Chamber();
        chambers[13] = new Chamber();
        chambers[14] = new Chamber();
        chambers[15] = new Chamber();
        chambers[16] = new Chamber();
        chambers[17] = new Chamber();
        chambers[18] = new Chamber();
        chambers[19] = new Chamber();

        chambers[1].setRight(chambers[2]);
        chambers[2].setRight(chambers[3]);
        chambers[3].setRight(chambers[4]);
        chambers[4].setRight(chambers[5]);
        chambers[5].setRight(chambers[6]);
        chambers[6].setRight(chambers[7]);
        chambers[7].setRight(chambers[8]);
        chambers[8].setRight(chambers[9]);
        chambers[9].setRight(chambers[10]);
        chambers[10].setRight(chambers[11]);

        chambers[19].setTop(chambers[15]);
        chambers[18].setTop(chambers[14]);
        chambers[17].setTop(chambers[13]);
        chambers[16].setTop(chambers[12]);
        chambers[15].setTop(chambers[9]);
        chambers[14].setTop(chambers[7]);
        chambers[13].setTop(chambers[5]);
        chambers[12].setTop(chambers[3]);
    }

    public List<Permutation> calcPermutations(Permutation current) {
        String permutation = current.permutation;
        setMaze(permutation);

        List<Permutation> perms = new ArrayList<>();
        for(int i = 1; i <= 19; i++) {
            if(chambers[i].c.equals("E")) {
                continue;
            }
            chambers[i].doNeighbours(0);
            int value = this.value.get(chambers[i].c);
            for(int j = 1; j <= 19; j++) {
                if(chambers[j].number != -1) {
                    char[] perm = current.permutation.toCharArray();
                    perm[j-1] = perm[i-1];
                    perm[i-1] = 'E';
                    int test = current.energy+value*chambers[j].number;
                    perms.add(new Permutation(current.energy+value*chambers[j].number, String.valueOf(perm)));
                }
            }
            resetMazePerm();
        }
        return perms;
    }

    private void resetMazePerm() {
        for(int i = 1; i <= 19; i++) {
            chambers[i].number = -1;
        }
    }

    private void setMaze(String permutation) {
        resetMazePerm();
        for(int i = 1; i <= 19; i ++) {
            chambers[i].setChar(permutation.charAt(i-1)+"");
        }
    }
}

class Chamber {
    boolean occupied;
    int number;
    Chamber left,bot,right,top;
    String c;

    public void setChar(String c) {
        this.c = c;
        this.occupied = !c.equals("E");
    }

    public void setRight(Chamber right) {
        this.right = right;
        right.left = this;
    }
    public void setTop(Chamber top) {
        this.top = top;
        top.bot = this;
    }

    public void doNeighbours(int number) {
        if((!occupied || number == 0) && this.number == -1) {
            if(number != 0)
                this.number = number;
            if (left != null)
                left.doNeighbours(number+1);
            if (right != null)
                right.doNeighbours(number+1);
            if (top != null)
                top.doNeighbours(number+1);
            if (bot != null)
                bot.doNeighbours(number+1);
        }
    }
}