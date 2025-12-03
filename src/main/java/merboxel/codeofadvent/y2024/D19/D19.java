package merboxel.codeofadvent.y2024.D19;

import java.io.IOException;

public class D19 {

    public D19() {}

    public static void main(String[] args) throws IOException {
        new D19().run();
    }

    public void run() throws IOException {
        System.out.println(new D19P1().runWithStats());
        System.out.println(new D19P2().runWithStats());
    }
}
