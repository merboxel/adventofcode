package merboxel.codeofadvent.y2023.day2;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day2_1_optimized {

    public static void main(String[] args) throws IOException {

        Scanner sc = readFileAsScanner("input2");

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
    }

    private static Scanner readFileAsScanner(String fileName) throws IOException {

        URL resource = day2_1.class.getClassLoader().getResource(fileName);
        assert resource != null;
        return new Scanner(resource.openStream());
    }
}
