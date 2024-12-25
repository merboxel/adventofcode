package merboxel.codeofadvent.y2024.D20;

import java.io.IOException;

public class D20 {

    public D20() {}

    public void run() throws IOException {
        System.out.println(new D20P1().runWithStats());
        System.out.println(new D20P2().runWithStats());
    }

    public static void main(String[] args) throws IOException {
        new D20().run();
    }
}
