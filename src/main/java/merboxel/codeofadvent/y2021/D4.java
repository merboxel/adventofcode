package merboxel.codeofadvent.y2021;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D4 {

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2021, 4);
    }

    static class P1 {
        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 1 ---------------");
            Scanner sc = readFile();

            int[] bingoNumbers = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
            List<BingoLot> tickets = new ArrayList<>();

            while(sc.hasNextLine()) {
                sc.nextLine();
                tickets.add(new BingoLot(sc));
            }

            outer: for(int number : bingoNumbers) {
                for(BingoLot ticket : tickets) {
                    ticket.crossNumber(number);
                    if(ticket.isValid()) {
                        System.out.println(ticket.getValue()*number);
                        break outer;
                    }
                }
            }

            System.out.println("--------------------------------------");
        }

        public static class BingoLot {
            int[][] ticket;
            boolean[][] crossed;

            public BingoLot(Scanner sc) {
                ticket = new int[5][5];
                crossed = new boolean[5][5];

                for(int i = 0; i < 5; i++) {
                    ticket[i] = Arrays.stream(sc.nextLine().trim().split("\\s+")).mapToInt(Integer::parseInt).toArray();
                }
            }

            public void crossNumber(int number) {
                for(int i = 0; i < ticket.length; i++) {
                    for(int j = 0; j < ticket[i].length; j++) {
                        if(ticket[i][j] == number) {
                            crossed[i][j] = true;
                        }
                    }
                }
            }

            public boolean isValid() {
                for(int i = 0; i < ticket.length; i++) {
                    boolean valid = true;
                    for(int j = 0; j < ticket[i].length; j++) {
                        if(!crossed[i][j]) {
                            valid = false;
                            break;
                        }
                    }
                    if(valid)
                        return true;
                }

                for(int i = 0; i < ticket[0].length; i++) {
                    boolean valid = true;
                    for(int j = 0; j < ticket.length; j++) {
                        if(!crossed[j][i]) {
                            valid = false;
                            break;
                        }
                    }
                    if(valid)
                        return true;
                }
                return false;
            }

            public int getValue() {
                int result = 0;
                for(int i = 0; i < ticket.length; i++) {
                    for(int j = 0; j < ticket[i].length; j++) {
                        if(!crossed[i][j]) {
                            result += ticket[i][j];
                        }
                    }
                }
                return result;
            }
        }
    }

    static class P2 {
        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 2 ---------------");
            Scanner sc = readFile();

            int[] bingoNumbers = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
            List<BingoLot> _tickets = new ArrayList<>();

            while(sc.hasNextLine()) {
                sc.nextLine();
                _tickets.add(new BingoLot(sc));
            }

            BingoLot[] tickets = _tickets.toArray(new BingoLot[0]);
            boolean[] wonTickets = new boolean[tickets.length];
            int validTickets = wonTickets.length;

            outer: for(int number : bingoNumbers) {
                for(int i = 0; i < tickets.length; i++) {
                    BingoLot ticket = tickets[i];
                    if(!wonTickets[i]) {
                        tickets[i].crossNumber(number);
                        wonTickets[i] = tickets[i].isValid();
                        if(wonTickets[i]) {
                            validTickets--;
                            if(validTickets == 0) {
                                System.out.println(ticket.getValue()*number);
                            }
                        }
                    }
                }
            }
            System.out.println("--------------------------------------");
        }

        public static class BingoLot {
            int[][] ticket;
            boolean[][] crossed;

            public BingoLot(Scanner sc) {
                ticket = new int[5][5];
                crossed = new boolean[5][5];

                for(int i = 0; i < 5; i++) {
                    ticket[i] = Arrays.stream(sc.nextLine().trim().split("\\s+")).mapToInt(Integer::parseInt).toArray();
                }
            }

            public void crossNumber(int number) {
                for(int i = 0; i < ticket.length; i++) {
                    for(int j = 0; j < ticket[i].length; j++) {
                        if(ticket[i][j] == number) {
                            crossed[i][j] = true;
                        }
                    }
                }
            }

            public boolean isValid() {
                for(int i = 0; i < ticket.length; i++) {
                    boolean valid = true;
                    for(int j = 0; j < ticket[i].length; j++) {
                        if(!crossed[i][j]) {
                            valid = false;
                            break;
                        }
                    }
                    if(valid)
                        return true;
                }

                for(int i = 0; i < ticket[0].length; i++) {
                    boolean valid = true;
                    for(int j = 0; j < ticket.length; j++) {
                        if(!crossed[j][i]) {
                            valid = false;
                            break;
                        }
                    }
                    if(valid)
                        return true;
                }
                return false;
            }

            public int getValue() {
                int result = 0;
                for(int i = 0; i < ticket.length; i++) {
                    for(int j = 0; j < ticket[i].length; j++) {
                        if(!crossed[i][j]) {
                            result += ticket[i][j];
                        }
                    }
                }
                return result;
            }
        }
    }
}
