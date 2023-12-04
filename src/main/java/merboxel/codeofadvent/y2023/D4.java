package merboxel.codeofadvent.y2023;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D4 {

    public static void main(String[] args) throws IOException {
        D4P1.run();
        D4P2.run();
    }
}

class D4P1 {

    static List<List<Part>> engineBlock;
    static List<List<Integer>> symbolsBlock;

    public static void main(String[] args) throws IOException {
        run();
    }

    public static void run() throws IOException {
        System.out.println("--------------- Part 1 ---------------");
        Scanner sc = readFileAsScanner(2023, 4);

        int totalTicketValue = 0;
        while(sc.hasNext()) {
            String line = sc.nextLine();
            String[] numbers = line.split(": ")[1].split(Pattern.quote("|"));

            int valueTicket = 0;
            Set<Integer> winningNumbers = new HashSet<>();

            Pattern pInt = Pattern.compile("\\d+");
            Matcher winningInt = pInt.matcher(numbers[1]);

            while(winningInt.find()){
                winningNumbers.add(Integer.parseInt(winningInt.group()));
            }

            Matcher ownInt = pInt.matcher(numbers[0]);
            while(ownInt.find()) {
                if(winningNumbers.contains(Integer.parseInt(ownInt.group())))
                {
                    if(valueTicket == 0)
                        valueTicket = 1;
                    else
                        valueTicket = valueTicket << 1;
                }
            }

            totalTicketValue += valueTicket;
        }

        System.out.println(totalTicketValue);
        System.out.println("--------------------------------------");
    }
}

class D4P2 {

    public static void main(String[] args) throws IOException {
        run();
    }

    public static void run() throws IOException {
        System.out.println("--------------- Part 2 ---------------");
        Scanner sc = readFileAsScanner(2023, 4);

        int[] totalCards = new int[207];
        while(sc.hasNext()) {
            String line = sc.nextLine();
            String[] gameLine = line.split(": ");
            String[] numbers = gameLine[1].split(Pattern.quote("|"));
            Pattern pInt = Pattern.compile("\\d+");
            Matcher gameNumber = pInt.matcher(gameLine[0]);
            gameNumber.find();
            int game = Integer.parseInt(gameNumber.group());

            int valueTicket = 0;
            Set<Integer> winningNumbers = new HashSet<>();

            Matcher winningInt = pInt.matcher(numbers[1]);

            while(winningInt.find()){
                winningNumbers.add(Integer.parseInt(winningInt.group()));
            }

            Matcher ownInt = pInt.matcher(numbers[0]);
            while(ownInt.find()) {
                if(winningNumbers.contains(Integer.parseInt(ownInt.group())))
                {
                    if(valueTicket == 0)
                        valueTicket = 1;
                    else
                        valueTicket ++;
                }
            }
            totalCards[game] ++;
            for(int i = 1; i <= valueTicket && game+i <= 206; i++){
                totalCards[game+i] += totalCards[game];
            }
        }

        int totalValue = 0;
        for(int i = 1; i < totalCards.length; i++)
        {
            totalValue += totalCards[i];
        }
        System.out.println(totalValue);
        System.out.println("--------------------------------------");
    }
}