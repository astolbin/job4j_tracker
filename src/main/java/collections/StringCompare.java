package collections;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        int rsl = 0;

        int length = Integer.min(left.length(), right.length());
        for (int index = 0; index < length; index++) {
            rsl = Character.compare(left.charAt(index), right.charAt(index));
            if (rsl != 0) {
                break;
            }
        }

        if (rsl == 0) {
            rsl = Integer.compare(left.length(), right.length());
        }

        return rsl;
    }
}
