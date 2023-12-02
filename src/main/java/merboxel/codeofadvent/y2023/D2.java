package merboxel.codeofadvent.y2023;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D2 {

    public static void main(String[] args) throws IOException {
        D2P1.run();
        D2P2.run();
    }
}

class D2P1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    public static void run() throws IOException {
        System.out.println("--------------- Part 1 ---------------");

        Scanner sc = readFileAsScanner(2023, 2);

        int sumValidGameNumbers = 0;

        nextGame: while (sc.hasNext()) {

            String line = sc.nextLine();

            Pattern pRed = Pattern.compile("\\d+ red");
            Matcher mRed = pRed.matcher(line);

            while(mRed.find())
                if(Integer.parseInt(mRed.group().split(" ")[0]) > 12)
                    continue nextGame;

            Pattern pGreen = Pattern.compile("\\d+ green");
            Matcher mGreen = pGreen.matcher(line);

            while(mGreen.find())
                if(Integer.parseInt(mGreen.group().split(" ")[0]) > 13)
                    continue nextGame;

            Pattern pBlue = Pattern.compile("\\d+ blue");
            Matcher mBlue = pBlue.matcher(line);

            while(mBlue.find())
                if(Integer.parseInt(mBlue.group().split(" ")[0]) > 14)
                    continue nextGame;

            Pattern pGameNumber = Pattern.compile("\\d+");
            Matcher mGameNumber = pGameNumber.matcher(line);

            if(mGameNumber.find())
                sumValidGameNumbers += Integer.parseInt(mGameNumber.group());
        }

        System.out.println(sumValidGameNumbers);
        System.out.println("--------------------------------------");
    }
}

class D2P2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    public static void run() throws IOException {
        System.out.println("--------------- Part 2 ---------------");

        Scanner sc = readFileAsScanner(2023, 2);

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
        System.out.println("--------------------------------------");
    }
}