package merboxel.codeofadvent.y2023.day1;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class day1_1 {

    public static void main(String[] args) throws IOException {

        Scanner sc = readFileAsScanner("input1");

        int result = 0;

        while (sc.hasNext()) {

            String line = sc.nextLine();
            result += calcLine(parse(line));
        }

        System.out.println(result);
    }

    private static Scanner readFileAsScanner(String fileName) throws IOException {

        URL resource = day1_1.class.getClassLoader().getResource(fileName);
        assert resource != null;
        return new Scanner(resource.openStream());
    }

    private static int calcLine(String line) {

        int firstInt = Integer.parseInt(line.charAt(0)+"");
        int lastInt = Integer.parseInt(line.charAt(line.length()-1)+"");

        return firstInt*10 + lastInt;
    }

    private static String parse(String line){
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<line.length();i++){

            if(line.charAt(i) >= '0' && line.charAt(i) <='9') sb.append(line.charAt(i));
        }
        return sb.toString();
    }
}