package merboxel.codeofadvent.y2023;

import merboxel.codeofadvent.util.PatternMatching;

import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D7 {

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2023, 7);
    }

    static class P1 {

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {

            System.out.println("--------------- Part 1 ---------------");
            long result = 0L;
            Scanner sc = readFile();

            PriorityQueue<Hand> rankingHands = new PriorityQueue<>();

            while(sc.hasNext()) {
                rankingHands.add(new Hand(sc.nextLine(), false));
            }

            long rank = 1L;
            while(!rankingHands.isEmpty()) {
                Hand hand = rankingHands.poll();

                result += hand.bid*rank;
                rank++;
            }
            System.out.println(result);
            System.out.println("--------------------------------------");
        }
    }

    static class P2 {

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 2 ---------------");
            long result = 0L;
            Scanner sc = D7.readFile();

            PriorityQueue<Hand> rankingHands = new PriorityQueue<>();

            while (sc.hasNext()) {
                rankingHands.add(new Hand(sc.nextLine(), true));
            }

            long rank = 1L;
            while (!rankingHands.isEmpty()) {
                Hand hand = rankingHands.poll();

                result += hand.bid * rank;
                rank++;
            }
            System.out.println(result);
            System.out.println("--------------------------------------");
        }
    }

    static class Hand implements Comparable<Hand> {

        final int[] cards = new int[5];
        final String hand;
        final int[] bucket = new int[15];
        final int rank;
        final long bid;

        public Hand(String hand,boolean joker) {
            String[] _hand = PatternMatching.getWordsAsArray(hand);

            bid = Long.parseLong(_hand[1]);
            this.hand = _hand[0];

            int i=0;
            for(char c: _hand[0].toCharArray()) {
                switch (c) {
                    case 'A' -> {
                        cards[i] = 14;
                        bucket[14] ++;
                    }
                    case 'K' -> {
                        cards[i] = 13;
                        bucket[13] ++;
                    }
                    case 'Q' -> {
                        cards[i] = 12;
                        bucket[12] ++;
                    }
                    case 'J' -> {
                        if(joker) {
                            cards[i] = 1;
                            bucket[1]++;
                        } else {
                            cards[i] = 11;
                            bucket[11]++;
                        }
                    }
                    case 'T' -> {
                        cards[i] = 10;
                        bucket[10] ++;
                    }
                    default -> {
                        cards[i] = Integer.parseInt(c+"");
                        bucket[Integer.parseInt(c+"")] ++;
                    }
                }
                i++;
            }
            this.rank = calculateRank();
        }
        private int calculateRank() {
            int max = 0,max2 = 0;
            for(int i =2; i < 15; i++) {
                if(bucket[i] >= max) {
                    max2 = max;
                    max = bucket[i];
                } else {
                    if(bucket[i] >= max2) {
                        max2 = bucket[i];
                    }
                }
            }
            max += bucket[1];
            switch (max) {
                case 5 -> {
                    return 6;
                }
                case 4 -> {
                    return 5;
                }
                case 3 -> {
                    if(max2 == 2)
                        return 4;
                    else
                        return 3;
                }
                case 2-> {
                    if(max2 == 2)
                        return 2;
                    else
                        return 1;
                }
                case 1 -> {
                    return 0;
                }
            }
            throw new RuntimeException("No cards found??");
        }

        @Override
        public int compareTo(Hand that) {
            int compRank = Integer.compare(this.rank,that.rank);
            if(compRank == 0) {
                for(int i = 0; i < 5;i++) {
                    int compRank2 = Integer.compare(this.cards[i],that.cards[i]);
                    if(compRank2 == 0)
                        continue;
                    else
                        return compRank2;
                }
            }
            return compRank;
        }
    }
}