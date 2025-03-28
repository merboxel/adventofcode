package merboxel.codeofadvent.y2024.D25;

import merboxel.codeofadvent.annotation.AOC2024;

import java.io.IOException;

@AOC2024
public class D25 {

    public D25() {}

    public void run() throws IOException {
        System.out.println(new D25P1().runWithStats());
    }

    public static void main(String[] args) throws IOException {
        new D25().run();
    }
}
