package merboxel.codeofadvent.y2024.D21;

import merboxel.codeofadvent.annotation.AOC2024;

import java.io.IOException;

@AOC2024
public class D21 {

    public D21() {}

    public void run() throws IOException {
        System.out.println(new D21P1().runWithStats());
        System.out.println(new D21P2().runWithStats());
    }

    public static void main(String[] args) throws IOException {
        new D21().run();
    }
}
