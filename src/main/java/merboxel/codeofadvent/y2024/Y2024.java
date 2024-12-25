package merboxel.codeofadvent.y2024;

import merboxel.codeofadvent.y2024.D20.D20;
import merboxel.codeofadvent.y2024.D20.D20P1;
import merboxel.codeofadvent.y2024.D20.D20P2;
import merboxel.codeofadvent.y2024.D21.D21;
import merboxel.codeofadvent.y2024.D22.D22;
import merboxel.codeofadvent.y2024.D23.D23;
import merboxel.codeofadvent.y2024.D24.D24;
import merboxel.codeofadvent.y2024.D25.D25;

import java.io.IOException;

public class Y2024 {

    public Y2024() {}

    public void run() throws IOException {
        new D20().run();
        new D21().run();
        new D22().run();
        new D23().run();
        new D24().run();
        new D25().run();
    }

    public static void main(String[] args) throws IOException {
        new Y2024().run();
    }
}
