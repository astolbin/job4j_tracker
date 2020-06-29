package collections;

import java.util.HashSet;
import java.util.List;

public class FullSearch {
    public static HashSet<String> extractNumber(List<TaskNumber> list) {
        HashSet<String> numbers = new HashSet<>();

        for (TaskNumber task : list) {
            numbers.add(task.getNumber());
        }

        return numbers;
    }
}
