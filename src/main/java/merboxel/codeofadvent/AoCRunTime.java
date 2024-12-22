package merboxel.codeofadvent;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public abstract class AoCRunTime {

    private final AoC[] problems;

    public AoCRunTime(AoC... problems) {
        this.problems = problems;
    }

    public void run() throws IOException {
        for(AoC problem : problems) {
            Instant start = Instant.now();
            String result = problem.run();
            Duration duration = Duration.between(start, Instant.now());

            System.out.println(problem.getDescription() +", runtime ['" + duration.toMillis() + "ms'], result ['" + result + "']");
        }
    }
}
