package merboxel.codeofadvent.y2024.D22;

import merboxel.codeofadvent.AoCRunTime;

import java.io.IOException;

public class D22 extends AoCRunTime {

    public D22() throws IOException {
        super(new D22P1(), new D22P2());
    }

    public static void main(String[] args) throws IOException {
        new D22().run();
    }
}
