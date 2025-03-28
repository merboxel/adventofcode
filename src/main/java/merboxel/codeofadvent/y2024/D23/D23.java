package merboxel.codeofadvent.y2024.D23;

import merboxel.codeofadvent.annotation.AOC2025;

import java.io.IOException;

@AOC2025
public class D23 {

    public D23() {}

    public void run() throws IOException {
        System.out.println(new D23P1().runWithStats());
        System.out.println(new D23P2().runWithStats());
    }

    public static void main(String[] args) throws IOException {
        new D23().run();
    }
}
