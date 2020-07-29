package lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FITest {
    @Test
    public void whenSortNatural() {
        List<String> cars = new ArrayList<>();

        cars.add("BMW");
        cars.add("Audi");
        cars.add("Mazda");
        cars.add("Volkswagen");

        FI.sortNatural(cars);

        assertThat(cars.get(0), is("Audi"));
    }

    @Test
    public void whenSortByLengthDesc() {
        List<String> cars = new ArrayList<>();

        cars.add("BMW");
        cars.add("Audi");
        cars.add("Mazda");
        cars.add("Volkswagen");

        FI.sortByLengthDesc(cars);

        assertThat(cars.get(0), is("Volkswagen"));
    }
}