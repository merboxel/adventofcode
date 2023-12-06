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

    public static Integer[] getIntegersAsArray(String line) {

        return getIntegers(line).toArray(new Integer[0]);
    }

    public static List<Long> getLongs(String line) {

        List<Long> matched = new ArrayList<>();

        Pattern pLong = Pattern.compile("\\d+");
        Matcher mLong = pLong.matcher(line);

        while(mLong.find())
            matched.add(Long.parseLong(mLong.group()));

        return matched;
    }

    public static Long[] getLongsAsArray(String line) {

        return getLongs(line).toArray(new Long[0]);
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
