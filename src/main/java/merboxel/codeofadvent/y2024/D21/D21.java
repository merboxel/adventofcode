package merboxel.codeofadvent.y2024.D21;

import java.io.IOException;

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
