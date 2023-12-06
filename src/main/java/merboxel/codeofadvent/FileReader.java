package merboxel.codeofadvent;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class FileReader {

    public static Scanner readFileAsScanner(int year, int day) throws IOException {

        //TODO Auto loading file from AoC
        URL resource = FileReader.class.getClassLoader().getResource(year+"/"+day+".txt");
        assert resource != null;
        return new Scanner(resource.openStream());
    }

    public static Scanner readFileAsScanner(int year, String day) throws IOException {

        //TODO Auto loading file from AoC
        URL resource = FileReader.class.getClassLoader().getResource(year+"/"+day+".txt");
        assert resource != null;
        return new Scanner(resource.openStream());
    }
}
