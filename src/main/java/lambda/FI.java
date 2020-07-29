package lambda;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FI {
    public static void sortNatural(List<String> words) {
        Comparator<String> comparator = (left, right) -> left.compareTo(right);
        Collections.sort(words, comparator);
    }

    public static void sortByLengthDesc(List<String> words) {
        Comparator<String> comparator = (left, right)
                -> Integer.compare(right.length(), left.length());
        Collections.sort(words, comparator);
    }
}