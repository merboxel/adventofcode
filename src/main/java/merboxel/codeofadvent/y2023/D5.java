package merboxel.codeofadvent.y2023;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D5 {

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2023, 5);
    }

    static class P1 {

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {

            int order = 0;
            System.out.println("--------------- Part 1 ---------------");
            Scanner sc = readFile();

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

    static class P2 {

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 2 ---------------");
            Scanner sc = readFile();

            List<Seed> seeds = new ArrayList<>();
            String sSeeds = sc.nextLine();

            Pattern pInt = Pattern.compile("\\d+");
            Matcher mSeeds = pInt.matcher(sSeeds);

            Map[][] mapper = new Map[7][];

            while(mSeeds.find()) {
                long start = Long.parseLong(mSeeds.group());
                mSeeds.find();
                long range = Long.parseLong(mSeeds.group());
                seeds.add(new Seed(start,range));
            }
            sc.nextLine();
            sc.nextLine();

            int iMap = 0;
            List<Map> mapStruct = new ArrayList<>();

            while(sc.hasNext()) {
                String line = sc.nextLine();

                if(line.isEmpty())
                {
                    mapper[iMap] = mapStruct.toArray(new Map[0]);
                    mapStruct = new ArrayList<>();
                    iMap++;
                    sc.nextLine(); //skipping 'map' string
                    continue;
                }
                Matcher mMap = pInt.matcher(line);

                Long[] tmp = new Long[3];
                mMap.find();
                tmp[0] = Long.parseLong(mMap.group());
                mMap.find();
                tmp[1] = Long.parseLong(mMap.group());
                mMap.find();
                tmp[2] = Long.parseLong(mMap.group());

                mapStruct.add(new Map(tmp[0],tmp[1],tmp[2]));
            }

            mapper[iMap] = mapStruct.toArray(new Map[0]);

            long min = Long.MAX_VALUE;

            for(Seed s: seeds) {
                for(long l = s.start; l < s.start + s.range; l++) {
                    long parsing = l;
                    nextMap: for(int i = 0; i < 7; i++) {
                        for(Map m: mapper[i]) {
                            if (m.hit(parsing)) {
                                Long parsed = m.parse(parsing);
                                parsing = parsed;
                                continue nextMap;
                            }
                        }
                    }
                    min = Math.min(min,parsing);
                }
            }
            System.out.println(min);
            System.out.println("--------------------------------------");
        }

        static class Seed {
            public final long start;
            public final long range;

            public Seed(long start, long range) {
                this.start = start;
                this.range = range;
            }
        }

        static class Map {

            public final long dest;
            public final long src;
            public final long range;

            public Map(Long dest, Long src, Long range) {
                this.dest = dest;
                this.src = src;
                this.range = range;
            }

            public boolean hit(Long target) {
                return (src <= target && src + range > target);
            }

            public Long parse(Long target) {
                if(hit(target))
                {
                    return dest + (target - src);
                }
                return target;
            }
        }
    }
}