package merboxel.codeofadvent.y2024.D23;

import merboxel.codeofadvent.AoCRunTime;

import java.io.IOException;

public class D23 extends AoCRunTime {

    public D23() throws IOException {
        super(new D23P1(), new D23P2());
    }

    public static void main(String[] args) throws IOException {
        new D23().run();
    }
}
