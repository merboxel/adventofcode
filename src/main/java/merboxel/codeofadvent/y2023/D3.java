package merboxel.codeofadvent.y2023;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D3 {

    public static void main(String[] args) throws IOException {
        D3P1.run();
        D3P2.run();
    }
}

class D3P1 {

    static List<List<Part>> engineBlock;
    static List<List<Integer>> symbolsBlock;

    public static void main(String[] args) throws IOException {
        run();
    }

    public static void run() throws IOException {
        System.out.println("--------------- Part 1 ---------------");
        Scanner sc = readFileAsScanner(2023, 3);

        engineBlock = new ArrayList<>();
        symbolsBlock = new ArrayList<>();
        while(sc.hasNext()) {
            String line = sc.nextLine();

            List<Part> engineLine = new ArrayList<>();
            List<Integer> symbolLine = new ArrayList<>();

            Pattern pInt = Pattern.compile("\\d+");
            Matcher mInt = pInt.matcher(line);
            while(mInt.find()) {
                engineLine.add(new Part(mInt.start(), mInt.end(), mInt.group()));
            }

            Pattern pSymbol = Pattern.compile("[^0-9.]");
            Matcher mSymbol = pSymbol.matcher(line);

            while(mSymbol.find()) {
                symbolLine.add(mSymbol.start());
            }

            engineBlock.add(engineLine);
            symbolsBlock.add(symbolLine);
        }

        System.out.println(calcSumParts());
        System.out.println("--------------------------------------");
    }

    private static int calcSumParts() {

        int engineSizeY = engineBlock.size();
        int sumEngine = 0;

        for(int i = 0; i < engineBlock.size();i++) {
            outer: for(Part enginePart: engineBlock.get(i)) {
                inner: for (int j = i - 1; j <= i + 1; j++) {
                    if (j < 0 || j >= engineSizeY)
                        continue inner;

                    for (Integer symbolX : symbolsBlock.get(j)) {
                        if (enginePart.isEnginePart(symbolX)) {
                            sumEngine += enginePart.getTotalValue();
                            continue outer;
                        }
                    }
                }
            }
        }
        return sumEngine;
    }
}

class D3P2 {

    static List<List<Part>> engineBlock;
    static List<List<Gear>> gearsBlock;

    public static void main(String[] args) throws IOException {
        run();
    }

    public static void run() throws IOException {
        System.out.println("--------------- Part 2 ---------------");
        Scanner sc = readFileAsScanner(2023, 3);

        engineBlock = new ArrayList<>();
        gearsBlock = new ArrayList<>();
        while(sc.hasNext()) {
            String line = sc.nextLine();

            List<Part> engineLine = new ArrayList<>();
            List<Gear> gearLine = new ArrayList<>();

            Pattern pInt = Pattern.compile("\\d+");
            Matcher mInt = pInt.matcher(line);
            while(mInt.find()) {
                engineLine.add(new Part(mInt.start(), mInt.end(), mInt.group()));
            }

            Pattern pSymbol = Pattern.compile("[^0-9.]");
            Matcher mSymbol = pSymbol.matcher(line);

            while(mSymbol.find()) {
                gearLine.add(new Gear(mSymbol.start()));
            }

            engineBlock.add(engineLine);
            gearsBlock.add(gearLine);
        }
        System.out.println(calcSumParts());
        System.out.println("--------------------------------------");
    }

    private static int calcSumParts() {

        int engineSizeY = engineBlock.size();

        for(int i = 0; i < engineBlock.size();i++) {
            outer: for(Part enginePart: engineBlock.get(i)) {
                inner: for (int j = i - 1; j <= i + 1; j++) {
                    if (j < 0 || j >= engineSizeY)
                        continue inner;

                    for (Gear gear : gearsBlock.get(j)) {
                        if (enginePart.isEnginePart(gear.getPosX())) {
                            gear.addValue(enginePart);
                            continue outer;
                        }
                    }
                }
            }
        }

        int sumEngine = 0;
        for(List<Gear> gears: gearsBlock)
        {
            for(Gear gear: gears) {
                sumEngine += gear.getTotalValue();
            }
        }
        return sumEngine;
    }
}

class Part {
    final int posX;
    final int length;
    final int totalValue;

    public Part(int startX, int endX,String totalValue) {
        this.posX = startX;
        this.length = endX - startX;
        this.totalValue = Integer.parseInt(totalValue);
    }

    public int getTotalValue() {
        return totalValue;
    }

    public boolean isEnginePart(int symbolX) {
        return posX - 1 <= symbolX && posX + length >= symbolX;
    }
}

class Gear {
    private final int posX;
    private final List<Part> parts;

    public Gear(int posX) {
        this.parts = new ArrayList<>();
        this.posX = posX;
    }

    public void addValue(Part part) {
        parts.add(part);
    }

    public int getPosX() {
        return posX;
    }

    public int getTotalValue() {
        if (parts.size() != 2)
            return 0;
        return parts.get(0).getTotalValue() * parts.get(1).getTotalValue();
    }
}