package merboxel.codeofadvent.y2023;


import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D15 {

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2023, 15);
    }

    static class P1 {

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 1 ---------------");
            long result = 0L;
            Scanner sc = readFile();

            while (sc.hasNext()) {
                String[] words = sc.nextLine().split(",");
                for(String word: words) {
                    long hashValue = 0;
                    for(char c: word.toCharArray()) {
                        hashValue = ((hashValue+c)*17) % 256;
                    }
                    result += hashValue;
                }
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
            Scanner sc = readFile();

            List<List<Entry>> boxes = new ArrayList<>();

            for(int i = 0; i < 256; i++)
                boxes.add(new ArrayList<>());

            while (sc.hasNext()) {
                String[] words = sc.nextLine().split(",");
                for (String word : words) {

                    Entry hashEntry = new Entry(word);
                    List<Entry> currentBox = boxes.get(hashEntry.box);
                    switch(hashEntry.operation) {
                        case '-' -> {
                            boxes.add(hashEntry.box, currentBox.stream().filter((elem) -> !elem.label.equals(hashEntry.label)).collect(Collectors.toList()));
                            boxes.remove(hashEntry.box+1);
                        }
                        case '=' -> {
                            List<Entry> newBoxes = new ArrayList<>();
                            boolean found = false;
                            for(Entry element: currentBox) {
                                if(element.label.equals(hashEntry.label)) {
                                    found = true;
                                    newBoxes.add(hashEntry);
                                    continue;
                                }
                                newBoxes.add(element);
                            }
                            if(!found)
                                newBoxes.add(hashEntry);
                            boxes.add(hashEntry.box, newBoxes);
                            boxes.remove(hashEntry.box+1);
                        }
                    }
                }
            }
            long boxNr = 1L;
            for(List<Entry> queue: boxes) {
                long queuNr = 1L;
                for(Entry lens: queue) {
                    result += boxNr*queuNr*lens.lens;
                    queuNr++;
                }
                boxNr++;
            }

            System.out.println(result);
            System.out.println("--------------------------------------");
        }

        static class Entry {
            int box;
            long lens;
            char operation;
            String label;

            public Entry(String word) {
                loop: for (char c : word.toCharArray()) {
                    switch (c) {
                        case '-' -> {
                            operation = c;
                            label = word.substring(0, word.length() - 1);
                            break loop;
                        }
                        case '=' -> {
                            operation = c;
                            lens = Long.parseLong(word.charAt(word.length() - 1)+"");
                            label = word.substring(0, word.length() - 2);
                            break loop;
                        }
                    }

                    box = ((box+c)*17) % 256;
                }
            }
        }
    }
}