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

            while (sc.hasNext()) {
                String[] line = PatternMatching.getWordsAsArray(sc.nextLine());
                String _spring = line[0];
                String spring = _spring+"?"+_spring+"?"+_spring+"?"+_spring+"?"+_spring;

                int[] _damages = PatternMatching.getIntegersAsArray(line[1]);
                int[] damages = new int[_damages.length*5];

                for(int i =0; i<5;i++){
                    System.arraycopy(_damages, 0, damages, i * _damages.length, _damages.length);
                }

                D12.P2.DP x = new DP(spring.toCharArray(),damages);
                result += DP.run();
            }
            System.out.println(result);
            System.out.println("--------------------------------------");
        }
        static class DP {
            static long[][] dp;
            static char[] line;
            static int[] damages;
            public DP(char[] line,int[] damages) {
                DP.line = line;
                DP.damages = damages;
                dp = new long[line.length+1][damages.length+1];
                for (long[] longs : dp) {
                    Arrays.fill(longs, -1);
                }
                dp[line.length][damages.length] = 1;
                for(int i = 0; i < damages.length; i++)
                    dp[line.length][i] = 0;
            }
            public static long run() {
                return dynamic(0,0);
            }

            public static long dynamic(int i, int _j) {
                long subTotal = 0;

                if(i > dp.length)
                    return 0;

                if(dp[i][_j] != -1)
                    return dp[i][_j];

                if(_j == damages.length) {
                    if(line[i] == '#') {
                        dp[i][_j] = 0;
                        return 0;
                    }
                    subTotal += dynamic(i+1,_j);
                } else {
                    switch (line[i]) {
                        case '.' -> {
                            subTotal += dynamic(i + 1, _j);
                        }
                        case '#' -> {
                            subTotal += addDamage(i,_j);
                        }
                        default -> {
                            subTotal += addDamage(i,_j);
                            subTotal += dynamic(i + 1, _j);
                        }
                    }
                }
                dp[i][_j] = subTotal;

                return subTotal;
            }
            public static boolean validNonDamage(int i) {
                return (i < line.length && line[i] != '#');
            }

            public static boolean validDamage(int i, int _j) {
                if(i + damages[_j]-1 >= line.length)
                    return false;
                for(int j = 0; j < damages[_j]; j++)
                    if(line[i+j] == '.')
                        return false;
                return true;
            }

            public static long addDamage(int i, int _j) {
                int damage = damages[_j];
                long subTotal = 0;

                if(validDamage(i,_j)) {
                    if(_j + 1 < damages.length) {
                        if(validNonDamage(i+damages[_j])) {
                            subTotal += dynamic(i + damage + 1, _j + 1);
                        }
                    } else {
                        subTotal += dynamic(i + damage, _j + 1);
                    }
                }

                return subTotal;
            }
        }
    }
}