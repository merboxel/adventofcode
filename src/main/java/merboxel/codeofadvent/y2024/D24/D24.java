package merboxel.codeofadvent.y2024.D24;

import java.io.IOException;

public class D24 {

    public D24() {}

    public void run() throws IOException {
        System.out.println(new D24P1().runWithStats());
        System.out.println(new D24P2().runWithStats());
    }

    public static void main(String[] args) throws IOException {
        new D24().run();
    }
}
