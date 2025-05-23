package merboxel.codeofadvent;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public abstract class AoC {

    protected final int year;
    protected final int day;
    protected final int part;
    protected final Scanner sc;

    public AoC(int year, int day, int part) throws IOException {
        this.year = year;
        this.day = day;
        this.part = part;
        this.sc = readFileAsScanner(year, day);
    }

    public AoC(int year, int day, int part, Scanner sc) {
        this.year = year;
        this.day = day;
        this.part = part;
        this.sc = sc;
    }

    public int getYear() {
        return year;
    }

    public int getDay() {
        return day;
    }

    public int getPart() {
        return part;
    }

    public String runWithStats() throws IOException {
        Instant start = Instant.now();
        String result = run();
        Duration duration = Duration.between(start, Instant.now());

        return "problem ['y:" + getYear() + " d:" + getDay() + " p:" + getPart() + "']" + ", runtime ['" + duration.toMillis() + "ms'], result ['" + result + "']";
    }

    public abstract String run() throws IOException;
}
