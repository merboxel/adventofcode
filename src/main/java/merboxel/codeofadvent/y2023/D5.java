package merboxel.codeofadvent.y2023;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D5 {

    public static void main(String[] args) throws IOException {
        D5P1.run();
        D5P2.run();
    }
}

class D5P1 {

    public static void main(String[] args) throws IOException {
        run();
    }

    public static void run() throws IOException {

        int order = 0;
        System.out.println("--------------- Part 1 ---------------");
        Scanner sc = readFileAsScanner(2023, 5);

        List<Long> origin = new ArrayList<>();
        List<Long> handled = new ArrayList<>();
        List<Long> moved = new ArrayList<>();
        String sSeeds = sc.nextLine();

        Pattern pInt = Pattern.compile("\\d+");
        Matcher mSeeds = pInt.matcher(sSeeds);

        while(mSeeds.find()) {
            origin.add(Long.parseLong(mSeeds.group()));
        }

        while(sc.hasNext()) {
            String line = sc.nextLine();

            if(line.isEmpty())
            {
                origin.removeAll(handled);
                origin.addAll(moved);
                moved = new ArrayList<>();
                handled = new ArrayList<>();
                sc.nextLine(); //skipping 'map' string
                continue;
            }
            Matcher mMap = pInt.matcher(line);

            Long[] map = new Long[3];
            mMap.find();
            map[0] = Long.parseLong(mMap.group());
            mMap.find();
            map[1] = Long.parseLong(mMap.group());
            mMap.find();
            map[2] = Long.parseLong(mMap.group());

            for(Long l: origin) {
                if(map[1] <= l && map[1] + map[2] - 1 >= l)
                {
                    moved.add(map[0]+(l-map[1]));
                    handled.add(l);
                }
            }
        }

        origin.removeAll(handled);
        origin.addAll(moved);

        System.out.println(origin.stream().min(Comparator.comparingLong(Long::longValue)).orElseThrow());
        System.out.println("--------------------------------------");
    }
}

class D5P2 {

    public static void main(String[] args) throws IOException {
        run();
    }

    public static void run() throws IOException {
        System.out.println("--------------- Part 2 ---------------");
        Scanner sc = readFileAsScanner(2023, 5);

        List<Long> origin = new ArrayList<>();
        List<Seed> seeds = new ArrayList<>();
        List<Long> handled = new ArrayList<>();
        List<Long> moved = new ArrayList<>();
        String sSeeds = sc.nextLine();

        Pattern pInt = Pattern.compile("\\d+");
        Matcher mSeeds = pInt.matcher(sSeeds);

        while(mSeeds.find()) {
            long start = Long.parseLong(mSeeds.group());
            mSeeds.find();
            long range = Long.parseLong(mSeeds.group());
            seeds.add(new Seed(start,range));
        }

        while(sc.hasNext()) {
            String line = sc.nextLine();

            if(line.isEmpty())
            {
                origin.removeAll(handled);
                origin.addAll(moved);
                moved = new ArrayList<>();
                handled = new ArrayList<>();
                sc.nextLine(); //skipping 'map' string
                continue;
            }
            Matcher mMap = pInt.matcher(line);

            Long[] map = new Long[3];
            mMap.find();
            map[0] = Long.parseLong(mMap.group());
            mMap.find();
            map[1] = Long.parseLong(mMap.group());
            mMap.find();
            map[2] = Long.parseLong(mMap.group());

            for(Seed s: seeds) {
                s.parse(map);
            }
        }

        origin.removeAll(handled);
        origin.addAll(moved);

        System.out.println(origin.stream().min(Comparator.comparingLong(Long::longValue)).orElseThrow());
        System.out.println("--------------------------------------");
    }

    static class Seed {
        long start;
        long range;

        List<Seed> usedSeed = new ArrayList<>();
        List<Seed> mapped = new ArrayList<>();

        public Seed(long start, long range) {
            this.start = start;
            this.range = range;
        }

        public void parse(Long[] map) {
            if(start + range < map[1] || start > map[1]+map[2])
                return;

            if(start >= map[1] && start+range <= map[1]+map[2]) {
                mapped.add(new Seed(map[0],range));
                usedSeed.add(this);
                return;
            }
            if(start >= map[1])
            {
                mapped.add(new Seed(map[0]+map[2]-start+map[1],start+map[1]));
                usedSeed.add(new Seed(map[1]+map[2],start+range-(map[1]-map[2])));
                return;
            }

            if(start+range <= map[1]+map[2])
            {
                mapped.add(new Seed(map[0],start+range - map[1]));
                usedSeed.add(new Seed(map[1],start+range-map[1]));
                return;
            }

            if(start < map[1] && start+range > map[1]+map[2])
            {
                mapped.add(new Seed(map[0],map[2]));
                usedSeed.add(new Seed(map[1],map[2]));
            }

            throw new RuntimeException("impossible!");
        }

        public List<Seed> doneParse() {
            return mapped;
        }
    }
}