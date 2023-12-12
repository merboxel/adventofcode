package merboxel.codeofadvent.y2023;

import merboxel.codeofadvent.util.PatternMatching;

import java.io.IOException;
import java.util.*;
import java.util.function.IntFunction;
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

            while (sc.hasNext()) {
                String[] line = PatternMatching.getWordsAsArray(sc.nextLine());
                String spring = line[0];

                Pattern pInt = Pattern.compile("[^\\.]+");
                Matcher mInt = pInt.matcher(spring);
                List<String> _springParts = new ArrayList<>();

                while (mInt.find())
                    _springParts.add(mInt.group());

                String[] springParts = _springParts.toArray(new String[0]);

                int[] damages = PatternMatching.getIntegersAsArray(line[1]);

                long subTotal = calcPossibilities(springParts, damages);
                System.out.println(subTotal);
                result += subTotal;

            }
            System.out.println(result);
            System.out.println("--------------------------------------");
        }

        public static long calcPossibilities(String[] parts, int[] broken) {

            if (broken.length == 0) {
                for (String part : parts) {
                    if (possibleCombinations(part.toCharArray(), 0, new int[0], false) == 0)
                        return 0;
                }
                return 1;
            }

            if (parts.length == 0)
                return 0;

            long subTotal = 0;

            long comb1 = possibleCombinations(parts[0].toCharArray(), 0, new int[0], false);
            if (comb1 != 0L) {
                long comb2 = calcPossibilities(Arrays.stream(parts).skip(1).toArray(String[]::new), broken);
                subTotal += comb1 * comb2;
            }
            for (int i = 0; i < broken.length; i++) {

                long comb3 = possibleCombinations(parts[0].toCharArray(), 0, Arrays.stream(broken).limit(i + 1).toArray(), false);
                if (comb3 != 0) {
                    long comb4 = calcPossibilities(Arrays.stream(parts).skip(1).toArray(String[]::new), Arrays.stream(broken).skip(i + 1).toArray());
                    subTotal += comb3 * comb4;
                }
            }

            return subTotal;
        }

        public static long possibleCombinations(char[] part, int index, int[] broken, boolean prevBroken) {

            if (index > part.length)
                return 0;

            if (broken.length == 0) {
                for (int i = index; i < part.length; i++)
                    if (part[i] == '#')
                        return 0;
                return 1;
            }

            try {
                if (prevBroken) {
                    if (part[index] == '#') {
                        return 0;
                    }
                    index++;
                }
            } catch (Exception ignored) {
            }

            long subTotal = 0;

            //Only skip if not broken
            try {
                if (part[index] != '#')
                    subTotal += possibleCombinations(part, index + 1, broken, false);
                subTotal += possibleCombinations(part, index + broken[0], Arrays.stream(broken).skip(1).toArray(), true);
            } catch (Exception ignored) {
            }

            return subTotal;
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

            int count = 0;

            while (sc.hasNext()) {
                count ++;
                String[] line = PatternMatching.getWordsAsArray(sc.nextLine());
                String _spring = line[0];
                String spring = _spring+"?"+_spring+"?"+_spring+"?"+_spring+"?"+_spring;

                Pattern pInt = Pattern.compile("[^\\.]+");
                Matcher mInt = pInt.matcher(spring);
                List<String> _springParts = new ArrayList<>();

                while (mInt.find())
                    _springParts.add(mInt.group());

                String[] springParts = _springParts.toArray(new String[0]);

                int[] _damages = PatternMatching.getIntegersAsArray(line[1]);
                int[] damages = new int[_damages.length*5];

                for(int i =0; i<5;i++){
                    for(int j=0; j< _damages.length;j++) {
                        damages[i*_damages.length+j] = _damages[j];
                    }
                }

                long subTotal = calcPossibilities(springParts, damages);
                System.out.println(count);
                System.out.println(subTotal);
                result += subTotal;

            }
            System.out.println(result);
            System.out.println("--------------------------------------");
        }

        public static long calcPossibilities(String[] parts, int[] broken) {

            if (broken.length == 0) {
                for (String part : parts) {
                    if (possibleCombinations(part.toCharArray(), 0, new int[0], false) == 0)
                        return 0;
                }
                return 1;
            }

            if (parts.length == 0)
                return 0;

            long subTotal = 0;

            long comb1 = possibleCombinations(parts[0].toCharArray(), 0, new int[0], false);
            if (comb1 != 0L) {
                long comb2 = calcPossibilities(Arrays.stream(parts).skip(1).toArray(String[]::new), broken);
                subTotal += comb1 * comb2;
            }
            for (int i = 0; i < broken.length; i++) {

                long comb3 = possibleCombinations(parts[0].toCharArray(), 0, Arrays.stream(broken).limit(i + 1).toArray(), false);
                if (comb3 != 0) {
                    long comb4 = calcPossibilities(Arrays.stream(parts).skip(1).toArray(String[]::new), Arrays.stream(broken).skip(i + 1).toArray());
                    subTotal += comb3 * comb4;
                }
            }

            return subTotal;
        }

        public static long possibleCombinations(char[] part, int index, int[] broken, boolean prevBroken) {

            if (index > part.length)
                return 0;

            if (broken.length == 0) {
                for (int i = index; i < part.length; i++)
                    if (part[i] == '#')
                        return 0;
                return 1;
            }

            try {
                if (prevBroken) {
                    if (part[index] == '#') {
                        return 0;
                    }
                    index++;
                }
            } catch (Exception ignored) {
            }

            long subTotal = 0;

            //Only skip if not broken
            try {
                if (part[index] != '#')
                    subTotal += possibleCombinations(part, index + 1, broken, false);
                subTotal += possibleCombinations(part, index + broken[0], Arrays.stream(broken).skip(1).toArray(), true);
            } catch (Exception ignored) {
            }

            return subTotal;
        }
    }
}