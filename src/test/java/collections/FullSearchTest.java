package collections;

import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FullSearchTest {
    @Test
    public void extractNumber() {
        List<TaskNumber> tasks = List.of(
            new TaskNumber("1", "First desc"),
            new TaskNumber("2", "Second desc"),
            new TaskNumber("1", "First desc")
        );
        assertThat(FullSearch.extractNumber(tasks), is(Set.of("1", "2")));
    }
}