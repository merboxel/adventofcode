package merboxel.codeofadvent.y2023;

import merboxel.codeofadvent.util.PatternMatching;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D12 {

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2023, 12);
    }

    static class P1 {

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 1 ---------------");
            long result = 0L;
            Scanner sc = readFile();

            while(sc.hasNext()) {
                String[] line = PatternMatching.getWordsAsArray(sc.nextLine());
                String spring = line[0];

                Pattern pInt = Pattern.compile("[^\\.]+");
                Matcher mInt = pInt.matcher(spring);
                List<String> _springParts = new ArrayList<>();

                while(mInt.find())
                    _springParts.add(mInt.group());
                String[] springParts = _springParts.toArray(new String[0]);

                int[] damages = PatternMatching.getIntegersAsArray(line[1]);
                int currDamage;

                long subTotal = calcParts(springParts,0,damages,0,0);
                System.out.println(subTotal);
                result += subTotal;
            }
            System.out.println(result);
            System.out.println("--------------------------------------");
        }

        public static long calcParts(String[] springParts, int currPart, int[] damages, int startDamage, int endDamage) {
            long subTotal = 0;

            if(currPart >= springParts.length)
                return 0;

            if(startDamage >= damages.length)
                return 1;


                //Add current
            long subResult1 = calcParts(springParts, currPart+1, damages, startDamage+endDamage, 0);
            long subResult2 = calcFit(springParts[currPart].toCharArray(), 0, damages, startDamage, startDamage+endDamage);
            subTotal += subResult1*subResult2;

            //Pass through
            subTotal = calcParts(springParts, currPart,damages,startDamage,endDamage+1);

            return subTotal;
        }

        public static long calcFit(char[] springPart, int index, int[] damages, int startDamage, int endDamage) {

            int currDamage = damages[startDamage];

            if(currDamage >= springPart.length || startDamage > endDamage)
                return 0;

            checkFit(springPart,index,currDamage);
            if(startDamage == endDamage)
                return 1;

            if(currDamage+index >= springPart.length || springPart[currDamage+index] == '#')
                return 0;

            long subTotal = 0;
            for(int i = currDamage+index+1; i < springPart.length; i ++) {
                subTotal += calcFit(springPart,i,damages,startDamage+1,endDamage);
            }

            return subTotal;
        }

        public static boolean checkFit(char[] part, int x, int damage) {
            if(x+damage >= part.length)
                return false;
            for(int i = 0; i < damage; i++) {
                if (part[x+i] == '.')
                    return false;
            }
            return true;
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

            while(sc.hasNext()) {
            }
            System.out.println(result);
            System.out.println("--------------------------------------");
        }
    }

    static class Part {

    }
}