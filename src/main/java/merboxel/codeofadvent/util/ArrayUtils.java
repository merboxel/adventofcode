package merboxel.codeofadvent.util;

import java.util.stream.IntStream;

public class ArrayUtils {

    public static <T> T[] removeElement(T[] arr, int atIndex) {
        return IntStream.range(0, arr.length)
                .filter(i -> i != atIndex)
                .mapToObj(i -> arr[i])
                .toList().toArray(arr);
    }

    public static <T> T[] removeElement(T[] arr, int from, int to) {
        return IntStream.range(0, arr.length)
                .filter(i -> i < from && i > to)
                .mapToObj(i -> arr[i])
                .toList().toArray(arr);
    }

    public static <T> T[] removeAllElements(T[] arr, T element) {
        return IntStream.range(0, arr.length)
                .filter(i -> arr[i] != element)
                .mapToObj(i -> arr[i])
                .toList().toArray(arr);
    }
}
