package merboxel.codeofadvent.y2024.D22;


import merboxel.codeofadvent.AoC;

import java.io.IOException;
import java.util.*;

public class D22P2 extends AoC {

    public D22P2(Scanner sc) {
        super(2024, 22, sc);
    }

    D22P2() throws IOException {
        super(2024, 22);
    }

    public static void main(String[] args) throws IOException {
        new D22P2().run();
    }

    public String run() throws IOException {
        System.out.println("--------------- Part 2 ---------------");

        List<Integer> initSecrets = new ArrayList<>();

        while (sc.hasNextLong()) {
            initSecrets.add(sc.nextInt());
        }

        int[][] prices = new int[initSecrets.size()][2001];

        for (int si = 0; si < initSecrets.size(); si++) {
            int secret = initSecrets.get(si);
            prices[si] = generateSecrets(secret).stream().mapToInt(s -> s % 10).toArray();
        }

        int[][] deltaPrices = new int[initSecrets.size()][2001];
        for (int i = 0; i < initSecrets.size(); i++) {
            for (int j = 1; j < prices[i].length; j++) {
                deltaPrices[i][j] = prices[i][j] - prices[i][j - 1];
            }
        }

        Map<Integer, Integer> sequences = new HashMap<>();

        for (int i = 0; i < initSecrets.size(); i++) {
            Set<Integer> seen = new HashSet<>();
            for (int j = 4; j < deltaPrices[i].length; j++) {
                int tmp = ((deltaPrices[i][j - 3] + 9) << 12) + ((deltaPrices[i][j - 2] + 9) << 8) + ((deltaPrices[i][j - 1] + 9) << 4) + (deltaPrices[i][j] + 9);
                if (!seen.contains(tmp)) {
                    seen.add(tmp);
                    sequences.put(tmp, (sequences.getOrDefault(tmp, 0) + prices[i][j]));
                }
            }
        }

        int result = sequences.values().stream().max(Integer::compare).get();

        System.out.println();
        System.out.println("--------------------------------------");

        return Integer.toString(result);
    }

    private List<Integer> generateSecrets(Integer secret) {

        List<Integer> secrets = new ArrayList<>();
        secrets.add(secret);

        for (int i = 0; i < 2000; i++) {
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