package merboxel.codeofadvent.y2024.D21;

import merboxel.codeofadvent.AoCRunTime;

import java.io.IOException;

public class D21 extends AoCRunTime {

    public D21() throws IOException {
        super(new D21P1(), new D21P2());
    }

    public static void main(String[] args) throws IOException {
        new D21().run();
    }
}
