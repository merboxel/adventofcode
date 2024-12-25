package merboxel.codeofadvent.y2024.D22;

import java.io.IOException;

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
