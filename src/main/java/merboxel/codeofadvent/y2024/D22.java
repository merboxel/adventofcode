package merboxel.codeofadvent.y2024;


import merboxel.codeofadvent.AoC;
import merboxel.codeofadvent.util.ArrayUtils;
import merboxel.codeofadvent.util.BitUtils;
import merboxel.codeofadvent.util.PatternMatching;
import merboxel.codeofadvent.util.ScannerUtil;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D22 extends AoC {

    D22(int year, int day) {
        this.year = year;
        this.day = day;
    }

    public static void main(String[] args) throws IOException {
        AoC d = new D22(2024,22);
        d.run();
    }

    public void runP1(Scanner sc) {
        System.out.println("--------------- Part 1 ---------------");

        List<Integer> initSecrets = new ArrayList<>();
        long result = 0L;

        while(sc.hasNextLong()) {
            initSecrets.add(sc.nextInt());
        }

        for(Integer secret : initSecrets) {
            result += generateSecrets(secret).getLast();
        }


        System.out.println(result);
        System.out.println("--------------------------------------");
    }

    public void runP2(Scanner sc) {
        System.out.println("--------------- Part 2 ---------------");

        List<Integer> initSecrets = new ArrayList<>();

        while(sc.hasNextLong()) {
            initSecrets.add(sc.nextInt());
        }

        int[][] prices = new int[initSecrets.size()][2001];

        for(int si = 0; si < initSecrets.size(); si++) {
            int secret = initSecrets.get(si);
            prices[si] = generateSecrets(secret).stream().mapToInt(s ->
                    s % 10
            ).toArray();
        }

        int[][] deltaPrices = new int[initSecrets.size()][2001];
        for(int i = 0; i < initSecrets.size(); i++) {
            for(int j = 1; j < prices[i].length; j++) {
                deltaPrices[i][j] = prices[i][j] - prices[i][j-1];
            }
        }

        Map<Integer, Integer> result = new HashMap<>();

        for(int i = 0; i < initSecrets.size(); i++) {
            Set<Integer> seen = new HashSet<>();
            for(int j = 4; j < deltaPrices[i].length; j++) {
                int tmp = ((deltaPrices[i][j-3] + 9) << 12) + ((deltaPrices[i][j-2] + 9) << 8) + ((deltaPrices[i][j-1] + 9) << 4) + (deltaPrices[i][j] + 9);
                if(!seen.contains(tmp)) {
                    seen.add(tmp);
                    result.put(tmp, (result.getOrDefault(tmp, 0) + prices[i][j]));
                }
            }
        }

        System.out.println(result.values().stream().max(Integer::compare).get());
        System.out.println("--------------------------------------");
    }

    private List<Integer> generateSecrets(Integer secret) {

        List<Integer> secrets = new ArrayList<>();
        secrets.add(secret);

        for(int i = 0; i < 2000; i++) {
            secret ^= secret * 64;
            secret = Math.floorMod(secret, 16777216);

            secret ^= secret / 32;
            secret = Math.floorMod(secret, 16777216);

            secret ^= secret * 2048;
            secret = Math.floorMod(secret, 16777216);

            secrets.add(secret);
        }
        return secrets;
    }
}