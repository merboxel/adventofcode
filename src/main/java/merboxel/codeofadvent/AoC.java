package merboxel.codeofadvent;

import java.io.IOException;
import java.util.Scanner;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public abstract class AoC {

    protected int year;
    protected int day;

    public AoC(int year, int day) {
        this.year = year;
        this.day = day;
    }

    Scanner readFile() throws IOException {
        return readFileAsScanner(year, day);
    }

    public void run() throws IOException {
        runP1(readFile());
        runP2(readFile());
    }

    public abstract void runP1(Scanner sc);
    public abstract void runP2(Scanner sc);
}
