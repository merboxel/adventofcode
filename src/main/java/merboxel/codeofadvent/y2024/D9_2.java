package merboxel.codeofadvent.y2024;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D9_2 {

    public static void main(String[] args) throws IOException {
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2024, 9);
    }

    static class P2 {
        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 2 ---------------");
            Scanner sc = readFile();

            PriorityQueue<Triple> solution = new PriorityQueue<>(Comparator.comparing(Triple::index));
            PriorityQueue<Integer>[] freeSpace = new PriorityQueue[10];
            for(int i = 0; i < freeSpace.length; i++) {
                freeSpace[i] = new PriorityQueue<>();
            }



            int[] diskMap = sc.nextLine().chars().map(c -> c - '0').toArray();
            int[] fileIndexes = new int[diskMap.length];
            int index = 0;
            for(int i = 0; i < diskMap.length; i++) {
                if(i % 2 == 1) {
                    freeSpace[diskMap[i]].add(index);
                }
                fileIndexes[i] = index;
                index += diskMap[i];
            }
            int i = diskMap.length - 1;

            do {
                int length = diskMap[i];
                int bestIndex = Integer.MAX_VALUE;
                int bestFreeSpace = -1;
                for(int j = length; j < freeSpace.length; j++) {
                    if(!freeSpace[j].isEmpty() && freeSpace[j].peek() < fileIndexes[i] && freeSpace[j].peek() < bestIndex ) {
                        bestIndex = freeSpace[j].peek();
                        bestFreeSpace = j;
                    }
                }

                if(bestFreeSpace != -1) {
                    int fileIndex = freeSpace[bestFreeSpace].poll();
                    solution.add(new Triple(fileIndex, length, i / 2));
                    if (bestFreeSpace != length) {
                        freeSpace[bestFreeSpace - length].add(fileIndex + length);
                    }
                } else {
                    solution.add(new Triple(fileIndexes[i], length, i/2));
                }

            }while((i -= 2) >= 0);

            long result = 0L;


            int _fileIndex = 0;
            for(Triple triple : solution) {
                for(;_fileIndex < triple.index + triple.length; _fileIndex++) {
                    if(_fileIndex < triple.index)
                        System.out.print('.');
                    else
                        System.out.print(triple.value);
                }
            }

            System.out.println();

            while(!solution.isEmpty()) {
                Triple triple = solution.poll();
                for(int fileIndex = triple.index; fileIndex < triple.index + triple.length; fileIndex++) {
                    result += ((long) fileIndex * triple.value);
                }
            }

            System.out.println(result);
            System.out.println("--------------------------------------");
        }
    }

    private record Triple(int index, int length, int value) {}
}