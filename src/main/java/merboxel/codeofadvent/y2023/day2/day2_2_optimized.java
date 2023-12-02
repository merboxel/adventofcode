package merboxel.codeofadvent.y2023.day2;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day2_2_optimized {

    public static void main(String[] args) throws IOException {

        Scanner sc = readFileAsScanner("input2");

        int power = 0;

        nextGame: while (sc.hasNext()) {

            String line = sc.nextLine();
            int red=0,green=0,blue=0;


            Pattern pRed = Pattern.compile("\\d+ red");
            Matcher mRed = pRed.matcher(line);

            while(mRed.find())
                red = Math.max(red,Integer.parseInt(mRed.group().split(" ")[0]));

            Pattern pGreen = Pattern.compile("\\d+ green");
            Matcher mGreen = pGreen.matcher(line);

            while(mGreen.find())
                green = Math.max(green,Integer.parseInt(mGreen.group().split(" ")[0]));

            Pattern pBlue = Pattern.compile("\\d+ blue");
            Matcher mBlue = pBlue.matcher(line);

            while(mBlue.find())
                blue = Math.max(blue,Integer.parseInt(mBlue.group().split(" ")[0]));

            Pattern pGameNumber = Pattern.compile("\\d+");
            Matcher mGameNumber = pGameNumber.matcher(line);

            power += red*green*blue;
        }

        System.out.println(power);
    }

    private static Scanner readFileAsScanner(String fileName) throws IOException {

        URL resource = day2_1.class.getClassLoader().getResource(fileName);
        assert resource != null;
        return new Scanner(resource.openStream());
    }
}
