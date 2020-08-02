package stream;

import java.util.List;
import java.util.stream.Collectors;

public class Numbers {
    public List<Integer> filterPositive(List<Integer> numbers) {
        return numbers.stream().filter(
                num -> num > 0
        ).collect(Collectors.toList());
    }
}
