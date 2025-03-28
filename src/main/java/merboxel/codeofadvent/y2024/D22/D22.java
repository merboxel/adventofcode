package merboxel.codeofadvent.y2024.D22;

import merboxel.codeofadvent.annotation.AOC2025;

import java.io.IOException;

@AOC2025
public class D22 {

    public D22() {}

    public void run() throws IOException {
        System.out.println(new D22P1().runWithStats());
        System.out.println(new D22P2().runWithStats());
    }

    public static void main(String[] args) throws IOException {
        new D22().run();
    }
}
