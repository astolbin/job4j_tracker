package stream;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class FlatIt {
    public static List<Integer> flatten(Iterator<Iterator<Integer>> it) {
        Iterable<Iterator<Integer>> iteratorOuter = () -> it;
        Stream<Iterator<Integer>> stream =
                StreamSupport.stream(iteratorOuter.spliterator(), false);

        return stream
                .flatMap(itInner -> {
                    Iterable<Integer> iteratorInner = () -> itInner;
                    return StreamSupport.stream(iteratorInner.spliterator(), false);
                })
                .collect(Collectors.toList());
    }
}
