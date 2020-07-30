package lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CalcTest {
    @Test
    public void whenLinearFunctionThenLinearResults() {
        List<Double> result = (new Calc()).diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);

        assertThat(result, is(expected));
    }

    @Test
    public void whenQuadraticFunctionThenQuadraticResults() {
        List<Double> result = (new Calc()).diapason(2, 5, x -> 3 * x * x - 2 * x + 2);
        List<Double> expected = Arrays.asList(10D, 23D, 42D);

        assertThat(result, is(expected));
    }

    @Test
    public void whenPowerFunctionThenPowerResults() {
        List<Double> result = (new Calc()).diapason(2, 5, x -> Math.pow(3, x));
        List<Double> expected = Arrays.asList(9D, 27D, 81D);

        assertThat(result, is(expected));
    }
}