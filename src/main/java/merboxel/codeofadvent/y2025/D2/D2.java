package merboxel.codeofadvent.y2025.D2;

import merboxel.codeofadvent.y2025.D1.D1P1;

import java.io.IOException;

public class D2 {

    public D2() {}

    public static void main(String[] args) throws IOException {
        new D2().run();
    }

    public void run() throws IOException {
        System.out.println(new D1P1().runWithStats());
//        System.out.println(new D1P2().runWithStats());
    }
}
