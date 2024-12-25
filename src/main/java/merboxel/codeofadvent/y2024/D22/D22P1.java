package merboxel.codeofadvent.y2024.D22;


import merboxel.codeofadvent.AoC;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class D22P1 extends AoC {

    private static final int year = 2024;
    private static final int day = 22;
    private static final int part = 1;

    public D22P1(Scanner sc) {
        super(year, day, part, sc);
    }

    D22P1() throws IOException {
        super(year, day, part);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("--------------- Part 1 ---------------");
        System.out.println(new D22P1().run());
        System.out.println("--------------------------------------");
    }

    public String run() throws IOException {

        List<Integer> initSecrets = new ArrayList<>();
        long result = 0L;

        while (sc.hasNextLong()) {
            initSecrets.add(sc.nextInt());
        }

        for (Integer secret : initSecrets) {
            result += generateSecrets(secret).getLast();
        }

        return Long.toString(result);
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