package merboxel.codeofadvent;

import java.io.IOException;
import java.util.Scanner;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public abstract class AoC {

    protected int year;
    protected int day;
    protected Scanner sc;

    public AoC(int year, int day) throws IOException {
        this.year = year;
        this.day = day;
        this.sc = readFileAsScanner(year, day);
    }

    public AoC(int year, int day, Scanner sc) {
        this.year = year;
        this.day = day;
        this.sc = sc;
    }

    public abstract String run() throws IOException;
}
