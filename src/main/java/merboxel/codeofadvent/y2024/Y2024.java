package merboxel.codeofadvent.y2024;

import merboxel.codeofadvent.annotation.AOC2024;
import org.reflections.Reflections;

import java.io.IOException;

public class Y2024 {

    public Y2024() {}

    public void run() throws IOException {

        new Reflections(Y2024.class.getPackage().getName()).getTypesAnnotatedWith(AOC2024.class).forEach(c -> {
            try {
                c.getMethod("run").invoke(c.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });



//        new D20().run();
//        new D21().run();
//        new D22().run();
//        new D23().run();
//        new D24().run();
//        new D25().run();
    }

    public static void main(String[] args) throws IOException {
        new Y2024().run();
    }
}
