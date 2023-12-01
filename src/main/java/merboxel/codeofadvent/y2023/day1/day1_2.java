package merboxel.codeofadvent.y2023.day1;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class day1_2 {

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

        URL resource = day1_2.class.getClassLoader().getResource(fileName);
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

            if(line.indexOf("one",i,Math.min(i+3,line.length())) != -1) sb.append(1);
            if(line.indexOf("two",i,Math.min(i+3,line.length())) != -1) sb.append(2);
            if(line.indexOf("three",i,Math.min(i+5,line.length())) != -1) sb.append(3);
            if(line.indexOf("four",i,Math.min(i+4,line.length())) != -1) sb.append(4);
            if(line.indexOf("five",i,Math.min(i+4,line.length())) != -1) sb.append(5);
            if(line.indexOf("six",i,Math.min(i+3,line.length())) != -1) sb.append(6);
            if(line.indexOf("seven",i,Math.min(i+5,line.length())) != -1) sb.append(7);
            if(line.indexOf("eight",i,Math.min(i+5,line.length())) != -1) sb.append(8);
            if(line.indexOf("nine",i,Math.min(i+4,line.length())) != -1) sb.append(9);
        }
        return sb.toString();
    }
}