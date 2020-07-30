package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Calc {
    public List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> rsl = new ArrayList<>();

        for (int i = start; i < end; i++) {
            double value = func.apply((double) i);
            rsl.add(value);
        }

        return rsl;
    }
}
