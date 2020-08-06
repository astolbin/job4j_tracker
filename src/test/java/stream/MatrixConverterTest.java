package stream;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MatrixConverterTest {
    @Test
    public void whenConvertMatrixToList() {
        Integer[][] matrix = {
                {1, 2},
                {3, 4}
        };

        List<Integer> list = new MatrixConverter().toList(matrix);

        assertThat(list, is(List.of(1, 2, 3, 4)));
    }
}