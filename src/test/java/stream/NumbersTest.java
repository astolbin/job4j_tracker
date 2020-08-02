package stream;

import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class NumbersTest {
    @Test
    public void whenFilterPositiveNumbers() {
        List<Integer> numbers = (new Numbers()).filterPositive(
                List.of(3, 0, -5, 10, 15)
        );

        long negative = numbers.stream().filter(
                num -> num < 1
        ).count();

        assertThat(negative, is(0L));
    }
}