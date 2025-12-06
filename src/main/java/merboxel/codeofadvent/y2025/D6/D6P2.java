package merboxel.codeofadvent.y2025.D6;


import merboxel.codeofadvent.AoC;
import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class D6P2 extends AoC {

    private static final int year = 2025;
    private static final int day = 6;
    private static final int part = 2;

    public D6P2(Scanner sc) throws IOException {
        super(year, day, part, sc);
    }

    public D6P2() throws IOException {
        super(year, day, part);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("--------------- Part 2 ---------------");
        System.out.println(new D6P2().run());
        System.out.println("--------------------------------------");
    }

    // Disable 'Remove trailing spaces'
    public String run() throws IOException {

        var x = ScannerUtil.toArray(sc);

        char[][] cephalopodGrid = Arrays.stream(x).map(String::toCharArray).toArray(char[][]::new);

        long result = 0;

        long subTotal = 0;
        boolean times = false;

        for(int i = 0; i < cephalopodGrid[0].length; i++){

            if(cephalopodGrid[cephalopodGrid.length-1][i] == '*'){
                result += subTotal;
                times = true;
                subTotal = 0;
            } else if (cephalopodGrid[cephalopodGrid.length-1][i] == '+'){
                result += subTotal;
                times = false;
                subTotal = 0;
            }
            long nbr = calculateNumber(cephalopodGrid, i);

            if(nbr == 0) {
                //continue
            } else if(subTotal == 0) {
                subTotal = nbr;
            } else if(times) {
                subTotal *= nbr;
            } else {
                subTotal += nbr;
            }
        }
        
        result += subTotal;

        return Long.toString(result);
    }

    private long calculateNumber(char[][] cephalopodGrid, int i) {
        long nbr = 0;

        for(int j = 0; j < cephalopodGrid.length - 1; j++){
            if(cephalopodGrid[j][i] != ' '){
                nbr = nbr * 10 + cephalopodGrid[j][i] - '0';
            }
        }
        return nbr;
    }
}