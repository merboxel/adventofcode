package merboxel.codeofadvent.y2024.D22;


import merboxel.codeofadvent.AoC;

import java.io.IOException;
import java.util.*;

public class D22P2_2 extends AoC {

    D22P2_2(Scanner sc) throws IOException {
        super(2024, 22, 2, sc);
    }

    D22P2_2() throws IOException {
        super(2024, 22, 2);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("--------------- Part 2 ---------------");
        System.out.println(new D22P2_2().run());
        System.out.println("--------------------------------------");
    }

    public String run() {
        List<Integer> initSecrets = new ArrayList<>();

        while (sc.hasNextLong()) {
            initSecrets.add(sc.nextInt());
        }

        int[][] prices = new int[initSecrets.size()][2001];

        for (int si = 0; si < initSecrets.size(); si++) {
            int secret = initSecrets.get(si);
            prices[si] = generateSecrets(secret).stream().mapToInt(s ->
                    s % 10
            ).toArray();
        }

        int[][] deltaPrices = new int[initSecrets.size()][2001];
        for (int i = 0; i < initSecrets.size(); i++) {
            for (int j = 1; j < prices[i].length; j++) {
                deltaPrices[i][j] = prices[i][j] - prices[i][j - 1];
            }
        }

        Map<Quad, Integer> sequences = new HashMap<>();

        for (int i = 0; i < initSecrets.size(); i++) {
            Set<Quad> seen = new HashSet<>();
            for (int j = 4; j < deltaPrices[i].length; j++) {
                Quad tmp = new Quad(
                        (deltaPrices[i][j - 3] + 9),
                        (deltaPrices[i][j - 2] + 9),
                        (deltaPrices[i][j - 1] + 9),
                        (deltaPrices[i][j] + 9)
                );
                if (!seen.contains(tmp)) {
                    seen.add(tmp);
                    sequences.put(tmp, (sequences.getOrDefault(tmp, 0) + prices[i][j]));
                }
            }
        }

        int result = sequences.values().stream().max(Integer::compare).get();

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

    private record Quad(
            int x1,
            int x2,
            int x3,
            int x4
    ) implements Comparable<Quad> {

        @Override
        public int compareTo(Quad that) {
            if (this.x1 == that.x1 && this.x2 == that.x2 && this.x3 == that.x3 && this.x4 == that.x4) {
                return 0;
            }

            if (this.x1 > that.x1 && this.x2 > that.x2 && this.x3 > that.x3 && this.x4 > that.x4) {
                return 1;
            }
            return -1;
        }
    }
}