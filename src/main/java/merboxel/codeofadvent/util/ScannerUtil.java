package merboxel.codeofadvent.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScannerUtil {

    public static List<String> toList(Scanner sc) {

        List<String> list = new ArrayList<>();

        while(sc.hasNext())
            list.add(sc.nextLine());

        return list;
    }

    public static String[] toArray(Scanner sc) {

        return toList(sc).toArray(new String[0]);
    }
}
