package merboxel.codeofadvent.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatching {

    public static List<Integer> getIntegers(String line) {

        List<Integer> matched = new ArrayList<>();

        Pattern pInt = Pattern.compile("\\d+");
        Matcher mInt = pInt.matcher(line);

        while(mInt.find())
            matched.add(Integer.parseInt(mInt.group()));

        return matched;
    }

    public static int[] getIntegersAsArray(String line) {
        List<Integer> ints = getIntegers(line);
        int[] tmp = new int[ints.size()];
        for(int i = 0; i <ints.size(); i++)
            tmp[i] = ints.get(i);

        return tmp;
    }

    public static List<Long> getLongs(String line) {

        List<Long> matched = new ArrayList<>();

        Pattern pLong = Pattern.compile("\\d+");
        Matcher mLong = pLong.matcher(line);

        while(mLong.find())
            matched.add(Long.parseLong(mLong.group()));

        return matched;
    }

    public static long[] getLongsAsArray(String line) {

        List<Long> longs = getLongs(line);
        long[] tmp = new long[longs.size()];
        for(int i = 0; i <longs.size(); i++)
            tmp[i] = longs.get(i);

        return tmp;
    }

    public static List<Long> getLongsWithNegatives(String line) {

        List<Long> matched = new ArrayList<>();

        Pattern pLong = Pattern.compile("-?\\d+");
        Matcher mLong = pLong.matcher(line);

        while(mLong.find())
            matched.add(Long.parseLong(mLong.group()));

        return matched;
    }

    public static long[] getLongsWithNegativesAsArray(String line) {

        List<Long> longs = getLongsWithNegatives(line);
        long[] tmp = new long[longs.size()];
        for(int i = 0; i <longs.size(); i++)
            tmp[i] = longs.get(i);

        return tmp;
    }

    public static List<Long> getRationNumbersAsLongs(String line) {

        List<Long> matched = new ArrayList<>();

        Pattern pLong = Pattern.compile("-?\\d*\\.?\\d+");
        Matcher mLong = pLong.matcher(line);

        while(mLong.find())
            matched.add(Long.parseLong(mLong.group()));

        return matched;
    }

    public static long[] getRationNumbersAsLongArray(String line) {

        List<Long> longs = getLongsWithNegatives(line);
        long[] tmp = new long[longs.size()];
        for(int i = 0; i <longs.size(); i++)
            tmp[i] = longs.get(i);

        return tmp;
    }

    public static List<String> getWords(String line) {

        List<String> matched = new ArrayList<>();

        Pattern pLong = Pattern.compile(" +");
        Matcher mLong = pLong.matcher(line);

        while(mLong.find())
            matched.add(mLong.group());

        return matched;
    }

    public static String[] getWordsAsArray(String line) {
        return line.split(" +");
    }
}
