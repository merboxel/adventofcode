package merboxel.codeofadvent.y2025.D2;

import java.io.IOException;

public class D2 {

    public D2() {}

    public static void main(String[] args) throws IOException {
        new D2().run();
    }

    public void run() throws IOException {
        System.out.println(new D2P1().runWithStats());
        System.out.println(new D2P2().runWithStats());
    }
}
