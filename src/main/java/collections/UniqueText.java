package collections;

import java.util.HashSet;

public class UniqueText {
    public static boolean isEquals(String originText, String duplicateText) {
        boolean rsl = true;
        String[] origin = originText.split(" ");
        String[] text = duplicateText.split(" ");
        HashSet<String> check = new HashSet<>();

        for (String word : origin) {
            check.add(word);
        }

        for (String duplicate : text) {
            if (check.add(duplicate)) {
                rsl = false;
                break;
            }
        }

        return rsl;
    }
}
